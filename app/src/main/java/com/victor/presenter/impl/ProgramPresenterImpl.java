package com.victor.presenter.impl;

import com.android.volley.Request;
import com.victor.fragment.view.ProgramView;
import com.victor.http.annotation.HttpParms;
import com.victor.http.inject.HttpInject;
import com.victor.http.module.VolleyRequest;
import com.victor.http.presenter.impl.BasePresenterImpl;
import com.victor.model.data.ProgramData;

/**
 * Created by victor on 2017/4/19.
 */
public class ProgramPresenterImpl <H,T> extends BasePresenterImpl<H,T> {
    /*Presenter作为中间层，持有View和Model的引用*/
    private ProgramView programView;

    public ProgramPresenterImpl(ProgramView programView) {
        this.programView = programView;
    }

    @Override
    public void onSuccess(T data) {
        if (programView == null) return;
        if (data == null) {
            programView.OnProgram(null,"no data response");
        } else {
            programView.OnProgram(data,"");
        }
    }

    @Override
    public void onError(String error) {
        if (programView == null) return;
        programView.OnProgram(null,error);
    }

    @Override
    public void detachView() {
        programView = null;
    }

    @HttpParms(method = Request.Method.GET,bodyContentType = VolleyRequest.mJsonBodyContentType,responseCls = ProgramData.class)
    @Override
    public void sendRequest(String url,H header,T parm) {
        HttpInject.inject(this);
        super.sendRequest(url,header,parm);
    }
}
