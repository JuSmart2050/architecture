package com.shanian.libbase.api.impl;

import android.util.ArrayMap;

import com.shanian.libbase.api.ApiProxy;
import com.shanian.libbase.api.ApiService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 把RequestBuilder 适配 成 apiProxy
 */
public class DefaultAdapter implements RequestBuilder.Adapter<Observable<ResponseBody>> {

    private ApiProxy apiProxy;

    public DefaultAdapter(ApiProxy apiProxy) {
        this.apiProxy = apiProxy;
    }

    @Override
    public Observable<ResponseBody> adapt(RequestBuilder<Observable<ResponseBody>> builder) {
        if (builder != null) {
            switch (builder.method) {
                case "post":
                    return doPost(builder);
                case "get":
                    return doGet(builder);
                default:
                    return Observable.never();

            }
        }
        return null;
    }

    private Observable<ResponseBody> doPost(RequestBuilder builder) {
        if (builder.body != null) {
            if (builder.headers == null) {
                builder.headers = new ArrayMap();
            }
            return apiProxy.create(ApiService.class).post(builder.url, builder.headers, builder.body);
        }
        if (builder.headers == null) {
            builder.headers = new ArrayMap();
        }
        if (builder.params == null) {
            builder.params = new ArrayMap();
        }
        return apiProxy.create(ApiService.class).post(builder.url, builder.headers, builder.params);
    }

    private Observable<ResponseBody> doGet(RequestBuilder builder) {
        if (builder.headers == null) {
            builder.headers = new ArrayMap();
        }
        if (builder.params == null) {
            builder.params = new ArrayMap();
        }
        return apiProxy.create(ApiService.class).get(builder.url, builder.headers, builder.params);
    }
}
