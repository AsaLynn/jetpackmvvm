package com.zxn.mvvm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * 通用的viewholder
 * Created by zxn on 2019/4/2.
 */
public abstract class ViewHolder<T> {

    private ViewGroup mRoot = null;
    protected Context mContext;
    protected View mView;
    protected T mData;

    public ViewHolder(FragmentActivity activity) {
        mContext = activity;
        initView();
    }

    /**
     * @param fragment 所依附的acitivty必须是FragmentActivity
     */
    public ViewHolder(Fragment fragment) {
        this(fragment.getActivity());
    }

    private void initView() {
        if (getLayoutResId() == 0) {
            return;
        }
        mView = LayoutInflater.from(mContext).inflate(getLayoutResId(), mRoot, false);
        ButterKnife.bind(this, mView);
        onInitView(mView);
    }

    protected void onInitView(View rootView) {

    }

    public ViewHolder setVisibility(boolean visibility) {
        if (null != mView) {
            mView.setVisibility(visibility ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    protected abstract int getLayoutResId();

    public View getView() {
        return mView;
    }

    @Deprecated
    public void setData(T data) {
        mData = data;
    }

    public ViewHolder setHolderData(T data) {
        mData = data;
        return this;
    }
}
