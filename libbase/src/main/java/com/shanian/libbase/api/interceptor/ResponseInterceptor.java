package com.shanian.libbase.api.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {

    private static final String TAG = "ResponseInterceptor";

    public ResponseInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().build();
        Response response = chain.proceed(request);
        Log.i("response", response.toString());
        Log.i("response headers ", response.headers().toString());
        return response;
    }
}
