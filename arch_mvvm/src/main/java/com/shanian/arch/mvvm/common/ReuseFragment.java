package com.shanian.arch.mvvm.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.shanian.arch.mvvm.R;
import com.shanian.arch.mvvm.base.BaseFragment;
import com.shanian.arch.mvvm.databinding.ReuseFragmentBinding;

import java.util.Random;

public class ReuseFragment extends BaseFragment {

    private ReuseViewModel reuseViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reuseViewModel = ViewModelProviders.of(getActivity()).get(ReuseViewModel.class);
        ReuseFragmentBinding commonFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.reuse_fragment, container, false);
        commonFragmentBinding.setNameViewModel(reuseViewModel);
        return commonFragmentBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getReuseViewModel().getReuseViewModel().postValue(new Random().nextInt() + "");
    }

    @Override
    public void initData() {

    }

    public ReuseViewModel getReuseViewModel() {
        return reuseViewModel;
    }
}
