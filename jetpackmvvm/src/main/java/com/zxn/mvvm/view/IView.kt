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

    /**
     * 是否使用EventBus
     */
    var usedEventBus: Boolean



    fun showToast(msg: Int)
    fun showToast(msg: String?)



    /**
     * 创建LiveData数据观察者
     */
    fun createObserver()

    /**
     * 控制注册注销使用EventBus
     */
    fun registerEventBus(isRegister: Boolean)
}
