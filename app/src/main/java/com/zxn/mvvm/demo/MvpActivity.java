package com.zxn.mvvm.demo;

import com.zxn.mvvm.view.BaseActivity;

/**
 * MvvmActivity
 * Created by zxn on 2019/4/8.
 */
public abstract  class MvpActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(boolean cancelable) {

    }

    @Override
    public void showLoading(String msg, boolean cancelable) {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoading(int msgResId) {

    }

    @Override
    public void closeLoading() {

    }
}
