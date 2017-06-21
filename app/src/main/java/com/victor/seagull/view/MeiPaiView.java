package com.victor.seagull.view;

import com.victor.model.data.MeiPaiData;

import java.util.List;

/**
 * Created by victor on 2017/4/27.
 */
public interface MeiPaiView {
    void showLoading ();
    void hideLoading ();
    void showError (String error);
    void setDatas (List<MeiPaiData> meiPaiDatas);
}
