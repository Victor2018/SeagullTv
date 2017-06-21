package com.victor.presenter.impl;

import com.victor.model.GankModel;
import com.victor.model.data.GankData;
import com.victor.model.impl.GankModelImpl;
import com.victor.presenter.GankPresenter;
import com.victor.presenter.OnGankListener;
import com.victor.seagull.view.GankView;

/**
 * Created by victor on 2017/5/9.
 */
public class GankPresenterImpl implements GankPresenter,OnGankListener {
    /*Presenter作为中间层，持有View和Model的引用*/
    private GankView gankView;
    private GankModel gankModel;

    public GankPresenterImpl (GankView gankView) {
        this.gankView = gankView;
        gankModel = new GankModelImpl();
    }

    @Override
    public void getGankDatas(int currentPage) {
        gankView.showLoading();
        gankModel.loadGank(currentPage,this);
    }

    @Override
    public void onSuccess(GankData data) {
        gankView.hideLoading();
        gankView.setGankDatas(data);
    }

    @Override
    public void onError(String error) {
        gankView.hideLoading();
        gankView.showError(error);
    }
}
