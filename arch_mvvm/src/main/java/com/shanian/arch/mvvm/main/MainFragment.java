package com.shanian.arch.mvvm.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.shanian.arch.mvvm.R;
import com.shanian.arch.mvvm.actordetail.ActorDetailFragment;
import com.shanian.arch.mvvm.actorlist.MovieComingFragment;
import com.shanian.arch.mvvm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment {

    private BottomNavigationBar bottomNavigationBar;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_activity, container, false);
        bottomNavigationBar = view.findViewById(R.id.design_bottom_sheet);
        viewPager = view.findViewById(R.id.viewPager);
        init();
        return view;

    }

    private void init() {
        List<Fragment> list = new ArrayList<>();
        list.add(new ActorDetailFragment());
        list.add(new MovieComingFragment());
        list.add(new ActorDetailFragment());
        list.add(new MovieComingFragment());
        list.add(new ActorDetailFragment());
        MainFragmentAdapter adapter = new MainFragmentAdapter(getFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(list.size());

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home_un_sel, "Home").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.home_un_sel, "Books").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.home_un_sel, "Music").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.home_un_sel, "Movies & TV").setActiveColorResource(R.color.brown))
                .addItem(new BottomNavigationItem(R.drawable.home_un_sel, "Games").setActiveColorResource(R.color.grey))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
