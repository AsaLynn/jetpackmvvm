package com.zxn.mvvm.view

/**
 * Created by zxn on 2020/10/23.
 */
interface ILoadingView {
    /**
     * 控制加载框是否可取消
     */
    var cancelable: Boolean
    fun showLoading()
    fun showLoading(msg: String?)
    fun showLoading(msgResId: Int)
    fun closeLoading()
}