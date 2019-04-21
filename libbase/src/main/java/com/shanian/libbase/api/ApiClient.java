package com.shanian.libbase.api;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.ArrayMap;
import android.widget.Toast;

import com.shanian.libbase.api.impl.DefaultAdapter;
import com.shanian.libbase.api.impl.DefaultConfig;
import com.shanian.libbase.api.impl.ReopenService;
import com.shanian.libbase.api.impl.RequestBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.ResponseBody;

/**
 * <p>
 * 网络请求统一入口
 * 1.Charles 抓包
 * 2.chrome://inspect/  请求日志查看（需要梯子）
 * 3.logcat 日志
 * </p>
 *
 * @author : zhuyingming
 * @date : 2018/12/29
 */
public class ApiClient {

    private static boolean isDebug;
    private volatile static ApiClient instance;
    private List<ApiErrorHandler> apiErrorList;
    private static ArrayMap<String, String> headers;

    private static ApiClient getInstance() {
        if (instance == null) {
            synchronized (ApiClient.class) {
                if (instance == null) {
                    instance = new ApiClient();
                }
            }
        }
        return instance;
    }

    private ApiClient() {
        apiErrorList = new ArrayList<>();
        headers = new ArrayMap<>();
        RxJavaPlugins.setErrorHandler(throwable -> {
            if (apiErrorList != null && apiErrorList.size() > 0) {
                for (ApiErrorHandler apiErrorHandler : apiErrorList) {
                    apiErrorHandler.handleError(throwable);
                }
            }
        });
    }

    /**
     * 添加通用Api错误处理
     *
     * @param errorHandler 错误回调
     */
    public ApiClient addErrorHandler(ApiErrorHandler errorHandler) {
        instance.apiErrorList.add(errorHandler);
        return instance;
    }

    /**
     * 在Application中初始化
     * <code>
     * Api.ConfigParams params = new Api.ConfigParams();
     * params.context = this;
     * params.isDebug = true;
     * params.isOnline = false;
     * params.channel = "vivo";
     * params.versionName = "1.0.0";
     * ApiClient.initApi(params);
     * </code>
     *
     * @param configParams
     * @return
     */
    public static ApiClient init(Api.ConfigParams configParams) {
        isDebug = configParams.isDebug;
        Api.init(configParams);
        return getInstance();
    }

    public static <T> T create(Class<T> service) {
        if (service == null) {
            throw new IllegalArgumentException("service can't be null...");
        }
        ApiConfig annotation = service.getAnnotation(ApiConfig.class);
        if (annotation != null) {
            Class<? extends Api.Config> config = annotation.value();
            return Api.provide(config).create(service);
        }
        return Api.provide(DefaultConfig.class).create(service);
    }


    public static void addHeader(String key, String value) {
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        headers.put(key, value);
    }

    public static void addHeaders(Map<String, String> header) {
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        headers.putAll(header);
    }

    public static ArrayMap<String, String> getHeaders() {
        return headers;
    }

    /*********************************对老版本兼容**************************************/
    /**
     * <code>
     * ApiClient.get("/live/v2/tv-data/{id}")
     * .method("get")
     * .path("id", "70002091")
     * .build()
     * .observeOn(AndroidSchedulers.mainThread())
     * .subscribe(responseBody -> {
     * Log.i(TAG, responseBody.toString());
     * });
     * </code>
     *
     * @param url
     * @return
     */
    public static RequestBuilder<Observable<ResponseBody>> get(String url) {
        return get(url, DefaultConfig.class);
    }

    /**
     * <code>
     * ApiClient.get("/live/v2/tv-data/{id}", MiGuVideoConfig.class)
     * .method("get")
     * .path("id", "70002091")
     * .build()
     * .observeOn(AndroidSchedulers.mainThread())
     * .subscribe(responseBody -> {
     * Log.i(TAG, responseBody.toString());
     * });
     * </code>
     *
     * @param url
     * @return
     */
    public static RequestBuilder<Observable<ResponseBody>> get(String url, Class<? extends Api.Config> config) {
        ApiProxy apiProxy = Api.provide(config);
        DefaultAdapter adapter = new DefaultAdapter(apiProxy);
        return apiProxy.newRequestBuilder(adapter)
                .url(url)
                .method("get");
    }

    /**
     * <code>
     * ApiClient.post("/live/v2/tv-data/{id}")
     * .method("post")
     * .path("id", "70002091")
     * .build()
     * .observeOn(AndroidSchedulers.mainThread())
     * .subscribe(responseBody -> {
     * Log.i(TAG, responseBody.toString());
     * });
     * </code>
     *
     * @param url
     * @return
     */
    public static RequestBuilder<Observable<ResponseBody>> post(String url) {
        return post(url, DefaultConfig.class);
    }

    /**
     * <code>
     * ApiClient.post("/live/v2/tv-data/{id}", MiGuVideoConfig.class)
     * .method("post")
     * .path("id", "70002091")
     * .build()
     * .observeOn(AndroidSchedulers.mainThread())
     * .subscribe(responseBody -> {
     * Log.i(TAG, responseBody.toString());
     * });
     * </code>
     *
     * @param url
     * @return
     */
    public static RequestBuilder<Observable<ResponseBody>> post(String url, Class<? extends Api.Config> config) {
        ApiProxy apiProxy = Api.provide(config == null ? DefaultConfig.class : config);
        DefaultAdapter adapter = new DefaultAdapter(apiProxy);
        return apiProxy.newRequestBuilder(adapter)
                .url(url)
                .method("post");
    }

    /**
     * 切换网络配置
     */
    public static void switchDebug(Context context, @Api.Environment String environment) {
        String msg = "";
        if (Api.RELEASE.equals(environment)) {
            msg = "生产环境";
        } else if (Api.DEV_RELEASE.equals(environment)) {
            msg = "测试环境";
        } else if (Api.DEV_TEST.equals(environment)) {
            msg = "开发环境";
        } else if (Api.DEV_CUSTOM.equals(environment)) {
            msg = "自定义环境";
        }
        Toast.makeText(context, "已切换至" + msg + "，重启中...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ReopenService.class);
        intent.putExtra(ReopenService.PACKAGE_NAME, context.getPackageName());
        intent.putExtra(ReopenService.PID, Process.myPid());
        context.startService(intent);
    }
}
