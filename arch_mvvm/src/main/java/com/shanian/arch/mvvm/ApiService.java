package com.shanian.arch.mvvm;

import com.shanian.arch.mvvm.bean.ActorDetailBean;
import com.shanian.arch.mvvm.bean.MovieComingBean;
import com.shanian.libbase.api.ApiConfig;
import com.shanian.libbase.api.impl.DefaultConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;

@ApiConfig()
public interface ApiService {

    @GET("movie/celebrity/actorId")
    Observable<ActorDetailBean> fetchActorDetail();

    @GET("v2/movie/coming")
    Observable<MovieComingBean> fetchMovieComings();
}
