package com.victor.data;import android.view.Gravity;import java.io.Serializable;import java.util.List;/** * Created by victor on 2015/12/25. */public class LiveCategory implements Serializable{    private int gravity = Gravity.CENTER_HORIZONTAL;    private String channel_category;    private List<Channel> channels;    public void setGravity(int gravity) {        this.gravity = gravity;    }    public void setChannel_category(String channel_category) {        this.channel_category = channel_category;    }    public void setChannels(List<Channel> channels) {        this.channels = channels;    }    public int getGravity() {        return gravity;    }    public String getChannel_category() {        return channel_category;    }    public List<Channel> getChannels() {        return channels;    }}