package com.victor.util;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;

import com.victor.app.App;
import com.victor.seagull.R;

import java.lang.reflect.Field;

public class ResUtils {

    public static String getStringRes(int id) {
        try {
            return App.getContext().getResources().getString(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getStringRes(int id, Object... args) {
        try {
            return App.getContext().getResources().getString(id, args);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取 String[] 值. 如果id对应的资源文件不存在, 则返回 null.
     *
     * @param id
     * @return
     */
    public static String[] getStringArrayRes(int id) {
        try {
            return App.getContext().getResources().getStringArray(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取dimension px值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    public static int getDimenPixRes(int id) {
        try {
            return App.getContext().getResources().getDimensionPixelOffset(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取dimension float形式的 px值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    public static float getDimenFloatPixRes(int id) {
        try {
            return App.getContext().getResources().getDimension(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取 color 值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    @ColorInt
    public static int getColorRes(int id) {
        try {
            return ContextCompat.getColor(App.getContext(), id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取 Drawable 对象. 如果id对应的资源文件不存在, 则返回 null.
     *
     * @param id
     * @return
     */
    public static Drawable getDrawableRes(int id) {
        try {
            return ContextCompat.getDrawable(App.getContext(), id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取资源
     *
     * @return
     */
    public static Resources getResources() {
        return App.getContext().getResources();
    }

    public static int getDrawableByName(String name) {
        return getResources().getIdentifier(name, "drawable", App.getContext().getPackageName());
    }

    /**
     * 根据图片名字获取Id
     */
    public static int getDrawableId(String name) {
        try {
            Field field = R.drawable.class.getField(name);
            int res_ID = field.getInt(field.getName());
            return res_ID;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
