package com.wanandroid.base

import android.view.View
import com.zxn.mvvm.view.BaseActivity

/**
 * 基类.
 *  Created by ny on 2021/2/20.
 */
abstract class MyBaseActivity : BaseActivity() {

    override val layoutResId: Int = 0

//    override val layoutRoot: View? = null

    override fun onCreateRootView(): View? = null

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

}