package com.shanian.libbase.api.impl;

import android.util.ArrayMap;

import java.util.Map;
import java.util.Set;

public class RequestBuilder<T> {

    public String url;
    public String method;
    public Map<String, String> paths;
    public Map<String, String> headers;
    public Map<String, String> params;
    public Object body;
    public Adapter<T> adapter;


    public RequestBuilder(Adapter<T> adapter) {
        this.adapter = adapter;
    }

    public RequestBuilder<T> url(String url) {
        this.url = url;
        return this;
    }

    public RequestBuilder<T> method(String method) {
        this.method = method;
        return this;
    }

    public RequestBuilder<T> path(String path, String value) {
        if (paths == null) {
            paths = new ArrayMap<>();
        }
        this.paths.put(path, value);
        return this;
    }

    public RequestBuilder<T> header(String key, String value) {
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        headers.put(key, value);
        return this;
    }

    public RequestBuilder<T> headers(Map<String, String> headers) {
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        headers.putAll(headers);
        return this;
    }

    public RequestBuilder<T> param(String key, String value) {
        if (params == null) {
            params = new ArrayMap<>();
        }
        params.put(key, value);
        return this;
    }

    public RequestBuilder<T> params(Map<String, String> params) {
        if (params == null) {
            params = new ArrayMap<>();
        }
        params.putAll(headers);
        return this;
    }

    public RequestBuilder<T> body(Object body) {
        this.body = body;
        return this;
    }

    private void checkPath() {
        if (paths != null) {
            Set<Map.Entry<String, String>> set = paths.entrySet();
            for (Map.Entry<String, String> path : set) {
                url = url.replace("{" + path.getKey() + "}", path.getValue());
            }
        }
    }

    public T build() {
        checkPath();
        return adapter.adapt(this);
    }

    public interface Adapter<T> {
        T adapt(RequestBuilder<T> builder);
    }
}
