package com.shanian.libbase.api;

import android.content.Context;
import android.widget.Toast;

public class FeedObserver<T> extends DefaultObserver<T> {

    public FeedObserver(Context context) {
        super(context);
    }

    @Override
    protected void onFeed(ExceptionHandle.ResponseThrowable responseThrowable) {
        super.onFeed(responseThrowable);
        Toast.makeText(context, responseThrowable.content, Toast.LENGTH_LONG).show();
    }
}
