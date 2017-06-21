package com.victor.presenter.impl;

import com.victor.fragment.view.ProgramView;
import com.victor.model.ProgramModel;
import com.victor.model.data.ProgramData;
import com.victor.model.impl.ProgramModelImpl;
import com.victor.presenter.OnProgramListener;
import com.victor.presenter.ProgramPresenter;

/**
 * Created by victor on 2017/4/19.
 */
public class ProgramPresenterImpl implements ProgramPresenter,OnProgramListener {
    /*Presenter作为中间层，持有View和Model的引用*/
    private ProgramView programView;
    private ProgramModel programModel;

    public ProgramPresenterImpl(ProgramView programView) {
        this.programView = programView;
        programModel = new ProgramModelImpl();
    }
    @Override
    public void getDatas() {
        programView.showLoading();
        programModel.loadProgram(this);
    }

    @Override
    public void onSuccess(ProgramData programData) {
        programView.hideLoading();
        programView.setDatas(programData);
    }

    @Override
    public void onError(String error) {
        programView.hideLoading();
        programView.showError(error);
    }
}
