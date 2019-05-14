package com.shanian.libbase.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;

public abstract class V implements IV {

    protected FragmentActivity activity;
    protected ViewDataBinding viewDataBinding;
    protected LayoutInflater inflater;

    public void init(FragmentActivity activity) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false);
        viewDataBinding.setLifecycleOwner(activity);
        initViewDataBinding(viewDataBinding);
    }

    protected abstract void initViewDataBinding(ViewDataBinding viewDataBinding);

    public abstract int getLayoutId();

    @Override
    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    @Override
    public FragmentActivity getActivity() {
        return activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onAttachFragment() {

    }

    @Override
    public void onContentChanged() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPostResume() {

    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onUserInteraction() {

    }

    @Override
    public void onUserLeaveHint() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
