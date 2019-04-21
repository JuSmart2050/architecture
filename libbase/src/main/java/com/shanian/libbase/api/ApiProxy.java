package com.shanian.libbase.api;

import android.util.ArrayMap;

import com.shanian.libbase.api.impl.RequestBuilder;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Retrofit;

public class ApiProxy {

    private ReentrantLock lock = new ReentrantLock();
    private Retrofit retrofit;
    private Map<Class, Object> apiCache;

    public ApiProxy(Retrofit retrofit) {
        this.retrofit = retrofit;
        apiCache = new ArrayMap<>();
    }

    public <T> T create(Class<T> apiCls) {
        if (apiCls == null) {
            throw new IllegalArgumentException("api service don't null");
        }
        Object object = apiCache.get(apiCls);
        if (object == null) {
            lock.lock();
            object = retrofit.create(apiCls);
            apiCache.put(apiCls, object);
            lock.unlock();
        }
        return (T) object;
    }

    public <T> RequestBuilder<T> newRequestBuilder(RequestBuilder.Adapter<T> adapter) {
        return new RequestBuilder<>(adapter);
    }

}
