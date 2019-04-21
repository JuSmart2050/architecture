package com.shanian.libbase.api;

import android.content.Context;
import android.text.TextUtils;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

public abstract class BaseConfig implements Api.Config {

    protected @Api.Environment String environment;
    protected boolean isDebug;
    protected Context context;

    @Override
    public void build(Retrofit.Builder builder, Api.ConfigParams configParams) {
        if (configParams != null) {
            environment = configParams.environment;
            isDebug = configParams.isDebug;
            context = configParams.context.getApplicationContext();
        }
        if (!TextUtils.isEmpty(getHttpUrlStr())) {
            builder.baseUrl(getHttpUrlStr());
        } else {
            builder.baseUrl(getHttpUrl());
        }
        builder.client(getOkHttpClient())
                .addCallAdapterFactory(getCallAdapterFactory())
                .addConverterFactory(getConvertFactory());
    }

    /**
     * @return
     */
    protected HttpUrl getHttpUrl() {
        return null;
    }

    protected String getHttpUrlStr() {
        return "";
    }

    protected abstract CallAdapter.Factory getCallAdapterFactory();

    protected abstract Converter.Factory getConvertFactory();

    protected abstract OkHttpClient.Builder okHttpClientBuild(OkHttpClient.Builder builder);


    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder = okHttpClientBuild(builder);
        return builder.build();
    }


}
