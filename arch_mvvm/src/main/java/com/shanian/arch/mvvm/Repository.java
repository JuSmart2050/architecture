package com.shanian.arch.mvvm;

import com.shanian.arch.mvvm.bean.ActorDetailBean;
import com.shanian.arch.mvvm.bean.MovieComingBean;
import com.shanian.libbase.api.ApiClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    public static Observable<ActorDetailBean> fetchActorDetail() {
        return ApiClient.create(ApiService.class)
                .fetchActorDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<MovieComingBean> fetchMovieComings() {
        return ApiClient.create(ApiService.class)
                .fetchMovieComings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
