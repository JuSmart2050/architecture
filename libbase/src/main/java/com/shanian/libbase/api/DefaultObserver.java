package com.shanian.libbase.api;

import android.content.Context;
import android.util.Log;

import com.shanian.libbase.utils.NetworkUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DefaultObserver<T> implements Observer<T> {

    protected static final String TAG = "DefaultObserver";

    protected Context context;

    public DefaultObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "DefaultObserver.onSubscribe()");
        if (!NetworkUtils.isAvailable(context)) {
            ExceptionHandle.ResponseThrowable responseThrowable = new ExceptionHandle.ResponseThrowable(new Throwable("网络异常，请稍后重试~"), 400);
            responseThrowable.content = "网络异常，请稍后重试~";
            responseThrowable.message = "网络异常，请稍后重试~";
            onFeed(responseThrowable);
            if (!d.isDisposed()) {
                d.dispose();
            }
            onFail();
            return;
        }
        onBefore();
    }

    @Override
    public void onNext(T value) {
        Log.i(TAG, "DefaultObserver.onNext() ");
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "DefaultObserver.onError() " + e.getMessage());
        if (e instanceof Exception) {
            onError(ExceptionHandle.handleException(e));
        } else {
            onError(new ExceptionHandle.ResponseThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    public void onError(ExceptionHandle.ResponseThrowable responseThrowable) {
        Log.i(TAG, "DefaultObserver.onError() " + responseThrowable.message);
        onFeed(responseThrowable);
        onFail();
    }

    /**
     * 事件开始之前执行
     */
    public void onBefore() {

    }

    /**
     * 事件成功后，最后执行方法
     */
    @Override
    public void onComplete() {
        Log.i(TAG, "DefaultObserver.onComplete() ");
        onFinally();
    }

    /**
     * 事件失败后，最终执行该方法
     */
    public void onFail() {
        onFinally();
    }

    /**
     * 事件结束执行该方法
     */
    public void onFinally() {

    }

    protected void onFeed(ExceptionHandle.ResponseThrowable responseThrowable) {

    }
}
