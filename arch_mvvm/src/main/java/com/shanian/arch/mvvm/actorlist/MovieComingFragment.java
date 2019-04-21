package com.shanian.arch.mvvm.actorlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shanian.arch.mvvm.R;
import com.shanian.arch.mvvm.actordetail.ActorDetailFragment;
import com.shanian.arch.mvvm.base.BaseFragment;
import com.shanian.arch.mvvm.bean.EntriesBean;
import com.shanian.arch.mvvm.databinding.MovieComingFragmentBinding;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MovieComingFragment extends BaseFragment {

    private Items items = new Items();
    private MovieComingViewModel movieComingViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        movieComingViewModel = ViewModelProviders.of(getActivity()).get(MovieComingViewModel.class);

        MovieComingFragmentBinding movieComingFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.movie_coming_fragment, container, false);
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.setItems(items);
        adapter.register(EntriesBean.class, new MovieComingItemBinder());
        movieComingFragmentBinding.recyclerView.setAdapter(adapter);
        movieComingFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        movieComingViewModel.getMovieComingViewModel().observe(getActivity(), objects -> {
            items.addAll(objects.getEntries());
            adapter.notifyDataSetChanged();
        });

        initActorDetailFragment();

        initData();
        return movieComingFragmentBinding.getRoot();
    }

    @Override
    public void initData() {
        movieComingViewModel.fetchMovieComingViewModel();
    }

    private void initActorDetailFragment() {
        ActorDetailFragment actorDetailFragment = new ActorDetailFragment();
        replaceFragmentInActivity(getActivity().getSupportFragmentManager(), actorDetailFragment, R.id.actor_detail_fragment);
    }
}
