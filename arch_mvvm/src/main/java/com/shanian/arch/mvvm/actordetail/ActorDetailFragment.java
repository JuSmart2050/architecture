package com.shanian.arch.mvvm.actordetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.shanian.arch.mvvm.R;
import com.shanian.arch.mvvm.base.BaseFragment;
import com.shanian.arch.mvvm.databinding.ActorDetailFragmentBinding;

public class ActorDetailFragment extends BaseFragment {

    private ActorDetailViewModel actorDetailViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        actorDetailViewModel = ViewModelProviders.of(getActivity()).get(ActorDetailViewModel.class);
        View view = inflater.inflate(R.layout.actor_detail_fragment, container, false);
        ActorDetailFragmentBinding actorDetailFragmentBinding = ActorDetailFragmentBinding.bind(view);
        actorDetailFragmentBinding.setActorDetail(actorDetailViewModel);
        actorDetailFragmentBinding.setLifecycleOwner(getActivity());
        //TODO 开始获取接口数据
        actorDetailViewModel.getActorDetail();
        return view;
    }
}
