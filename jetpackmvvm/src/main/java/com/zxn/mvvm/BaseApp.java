package com.zxn.mvvm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.zxn.utils.UIUtils;

/**
 * 应用需要继承此类.
 * Created by zxn on 2019/4/30.
 */
public class BaseApp extends Application {

    @SuppressLint("StaticFieldLeak")
    protected static Context mContext;

    /**
     * 开发模式
     *
     * @return false:线上模式,true:调试模式
     */
    public static boolean isDebug() {
        return TextUtils.equals(BuildConfig.BUILD_TYPE, "debug");
    }

    public static <T extends BaseApp> T getApplication() {
        return (T) mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        UIUtils.init(this);

        initDevelopMode();

        initLogConfig();

        initDbConfig();

    }

    /**
     * 初始化本地数据库配置
     */
    protected void initDbConfig() {

    }

    /**
     * 初始化开发环境.
     */
    protected void initDevelopMode() {
    }

    /**
     * 初始化日志配置.
     */
    protected void initLogConfig() {
    }

    public boolean isShowLog() {
        return isDebug();
//        return true;
    }

    public void showToast(int msg) {
        UIUtils.toast(UIUtils.getString(msg));
    }

    public void showToast(String msg) {
        UIUtils.toast(msg);
    }

}
