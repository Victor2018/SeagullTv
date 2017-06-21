package com.victor.seagull.view;

import com.victor.model.data.FunnyData;

/**
 * Created by victor on 2017/4/19.
 */
public interface FunnyView {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void setFunnyInfo(FunnyData funnyData);
}
