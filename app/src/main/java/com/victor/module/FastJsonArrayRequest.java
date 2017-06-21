package com.victor.module;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @param <T>
 * Created by victor on 2017/2/8.
 */
public class FastJsonArrayRequest<T> extends Request<T> {
    private String TAG = "FastJsonArrayRequest";
    private final Listener<List<T>> mListener;
    private Class<T> mClass;
    private String url;

    public FastJsonArrayRequest(int method, String url, Class<T> clazz, Listener<List<T>> listener,
                                ErrorListener errorListener) {
        super(method, url, errorListener);
        this.url = url;
        mClass = clazz;
        mListener = listener;
    }

    public FastJsonArrayRequest(int method, String url, Listener<List<T>> listener,
                                ErrorListener errorListener) {
        super(method, url, errorListener);
        this.url = url;
        mListener = listener;
    }

    public FastJsonArrayRequest(String url, Class<T> clazz, Listener<List<T>> listener, ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    public FastJsonArrayRequest(String url, Listener<List<T>> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.url = url;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Log.e(TAG,"HttpHeaderParser.parseCharset(response.headers) = " + HttpHeaderParser.parseCharset(response.headers));
            String responseData = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.e(TAG,"url = " + url);
            Log.e(TAG,"responseData = " + responseData);
            return (Response<T>) Response.success(JSON.parseArray(responseData, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse((List<T>) response);
    }


}