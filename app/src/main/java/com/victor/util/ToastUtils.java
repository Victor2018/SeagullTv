package com.victor.util;

import android.support.annotation.IntegerRes;
import android.widget.Toast;

import com.victor.app.App;

/**
 * 吐司工具类
 */

public class ToastUtils {

    /**
     * 调试模式下可显示
     *
     * @param msg
     */
    public static void showDebug(String msg) {
        if (Constant.MODEL_DEBUG) {
            Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 调试模式下可显示
     *
     * @param resId
     */
    public static void showDebug(@IntegerRes int resId) {
        if (Constant.MODEL_DEBUG) {
            final String text = ResUtils.getStringRes(resId);
            Toast.makeText(App.getContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短暂显示
     *
     * @param msg
     */
    public static void showShort(CharSequence msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    public static void showShort(int resId) {
        final String text = ResUtils.getStringRes(resId);
        Toast.makeText(App.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示
     *
     * @param msg
     */
    public static void showLong(CharSequence msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    public static void showLong(int resId) {
        final String text = ResUtils.getStringRes(resId);
        Toast.makeText(App.getContext(), text, Toast.LENGTH_LONG).show();
    }

}
