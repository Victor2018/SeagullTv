package com.victor.data;import android.graphics.Bitmap;import java.io.Serializable;/** * Created by victor on 2017/5/10. */public class Picture implements Serializable{    private String dir;    private String name;    private String imgUrl;    private Bitmap bitmap;    public String getDir() {        return dir;    }    public String getName() {        return name;    }    public String getImgUrl() {        return imgUrl;    }    public Bitmap getBitmap() {        return bitmap;    }    public void setDir(String dir) {        this.dir = dir;    }    public void setName(String name) {        this.name = name;    }    public void setImgUrl(String imgUrl) {        this.imgUrl = imgUrl;    }    public void setBitmap(Bitmap bitmap) {        this.bitmap = bitmap;    }}