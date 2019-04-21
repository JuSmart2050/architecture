package com.shanian.libbase.api.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shanian.libbase.api.Api;
import com.shanian.libbase.api.BaseConfig;
import com.shanian.libbase.api.adapter.DoubleDefault0Adapter;
import com.shanian.libbase.api.adapter.IntegerDefault0Adapter;
import com.shanian.libbase.api.adapter.LongDefault0Adapter;
import com.shanian.libbase.api.interceptor.HeaderInterceptor;
import com.shanian.libbase.api.interceptor.MockResponseInterceptor;
import com.shanian.libbase.api.interceptor.ResponseInterceptor;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultConfig extends BaseConfig {

    @Override
    protected CallAdapter.Factory getCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Override
    protected Converter.Factory getConvertFactory() {
        final Gson customGsonInstance = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .create();
        return GsonConverterFactory.create(customGsonInstance);
    }

    @Override
    protected OkHttpClient.Builder okHttpClientBuild(OkHttpClient.Builder builder) {
        builder
                // 虽然没有设置缓存时间，但是设置了这个大小还是会导致一些数据一直不能得到刷新的问题（影院影片场次）
//                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
//                .sslSocketFactory(MGLiveHttpsUtil.getSslSocketFactory(context).sSLSocketFactory)
                .hostnameVerifier(getHostnameVerifier());
        addInterceptors(builder);
        addNetworkInterceptor(builder);
        return builder;
    }

    @Override
    protected HttpUrl getHttpUrl() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        if (isDebug) {
            builder.scheme("http")
                    .host("183.192.162.209")
                    .port(10080);
        } else {
            builder.scheme("http")
                    .host("183.192.162.209")
                    .port(10080);
        }
        return builder.build();
    }

    protected void appendNetworkInterceptors(List<Interceptor> interceptors) {
    }

    protected void appendInterceptors(List<Interceptor> interceptors) {
    }

    private List<Interceptor> getInterceptors() {
        List<Interceptor> interceptors = new ArrayList<>();
        if (isDebug || !Api.RELEASE.equals(environment)) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(getLoggerManager())
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
            interceptors.add(httpLoggingInterceptor);
            interceptors.add(new MockResponseInterceptor());
        }
        interceptors.add(new HeaderInterceptor());
        appendInterceptors(interceptors);
        return interceptors;
    }

    private List<Interceptor> getNetworkInterceptors() {
        List<Interceptor> interceptors = new ArrayList<>();
        appendNetworkInterceptors(interceptors);
        return interceptors;
    }


    private void addInterceptors(OkHttpClient.Builder builder) {
        List<Interceptor> list = getInterceptors();
        if (list != null) {
            for (Interceptor interceptor : list) {
                builder.addInterceptor(interceptor);
            }
        }
    }

    private void addNetworkInterceptor(OkHttpClient.Builder builder) {
        builder.addNetworkInterceptor(new ResponseInterceptor());
        List<Interceptor> list = getNetworkInterceptors();
        if (list != null) {
            for (Interceptor interceptor : list) {
                builder.addNetworkInterceptor(interceptor);
            }
        }
    }

    /***
     * 打印请求信息
     * @return
     */
    private HttpLoggingInterceptor.Logger getLoggerManager() {
        HttpLoggingInterceptor.Logger logger = message -> {
//                Log.i("response", message);
            final int chunkSize = 2048;
            for (int i = 0; i < message.length(); i += chunkSize) {
                Log.d("response", message.substring(i, Math.min(message.length(), i + chunkSize)));
            }
        };
        return logger;
    }


    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    private SSLSocketFactory getSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }
}
