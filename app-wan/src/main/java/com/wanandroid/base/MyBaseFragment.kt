package com.wanandroid.base

import android.view.View
import com.zxn.mvvm.view.BaseFragment

/**
 *
 *  Created by ny on 2021/2/20.
 */
abstract class MyBaseFragment : BaseFragment() {

    override val layoutResId: Int = 0

    override val layoutRoot: View?= null

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