package com.shanian.arch.v.mvvm.v.packa;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.shanian.arch.v.mvvm.R;
import com.shanian.arch.v.mvvm.databinding.PackABinding;
import com.shanian.libbase.view.V;

public class PackA extends V {


    private PackABinding packABinding;
    private PackAViewModel packAViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.pack_a;
    }

    @Override
    protected void initViewDataBinding(ViewDataBinding viewDataBinding) {
        packABinding = (PackABinding) viewDataBinding;
        packAViewModel = ViewModelProviders.of(getActivity()).get(PackAViewModel.class);
        packABinding.setViewModel(packAViewModel);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packAViewModel.getData();
    }

}
