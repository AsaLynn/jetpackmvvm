package com.zxn.mvvm.view

import androidx.appcompat.app.AppCompatActivity

/**
 *  Updated by zxn on 2020/10/23.
 */
interface IView {

    /**
     * 布局的资源id.
     */
    val layoutResId: Int

    /**
     * 上下文
     */
    var mContext: AppCompatActivity

    fun showToast(msg: Int)

    fun showToast(msg: String)

    fun onInitView()

    /**
     * 创建LiveData数据观察者
     */
    fun createObserver()
}
