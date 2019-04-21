package com.shanian.libbase.api;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EmptyObserver<T> implements Observer<T> {

    protected static final String TAG = "EmptyObserver";


    public EmptyObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "EmptyObserver.onSubscribe()");
        onBefore();
    }

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "EmptyObserver.onError() " + e.getMessage());
        if (e instanceof Exception) {
            onError(ExceptionHandle.handleException(e));
        } else {
            onError(new ExceptionHandle.ResponseThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    public void onError(ExceptionHandle.ResponseThrowable responseThrowable) {
        Log.i(TAG, "EmptyObserver.onError() " + responseThrowable.message);
        onFeed(responseThrowable.content);
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
        Log.i(TAG, "EmptyObserver.onComplete() ");
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

    protected void onFeed(String feed) {

    }
}
