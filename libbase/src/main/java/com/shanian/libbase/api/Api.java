package com.shanian.libbase.api;

import android.content.Context;
import android.util.ArrayMap;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import androidx.annotation.StringDef;
import retrofit2.Retrofit;

/**
 * Api.ConfigParams params = new Api.ConfigParams();
 * params.isDebug = true;
 * params.versionName = "5.0.0";
 * params.isOnline = false;
 * params.channel = "migu"
 * Api.init(params);
 */
public class Api {

    private static ReentrantLock lock = new ReentrantLock();
    protected static ConfigParams configParams;
    private static Map<String, ApiProxy> apiProxies = new ArrayMap<>();

    private Api() {
    }

    public static void init(ConfigParams params) {
        configParams = params;
    }

    public static ApiProxy provide(Class<? extends Config> configCls) {
        if (configCls == null) {
            throw new IllegalArgumentException("config class don't null.");
        }
        String cls = configCls.getCanonicalName();
        ApiProxy apiProxy = apiProxies.get(cls);
        if (apiProxy == null) {
            apiProxy = new ApiProxy(newRetrofit(configCls, configParams));
            lock.lock();
            apiProxies.put(cls, apiProxy);
            lock.unlock();
        }
        return apiProxy;
    }

    public static boolean isDebug() {
        if (configParams != null) {
            return configParams.isDebug;
        }
        return false;
    }

    private static Retrofit newRetrofit(Class<? extends Config> configCls, ConfigParams configParams) {
        Retrofit.Builder builder = new Retrofit.Builder();
        try {
            configCls.newInstance().build(builder, configParams);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    public static synchronized void clear() {
        apiProxies.clear();
    }

    /**
     * 网络请求配置
     */
    public static class ConfigParams {
        public Context context;
        public @Environment String environment;
        public boolean isDebug;
        public boolean isOnline;
        public String versionName;
        public String channel;
    }

    public interface Config {

        void build(Retrofit.Builder builder, ConfigParams configParams);
    }

    public static final String RELEASE = "release";
    public static final String DEV_RELEASE = "dev_release";
    public static final String DEV_TEST = "dev_test";
    public static final String DEV_CUSTOM = "dev_custom";

    @StringDef({RELEASE, DEV_RELEASE, DEV_TEST, DEV_CUSTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Environment{

    }

}
