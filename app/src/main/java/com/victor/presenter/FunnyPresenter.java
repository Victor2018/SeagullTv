package com.victor.presenter;

/**
 * Created by victor on 2017/4/19.
 */
public interface FunnyPresenter {

    /**
     * @param currentPage
     * @param time yyyyMMddHHmmss
     */
    void getFunny(String url, int currentPage, String time);
}
