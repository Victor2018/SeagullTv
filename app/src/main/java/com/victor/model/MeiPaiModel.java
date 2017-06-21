package com.victor.model;

import com.victor.presenter.OnMeiPaiListener;

/**
 * Created by victor on 2017/4/27.
 */
public interface MeiPaiModel {
    void loadMeiPai (String url,int currentPage,OnMeiPaiListener listener);
}
