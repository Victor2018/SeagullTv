package com.victor.presenter;import com.victor.model.data.ChannelData;import com.victor.model.data.ProgramData;/** * Created by victor on 2017/4/26. */public interface OnChannelListener {    void onSuccess(ChannelData channelData);    void onError(String error);}