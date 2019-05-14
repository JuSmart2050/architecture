package com.shanian.arch.v.mvvm.v.packa;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PackAViewModel extends ViewModel {

    private final MutableLiveData<String> button1 = new MutableLiveData<>();

    private final MutableLiveData<String> button2 = new MutableLiveData<>();

    public MutableLiveData<String> getButton1() {
        return button1;
    }
    public MutableLiveData<String> getButton2() {
        return button2;
    }

    public void getData() {
        button1.postValue("zhuyingming");
        button2.postValue("migu");
    }
}
