package com.zxn.mvvm.view;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zxn.mvvm.presenter.BasePresenter;
import com.zxn.mvvm.presenter.CreatePresenter;
import com.zxn.mvvm.presenter.IView;
import com.zxn.mvvm.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zxn on 2019/1/23.
 */
public abstract class BaseDialogFragment<P extends BasePresenter> extends DialogFragment implements IView {
    protected P mPresenter;
    protected View mRootView;
    private Unbinder mUnbinder;
    protected FragmentActivity mContext;
    protected String TAG = this.getClass().getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (createPresenter() != null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }
        setStyle(DialogFragment.STYLE_NO_TITLE, initTheme());
    }

    /**
     * 重写此方法可以更换主题
     *
     * @return
     */
    protected int initTheme() {
        //return R.style.BaseDialog_FullScreen;
        return R.style.BaseDialog_Nice;
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        onDialogCreated(params, dialog);
        window.setAttributes(params);
        return dialog;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }

    protected P createPresenter() {
        CreatePresenter annotation = getClass().getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null) {
            pClass = (Class<P>) annotation.value();
        }
        try {
            return pClass.newInstance();
        } catch (Exception e) {
            Log.i(TAG, "Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
            return null;
        }
    }

    protected abstract int getLayoutResId();

    protected void onDialogCreated(WindowManager.LayoutParams params, Dialog dialog) {
        /*dialog.setCancelable(true);
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;*/
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }

    public void showNow(FragmentManager manager) {
        super.showNow(manager, TAG);
    }

    public int show(FragmentTransaction transaction) {
        return super.show(transaction, TAG);
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

    @Override
    public void closeLoading() {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            activity.closeLoading();
        }
    }

    public interface OnDialogClickListener<V extends View> {
        void onConfirmClick(V view);
    }
}
