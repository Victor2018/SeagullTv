package com.victor.presenter;

import com.victor.model.data.ProgramData;

/**
 * Created by victor on 2017/4/19.
 */
public interface OnProgramListener {
    void onSuccess(ProgramData programData);
    void onError(String error);
}
