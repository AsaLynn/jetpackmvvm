package com.zxn.mvvm.view

import android.view.View

/**
 *  Created by zxn on 2020/11/13.
 */
interface IBaseView : IView, IToastView {

    /**
     * 标题.
     */
    var titleBar: View?

    /**
     * 是否使用沉浸式
     */
    var usedImmersionBar: Boolean

    /**
     * 是否使用EventBus
     */
    var usedEventBus: Boolean

    /**
     * 是否启用白色状态栏,黑色字体.
     */
    var usedStatusBarDarkFont: Boolean

    /**
     * 控制注册注销使用EventBus
     */
    fun registerEventBus(isRegister: Boolean)

    fun onInitImmersionBar()
}