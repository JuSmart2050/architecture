package com.shanian.libbase.api.interceptor;

import android.util.ArrayMap;
import android.util.Log;

import com.shanian.libbase.api.ApiClient;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private Map<String, String> headers;

    public HeaderInterceptor() {
        this.headers = new ArrayMap<>();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Map<String, String> headers = ApiClient.getHeaders();
        if (headers != null && headers.size() > 0) {
            for (ArrayMap.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        builder.addHeader("Content-Type", "application/json; charset=UTF-8")
                .build();
        Request request = builder.build();
        Log.i("request", request.toString());
        Log.i("request headers ", request.headers().toString());
        return chain.proceed(request);
    }

    @Override
    public String toString() {
        return headers != null ? headers.toString() : "";
    }
}
