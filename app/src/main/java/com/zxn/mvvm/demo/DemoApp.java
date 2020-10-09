package com.zxn.mvvm.demo;

import android.text.TextUtils;

import com.zxn.mvvm.BaseApp;
import com.zxn.utils.UIUtils;

/**
 * Created by zxn on 2020/4/14.
 */
public class DemoApp extends BaseApp {

    public static final DemoApp getApp() {
        return (DemoApp) mContext;
    }

    /**
     * 开发模式
     *
     * @return false:线上模式,true:调试模式
     */
    public static boolean isDebug() {
        return TextUtils.equals(BuildConfig.BUILD_TYPE, "debug");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //HttpHelper.init(new VolleyProcessor(this));
    }

    @Override
    public void showToast(String msg) {
        UIUtils.toast(msg);
    }

    /**
     * 日志输出配置.
     */
    @Override
    protected void initLogConfig() {
//        LogUtils.getLogConfig()
//                .configAllowLog(isShowLog())
//                .configTagPrefix(UIUtils.getString(R.string.app_name))
//                .configShowBorders(true)
//                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");
    }

    @Override
    protected void initDevelopMode() {
        super.initDevelopMode();
//        if (isDebug()) {
//            ApiFactory.getInstance().setMode(ApiFactory.MODE_ON_LINE);
//        } else {
//            ApiFactory.getInstance().setMode(ApiFactory.MODE_ON_LINE);
//        }
    }


}