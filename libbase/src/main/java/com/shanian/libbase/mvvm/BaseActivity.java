package com.shanian.libbase.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseActivity<T extends V> extends AppCompatActivity {

    private T view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (view == null) {
            initV();
        }
        setContentView(view.getViewDataBinding().getRoot());
        view.onCreate(savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        view.onAttachFragment();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        view.onContentChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        view.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        view.onRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        view.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        view.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        view.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        view.onPostResume();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        view.onAttachedToWindow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return view.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return view.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        view.onUserInteraction();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        view.onUserLeaveHint();
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        view.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        view.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.onDestroy();
    }


    private void initV() {
        try {
            Type type = this.getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments();
                if (types != null && types.length > 0) {
                    Class cls = (Class) types[0];
                    view = (T) cls.newInstance();
                    view.init(this);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
