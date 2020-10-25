package com.zxn.mvvm.view

import BaseViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.zxn.mvvm.R
import com.zxn.mvvm.ext.getVmClazz

/**
 * Copyright(c) ${}YEAR} ZhuLi co.,Ltd All Rights Reserved.
 *
 * @className: BaseDialogFragment$
 * @description: TODO 类描述
 * @version: v0.0.1
 * @author: zxn < a href=" ">zhangxiaoning@17biyi.com</ a>
 * @date: 2020/10/23$ 15:24$
 * @updateUser: 更新者：
 * @updateDate: 2020/10/23$ 15:24$
 * @updateRemark: 更新说明：
 * @version: 1.0
 * */
//abstract class BaseDialogFragment<VM : BaseViewModel> : DialogFragment(),IView {
//    protected var TAG = this.javaClass.simpleName
//
//    lateinit var mViewModel: VM
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NO_TITLE, initTheme())
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        mContext = activity as AppCompatActivity
//        mViewModel = createViewModel()
//        createObserver()
//    }
//
//    private fun createViewModel(): VM {
//        return ViewModelProvider(this).get(getVmClazz(this)!!)
//    }
//
//    override fun createObserver(){
//
//    }
//
//    /**
//     * 重写此方法可以更换主题
//     *
//     * @return
//     */
//    protected fun initTheme(): Int {
//        //return R.style.BaseDialog_FullScreen;
//        return R.style.BaseDialog_Nice
//    }
//
//    fun show(manager: FragmentManager?) {
//        super.show(manager!!, TAG)
//    }
//
//    fun showNow(manager: FragmentManager?) {
//        super.showNow(manager!!, TAG)
//    }
//
//    fun show(transaction: FragmentTransaction?): Int {
//        return super.show(transaction!!, TAG)
//    }
//
//    override fun showToast(msg: Int) {
//        if (activity is BaseActivity<*>) {
//            val activity = activity as BaseActivity<*>?
//            activity!!.showToast(msg)
//        }
//    }
//
//    override fun showToast(msg: String?) {
//        if (activity is BaseActivity<*>) {
//            val activity = activity as BaseActivity<*>?
//            activity!!.showToast(msg)
//        }
//    }
//}