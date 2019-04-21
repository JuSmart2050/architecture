package com.shanian.libbase.api.impl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Pair;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import io.reactivex.Observable;

public class ReopenService extends Service {

    public static final String PACKAGE_NAME = "packageName";
    public static final String PID = "pid";

    public ReopenService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String packageName = intent.getStringExtra(PACKAGE_NAME);
            int pid = intent.getIntExtra(PID, 0);
            Observable.just(new Pair<>(pid, packageName))
                    .delay(320, TimeUnit.MILLISECONDS)
                    .doOnNext(pair -> killProcess(pair.first))
                    .delay(80, TimeUnit.MILLISECONDS)
                    .doOnNext(pair -> reopen(pair.second))
                    .subscribe();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void killProcess(int pid) {
        if (pid != 0) {
            Process.killProcess(pid);
        }
    }

    public void reopen(String packageName) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
