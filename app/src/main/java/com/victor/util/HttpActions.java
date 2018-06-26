package com.victor.util;import android.content.Context;import android.os.Bundle;import android.text.TextUtils;import android.util.Log;import com.google.gson.JsonObject;import com.victor.data.EpgData;import com.victor.data.EpgInfo;import com.victor.data.YoutubeInfo;import com.victor.data.YoutubeReq;import com.victor.module.DataObservable;import com.victor.player.library.util.PlayUtil;import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject;import org.jsoup.Jsoup;import org.jsoup.nodes.Document;import org.jsoup.select.Elements;import java.io.IOException;import java.io.Serializable;import java.net.SocketTimeoutException;import java.util.ArrayList;import java.util.HashMap;import java.util.Iterator;import java.util.List;/** * Created by victor on 2016/1/21. */public class HttpActions {    private static String TAG = "HttpActions";    public static void requestLiveEpgAction (Context context,String url){        Log.e(TAG,"requestLiveEpgAction()......url = " + url);        int status = 0;        EpgData epgData = new EpgData();        if (HttpUtil.isNetEnable(context)){            try {                Document document = Jsoup.connect(url)                        .userAgent("Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/535.12")                        .timeout(10000).get();                HashMap<String,String> epgMap = new HashMap<>();//                Elements liElements = document.getElementsByClass("li");                Elements liElements = document.getElementsByAttribute("data-name");                if (liElements.size() > 0) {                    List<EpgInfo> epgInfos = new ArrayList<>();                    for (int i = 0; i < liElements.size(); i++) {                        String[] epgs = liElements.get(i).text().split(" ");                        epgMap.put(epgs[0],epgs[1]);                        EpgInfo info = new EpgInfo();                        info.epg = epgs[1];                        Loger.e(TAG,"info.epg = " + info.epg);                        if (i < liElements.size() - 1) {                            info.startTime = liElements.get(i).text().split(" ")[0];                            info.endTime = liElements.get(i + 1).text().split(" ")[0];                        } else {                            if (i == liElements.size() - 1) {                                info.startTime = liElements.get(i).text().split(" ")[0];                                info.endTime = "00:00";                            }                        }                        epgInfos.add(info);                    }                    status = Constant.Msg.REQUEST_SUCCESS;                    epgData.epgInfos = epgInfos;                } else {                    status = Constant.Msg.REQUEST_SUCCESS_NO_DATA;                }            } catch (SocketTimeoutException e) {                e.printStackTrace();                status = Constant.Msg.SOCKET_TIME_OUT;            } catch (IOException e) {                e.printStackTrace();            }        } else {            status = Constant.Msg.NETWORK_ERROR;        }        epgData.status = status;        DataObservable.getInstance().setData(epgData);    }    public static void requestYoutubeData (Context context,String url) {        Log.e(TAG,"requestYoutubeData()......url = " + url);        int status = 0;        YoutubeReq youtubeReq = new YoutubeReq();        List<YoutubeInfo> datas = new ArrayList<>();        if (HttpUtil.isNetEnable(context)){            try {                Document document = Jsoup.connect(url)                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0")                        .timeout(10000).get();                if (document != null) {                    String result = document.toString();                    if (!TextUtils.isEmpty(result)) {                        if (result.contains("\n")) {                            String[] items = document.toString().split("\n");                            for (int i=0;i<items.length;i++) {                                if (items[i].contains("itemListElement")) {                                    JSONObject res = new JSONObject(items[i]);                                    if (res != null) {                                        JSONArray list = res.getJSONArray("itemListElement");                                        if (list != null && list.length() > 0) {                                            for (int j=0;j<list.length();j++) {                                                JSONObject cell = list.getJSONObject(j);                                                JSONObject item = cell.getJSONObject("item");                                                if (item != null) {                                                    JSONArray itemArry = item.getJSONArray("itemListElement");                                                    if (itemArry != null && itemArry.length() > 0) {                                                        for (int k=0;k<itemArry.length();k++) {                                                            JSONObject info = itemArry.getJSONObject(k);                                                            YoutubeInfo data = new YoutubeInfo();                                                            data.url = info.optString("url");                                                            if (!TextUtils.isEmpty(data.url)) {                                                                data.poster = String.format(Constant.YOUTUBE_IMG_URL,PlayUtil.getVideoId(data.url));                                                            }                                                            datas.add(data);                                                        }                                                    }                                                }                                            }                                        }                                    }                                }                            }                            youtubeReq.data = datas;                        }                    }                }            } catch (SocketTimeoutException e) {                e.printStackTrace();                status = Constant.Msg.SOCKET_TIME_OUT;            } catch (IOException e) {                e.printStackTrace();            } catch (JSONException e) {                e.printStackTrace();            }        } else {            status = Constant.Msg.NETWORK_ERROR;        }        youtubeReq.status = status;        DataObservable.getInstance().setData(youtubeReq);    }    public static void requestVimeoData (Context context,String url) {        Log.e(TAG,"requestVimeoData()......url = " + url);        int status = 0;        YoutubeReq youtubeReq = new YoutubeReq();        List<YoutubeInfo> datas = new ArrayList<>();        if (HttpUtil.isNetEnable(context)){            try {                Document document = Jsoup.connect(url)                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0")                        .timeout(10000).get();                if (document != null) {                    String result = document.toString();                    if (!TextUtils.isEmpty(result)) {                        if (result.contains("\n")) {                            String[] items = document.toString().split("\n");                            for (int i=0;i<items.length;i++) {                                if (items[i].contains("explore_data")) {                                    String json = items[i].replace("vimeo.explore_data", "").                                            replace("=","").replace(";","");                                    JSONObject res = new JSONObject(json);                                    if (res != null) {                                        JSONArray modules = res.getJSONArray("modules");                                        if (modules != null && modules.length() > 0) {                                            for (int j=0;j<modules.length();j++) {                                                JSONObject module = modules.getJSONObject(j);                                                JSONArray collectionItems = module.optJSONArray("collection_items");                                                if (collectionItems != null && collectionItems.length() > 0) {                                                    for (int k=0;k<collectionItems.length();k++) {                                                        JSONObject item = collectionItems.getJSONObject(k);                                                        YoutubeInfo info = new YoutubeInfo();                                                        info.url = item.optString("url").replace("/","");                                                        info.poster = item.optString("thumbnail");                                                        info.videoName = item.optString("title");                                                        datas.add(info);                                                    }                                                }                                            }                                        }                                    }                                }                            }                            youtubeReq.data = datas;                        }                    }                }            } catch (SocketTimeoutException e) {                e.printStackTrace();                status = Constant.Msg.SOCKET_TIME_OUT;            } catch (IOException e) {                e.printStackTrace();            } catch (JSONException e) {                e.printStackTrace();            }        } else {            status = Constant.Msg.NETWORK_ERROR;        }        youtubeReq.status = status;        DataObservable.getInstance().setData(youtubeReq);    }}