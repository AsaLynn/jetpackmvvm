package com.zxn.mvvm.view

/**
 * Created by zxn on 2020/10/23.
 */
interface ILoadingView {
    /**
     * 控制加载框是否可取消
     */
    var cancelable: Boolean
    fun showLoading(msg: String? = null)
    fun showLoading(msgResId: Int)

    /**
     *处理加载框状态.
     * @param isLoading true:加载,false:消失.
     */
    fun onLoading(isLoading: Boolean)

    fun closeLoading()
}