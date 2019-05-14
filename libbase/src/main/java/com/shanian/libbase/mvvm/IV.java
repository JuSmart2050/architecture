package com.shanian.libbase.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;

public interface IV {

    ViewDataBinding getViewDataBinding();

    FragmentActivity getActivity();

    void onCreate(@Nullable Bundle savedInstanceState);

    void onAttachFragment();

    void onContentChanged();

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onPostCreate(@Nullable Bundle savedInstanceState);

    void onResume();

    void onPostResume();

    void onAttachedToWindow();

    boolean onCreateOptionsMenu(Menu menu);

    boolean onPrepareOptionsMenu(Menu menu);

    void onUserInteraction();

    void onUserLeaveHint();

    void onPause();

    void onStop();

    void onDestroy();
}
