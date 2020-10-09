package com.zxn.mvvm.model.http;

import java.util.Map;

public interface IHttpProcessor {
    //GET请求
    void get(String url, Map<String, Object> params, ICallBack callback);
    //POST请求
    void post(String url, Map<String, Object> params, ICallBack callback);
}
