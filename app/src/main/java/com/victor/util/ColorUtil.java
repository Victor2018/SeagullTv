package com.victor.util;

import com.victor.app.App;
import com.victor.seagull.R;

import java.util.Random;

/**
 * 随机获取默认占位图背景颜色
 * Created by victor on 2017/8/10 0010.
 */

public class ColorUtil {
    public static int getDefaultRandomColor () {
        int[] colors = new int[] {
                App.getContext().getResources().getColor(R.color.color_E6FCFC),
                App.getContext().getResources().getColor(R.color.color_E6FBFD),
                App.getContext().getResources().getColor(R.color.color_E5FAFE),
                App.getContext().getResources().getColor(R.color.color_E5F9FE)};
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }
}
