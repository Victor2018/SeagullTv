package com.victor.app;import android.app.Application;import com.victor.http.module.VolleyRequest;/** * Created by victor on 2017/4/26. */public class App extends Application{    private static App instance;    public App() {        instance = this;    }    public static App getContext() {        return instance;    }    @Override    public void onCreate() {        super.onCreate();        //初始化        VolleyRequest.buildRequestQueue(this);    }}