package com.victor.module;

import java.util.Observable;

/**
 * 观察者模式
 * Created by victor on 2016/1/20.
 * 定义对象间一种一对多的依赖关系，使得当每一个对象改变状态，则所有依赖于它的对象都会得到通知并自动更新。
 * 观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
 * 这个主题对象在状态上发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
 */
public class DataObservable extends Observable{
    private String TAG = "DataObservable";
    private static DataObservable mDataObservable;
    private Object data;

    public static DataObservable getInstance () {
        if (mDataObservable == null) {
            mDataObservable = new DataObservable();
        }
        return mDataObservable;
    }

    public void setData(Object data) {
        this.data = data;
        setChanged();
        //只有在setChanged()被调用后notifyObservers()才会去调用update()，否则什么都不干
        notifyObservers(data);
    }

    public Object getData() {
        return data;
    }
}
