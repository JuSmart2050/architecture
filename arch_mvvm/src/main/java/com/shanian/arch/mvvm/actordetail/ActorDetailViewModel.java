package com.shanian.arch.mvvm.actordetail;

import com.shanian.arch.mvvm.Repository;
import com.shanian.arch.mvvm.bean.ActorDetailBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ActorDetailViewModel extends ViewModel {


    private MutableLiveData<ActorDetailBean> actorDetailLiveData = new MutableLiveData<>();

    public MutableLiveData<ActorDetailBean> getActorDetailLiveData() {
        return actorDetailLiveData;
    }

    public void setActorDetailLiveData(MutableLiveData<ActorDetailBean> actorDetailLiveData) {
        this.actorDetailLiveData = actorDetailLiveData;
    }

    public ActorDetailViewModel() {
    }

    public void getActorDetail() {
        Repository.fetchActorDetail()
                .subscribe(new Observer<ActorDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ActorDetailBean value) {
                        actorDetailLiveData.setValue(value);
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
