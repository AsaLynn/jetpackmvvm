package com.wanandroid.base

import com.zxn.mvvm.view.BaseFragment

/**
 *
 *  Created by ny on 2021/2/20.
 */
abstract class MyBaseFragment/*<VM : BaseViewModel<out IBaseModel<*>>>*/ : BaseFragment/*<VM>*/() {

    override fun lazyLoadData() {

    }

    override fun registerEventBus(isRegister: Boolean) {

    }

    override fun onInitView() {
        initView()
    }

    override fun createObserver() {

    }

    override fun onLoading(isLoading: Boolean) {

    }

    open fun initView() {

    }



}