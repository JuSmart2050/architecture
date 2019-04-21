package com.shanian.arch.mvvm.common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReuseViewModel extends ViewModel {

    private MutableLiveData<String> reuseViewModel = new MutableLiveData<>();

    public void setReuseViewModel(MutableLiveData<String> reuseViewModel) {
        this.reuseViewModel = reuseViewModel;
    }

    public MutableLiveData<String> getReuseViewModel() {
        return reuseViewModel;
    }
}
