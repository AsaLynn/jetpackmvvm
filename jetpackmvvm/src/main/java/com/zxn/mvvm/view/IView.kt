package com.zxn.mvvm.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 *  Updated by zxn on 2020/10/23.
 */
interface IView {


    /**
     * 根布局不为null的时候生效启动,
     */
    val layoutRoot: View?

    /**
     * 布局的资源id,layoutRoot为null的时候启用.
     */
    val layoutResId: Int

    /**
     * 上下文
     */
    var mContext: AppCompatActivity



    fun onInitView()

    /**
     * 创建LiveData数据观察者
     */
    fun createObserver()
}
