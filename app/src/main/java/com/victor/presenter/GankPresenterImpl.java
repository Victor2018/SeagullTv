package com.victor.presenter;

import com.android.volley.Request;
import com.victor.http.annotation.HttpParms;
import com.victor.http.inject.HttpInject;
import com.victor.http.module.VolleyRequest;
import com.victor.http.presenter.impl.BasePresenterImpl;
import com.victor.data.GankData;
import com.victor.seagull.view.GankView;

/**
 * Created by victor on 2017/5/9.
 */
public class GankPresenterImpl <H,T> extends BasePresenterImpl<H,T> {
    /*Presenter作为中间层，持有View和Model的引用*/
    private GankView gankView;

    public GankPresenterImpl (GankView gankView) {
        this.gankView = gankView;
    }

    @Override
    public void onSuccess(T data) {
        if (gankView == null) return;
        if (data == null) {
            gankView.OnGank(null,"no data response");
        } else {
            gankView.OnGank(data,"");
        }
    }

    @Override
    public void onError(String error) {
        if (gankView == null) return;
        gankView.OnGank(null,error);
    }

    @Override
    public void detachView() {
        gankView = null;
    }

    @HttpParms(method = Request.Method.GET,bodyContentType = VolleyRequest.mJsonBodyContentType,responseCls = GankData.class)
    @Override
    public void sendRequest(String url,H header,T parm) {
        HttpInject.inject(this);
        super.sendRequest(url,header,parm);
    }
}
