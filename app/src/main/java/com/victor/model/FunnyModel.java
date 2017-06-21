package com.victor.model;

import com.victor.presenter.OnFunnyListener;

/**
 * Created by victor on 2017/4/19.
 */
public interface FunnyModel {
    void loadFunny(String url, int currentPage, String time, OnFunnyListener listener);
}
