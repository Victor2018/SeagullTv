package com.victor.seagull.view;

import com.victor.model.data.FunnyData;

/**
 * Created by victor on 2017/4/19.
 */
public interface FunnyView <T> {
    void OnFunny(T data, String msg);
}
