package com.shanian.libbase.api;

public interface ApiErrorHandler {
    /**
     * 处理错误回调实现
     *
     * @param throwable 异常
     */
    void handleError(Throwable throwable);

}
