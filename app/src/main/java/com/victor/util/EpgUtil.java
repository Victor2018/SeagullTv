package com.victor.util;

import com.victor.model.data.EpgData;
import com.victor.model.data.EpgInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 2016/9/8.
 */
public class EpgUtil {
    private static String TAG = "EpgUtil";
    public static List<EpgInfo> getEpgByTime (EpgData epgData) {
        List<EpgInfo> epgs = new ArrayList<>();
        List<EpgInfo> epgInfos = epgData.epgInfos;
        if (epgInfos != null && epgInfos.size() > 0) {
            for (int i=0;i<epgInfos.size();i++) {
                int a = compare_date(epgInfos.get(i).startTime,false);
                int b = compare_date(epgInfos.get(i).endTime,true);
                if (a >= 0 && b <= 0) {//当前时间比开始时间大并且当前时间小于结束时间
                    if (i < epgInfos.size() - 1) {
                        epgs.add(epgInfos.get(i));
                        epgs.add(epgInfos.get(i + 1));
                    } else {
                        epgs.add(epgInfos.get(i));
                    }
                    break;
                }
            }
        }
        return epgs;
    }

    public static int compare_date(String date2,boolean isEndTime) {
        String date1 = DateUtil.getCurrentTime();
        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);
        if (isEndTime && date2.equals("00:00")) {
            return -1;
        }
        DateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            System.out.println("dt1 = " + dt1);
            System.out.println("dt2 = " + dt2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("比当前时间小");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("比当前时间大");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
