package com.victor.module;import android.app.WallpaperManager;import android.content.Context;import android.os.Bundle;import android.os.Handler;import android.os.HandlerThread;import android.os.Message;import com.victor.data.Picture;import com.victor.util.BitmapUtil;import com.victor.util.Constant;import com.victor.util.HttpActions;import java.util.HashMap;/** * Created by victor on 2016/1/21. */public class HttpRequestHelper {    private String TAG = "HttpRequestHelper";    private Context mContext;    private WallpaperManager mWallpaperManager;    private Handler mRequestHandler;    private HandlerThread mRequestHandlerThread;    public HttpRequestHelper (Context context) {        mContext = context;        mWallpaperManager = WallpaperManager.getInstance(mContext);        startRequestTask ();    }    private void startRequestTask (){        mRequestHandlerThread = new HandlerThread("HttpRequestTask");        mRequestHandlerThread.start();        mRequestHandler = new Handler(mRequestHandlerThread.getLooper()){            @Override            public void handleMessage(Message msg) {                switch (msg.what) {                    case Constant.Msg.REQUEST_LIVE_EPG:                        HashMap<Integer,Object> epgMap = (HashMap<Integer, Object>) msg.obj;                        String epg = (String) epgMap.get(Constant.Msg.REQUEST_LIVE_EPG);                        HttpActions.requestLiveEpgAction(mContext,epg);                        break;                    case Constant.Action.SAVE_PICTURE:                        HashMap<Integer,Object> pictureMap = (HashMap<Integer, Object>) msg.obj;                        Picture picture = (Picture) pictureMap.get( Constant.Action.SAVE_PICTURE);                        BitmapUtil.saveBitmap(picture.getBitmap(),picture.getDir(), picture.getName(),true);                        Bundle bundle = new Bundle();                        bundle.putInt(Constant.ACTION_KEY,Constant.Msg.SAVE_PICTURE_SUCCESS);                        DataObservable.getInstance().setData(bundle);                        break;                    case Constant.Action.SET_WALLPAPER:                        HashMap<Integer,Object> wallPaperMap = (HashMap<Integer, Object>) msg.obj;                        Bundle data = new Bundle();                        try {                            Picture info = (Picture) wallPaperMap.get( Constant.Action.SET_WALLPAPER);                            mWallpaperManager.setBitmap(info.getBitmap());                            data.putInt(Constant.ACTION_KEY,Constant.Msg.SET_WALLPAPER_SUCCESS);                        } catch (Exception e) {                            e.printStackTrace();                            data.putInt(Constant.ACTION_KEY,Constant.Msg.SET_WALLPAPER_FAILED);                        }                        DataObservable.getInstance().setData(data);                        break;                }            }        };    }    public void sendRequestWithParms (int Msg,Object requestData) {        HashMap<Integer, Object> requestMap = new HashMap<Integer, Object>();        requestMap.put(Msg, requestData);        Message msg = mRequestHandler.obtainMessage(Msg,requestMap);        mRequestHandler.sendMessage(msg);    }    public void sendRequest (int msg) {        mRequestHandler.sendEmptyMessage(msg);    }    public void onDestroy (){        if (mRequestHandlerThread != null) {            mRequestHandlerThread.quit();            mRequestHandlerThread = null;        }    }}