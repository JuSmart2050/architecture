package com.shanian.jetpack;

import android.app.Application;

import com.shanian.libbase.api.Api;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Api.ConfigParams configParams = new Api.ConfigParams();
        configParams.isDebug = true;
        configParams.environment = Api.DEV_CUSTOM;
        configParams.context = this;
        Api.init(configParams);
    }
}
