package com.zxn.mvvm.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.zxn.mvvm.presenter.BasePresenter;
import com.zxn.mvvm.presenter.CreatePresenter;
import com.zxn.mvvm.presenter.IView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zxn on 2019/3/28.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment /*SimpleImmersionFragment*/ implements IView {

    private String TAG = "BaseFragment";

    protected P mPresenter;
    protected View mRootView;
    private Unbinder mUnbinder;
    protected String mPageTitle = "";
    protected FragmentActivity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = getLayoutResId() <= 0
                    ? super.onCreateView(inflater, container, savedInstanceState)
                    : inflater.inflate(getLayoutResId(), container, false);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext =  getActivity();
//        isPrepared = true;
        if (usedEventBus())
            regEventBus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
        unRegEventBus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract int getLayoutResId();
    protected P createPresenter() {
        CreatePresenter annotation = getClass().getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null) {
            pClass = (Class<P>) annotation.value();
        }
        try {
            return pClass.newInstance();
        } catch (Exception e) {
//            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
            Log.i(TAG, "Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
            return null;
        }
    }


    public CharSequence getPageTitle() {
        return mPageTitle;
    }

    /**
     * 是否使用EventBus，如果需要使用子类重载此方法并返回true
     *
     * @return 使用启用
     */
    protected boolean usedEventBus() {
        return false;
    }

    protected void regEventBus() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    protected void unRegEventBus() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    public void showLoading() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.showLoading();
        }
    }

    @Override
    public void showLoading(String msg) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.showLoading(msg);
        }
    }

    @Override
    public void showLoading(int msgResId) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.showLoading(msgResId);
        }
    }

    @Override
    public void showLoading(boolean cancelable) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.showLoading(cancelable);
        }
    }

    @Override
    public void showLoading(String msg, boolean cancelable) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.showLoading(msg, cancelable);
        }
    }

    @Override
    public void closeLoading() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.closeLoading();
        }
    }

    @Override
    public void showToast(int msg) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.showToast(msg);
        }
    }

    @Override
    public void showToast(String msg) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.showToast(msg);
        }
    }

    /**
     * Call this when your activity is done and should be closed.  The
     * ActivityResult is propagated back to whoever launched you via
     * onActivityResult().
     */
    public void finish() {
        if (null != getActivity()) {
            getActivity().finish();
        }
    }

    //    @Override
//    public void onResume() {
//        super.onResume();
//        if (getUserVisibleHint()) {
//            setUserVisibleHint(true);
//        }
//    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (getUserVisibleHint()) {
//            isVisible = true;
//            lazyLoad();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }

//    /**
//     * 懒加载
//     */
//    protected void lazyLoad() {
//        if (!isPrepared || !isVisible || !isFirst) {
//            return;
//        }
//        initData();
//        isFirst = false;
//    }

//    /**
//     * fragment被设置为不可见时调用
//     */
//    protected abstract void onInvisible();


//    /**
//     * 这里获取数据，刷新界面，此方法执行在initView后面
//     */
//    protected abstract void initData();

    //    protected boolean isVisible;
//    private boolean isPrepared;//标志位，标志已经初始化完成
//    private boolean isFirst = true;

}
