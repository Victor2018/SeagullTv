package com.victor.model;

import com.victor.presenter.OnGankListener;

/**
 * Created by victor on 2017/5/9.
 */
public interface GankModel {
    void loadGank (int currentPage, OnGankListener listener);
}
