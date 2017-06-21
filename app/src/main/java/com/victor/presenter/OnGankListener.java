package com.victor.presenter;

import com.victor.model.data.GankData;

/**
 * Created by victor on 2017/5/9.
 */
public interface OnGankListener {
    void onSuccess (GankData data);
    void onError (String error);
}
