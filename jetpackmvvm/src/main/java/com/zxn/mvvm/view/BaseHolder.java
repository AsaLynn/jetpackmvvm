package com.zxn.mvvm.view;

import com.zxn.mvvm.presenter.IView;

/**
 * Created by zxn on 2019/4/2.
 */
public abstract class BaseHolder<T> extends ViewHolder<T> implements IView {

    protected BaseActivity mBaseActivity;
    protected BaseFragment mBaseFragment;

    public BaseHolder(BaseActivity activity) {
        super(activity);
        mBaseActivity = (BaseActivity) mContext;
    }

    public BaseHolder(BaseFragment baseFragment) {
        this((BaseActivity) baseFragment.getActivity());
        mBaseFragment = baseFragment;
    }

    @Override
    public void showLoading() {
        mBaseActivity.showLoading();
    }

    @Override
    public void showToast(int msg) {
        mBaseActivity.showToast(msg);
    }

    @Override
    public void showToast(String msg) {
        mBaseActivity.showToast(msg);
    }

    @Override
    public void showLoading(boolean cancelable) {
        mBaseActivity.showLoading(cancelable);
    }

    @Override
    public void showLoading(String msg, boolean cancelable) {
        mBaseActivity.showLoading(msg, cancelable);
    }

    @Override
    public void showLoading(String msg) {
        mBaseActivity.showLoading(msg);
    }

    @Override
    public void showLoading(int msgResId) {
        mBaseActivity.showLoading(msgResId);
    }

    @Override
    public void closeLoading() {
        mBaseActivity.closeLoading();
    }


}
