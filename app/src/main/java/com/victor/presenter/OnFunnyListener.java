package com.victor.presenter;

import com.victor.model.data.FunnyData;

/**
 * Created by victor on 2017/4/19.
 */
public interface OnFunnyListener {
    void onSuccess(FunnyData funnyData);
    void onError(String error);
}
