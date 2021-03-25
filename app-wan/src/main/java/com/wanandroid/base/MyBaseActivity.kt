package com.wanandroid.base

import com.zxn.mvvm.view.BaseActivity

/**
 * 基类.
 *  Created by ny on 2021/2/20.
 */
abstract class MyBaseActivity/*<VM : BaseViewModel<out IBaseModel<*>>>*/ : BaseActivity/*<VM>*/() {

    override fun registerEventBus(isRegister: Boolean) {

    }

    override fun createObserver() {

    }

    override fun onInitView() {
        initView()
        initData()
    }

    open fun initView() {
    }

    open fun initData() {
    }

//    override val layoutResId: Int
//        get() = getLayoutResId()

//    abstract fun getLayoutResId(): Int = layoutResId

}