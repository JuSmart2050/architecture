package com.shanian.arch.mvvm.actorlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shanian.arch.mvvm.Repository;
import com.shanian.arch.mvvm.bean.MovieComingBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MovieComingViewModel extends ViewModel {

    private MutableLiveData<MovieComingBean> movieComingViewModel = new MutableLiveData<>();

    public void setMovieComingViewModel(MutableLiveData<MovieComingBean> movieComingViewModel) {
        this.movieComingViewModel = movieComingViewModel;
    }

    public MutableLiveData<MovieComingBean> getMovieComingViewModel() {
        return movieComingViewModel;
    }

    public void fetchMovieComingViewModel() {
        Repository.fetchMovieComings()
                .subscribe(new Observer<MovieComingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieComingBean value) {
                        movieComingViewModel.postValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
