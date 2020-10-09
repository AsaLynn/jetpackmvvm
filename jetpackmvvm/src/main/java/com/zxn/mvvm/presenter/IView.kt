package com.zxn.mvvm.presenter

/**
 * Updated by zxn on 2020/10/9.
 */
interface IView {
    fun showLoading()
    fun showToast(msg: Int)
    fun showToast(msg: String?)
    fun showLoading(cancelable: Boolean)
    fun showLoading(msg: String?, cancelable: Boolean)
    fun showLoading(msg: String?)
    fun showLoading(msgResId: Int)
    fun closeLoading()
}