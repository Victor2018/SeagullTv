package com.victor.presenter;

import com.victor.model.data.MeiPaiData;

import java.util.List;

/**
 * Created by victor on 2017/4/27.
 */
public interface OnMeiPaiListener {
    void onSuccess(List<MeiPaiData> meiPaiDatas);
    void onError(String error);
}
