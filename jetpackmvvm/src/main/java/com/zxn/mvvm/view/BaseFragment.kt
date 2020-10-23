package com.zxn.mvvm.view

import BaseViewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zxn.mvvm.ext.getVmClazz

/**
 * Updated by zxn on 2020/10/23.
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment(), IView,ILoadingView {

//    abstract val layoutResId: Int
    protected var mPageTitle = ""
//    lateinit var mContext: AppCompatActivity
    lateinit var mViewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = if (layoutResId <= 0) super.onCreateView(inflater, container, savedInstanceState)
    else inflater.inflate(layoutResId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = createViewModel()
        createObserver()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (usedEventBus) registerEventBus(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    val pageTitle: CharSequence
        get() = mPageTitle

    override fun showLoading() {
        if (activity is BaseActivity<*>) {
            val activity = activity as BaseActivity<*>?
            activity!!.showLoading()
        }
    }

    override fun showLoading(msg: String?) {
        if (activity is BaseActivity<*>) {
            val activity = activity as BaseActivity<*>?
            activity!!.showLoading(msg)
        }
    }

    override fun showLoading(msgResId: Int) {
        if (activity is BaseActivity<*>) {
            val activity = activity as BaseActivity<*>?
            activity!!.showLoading(msgResId)
        }
    }


    override fun closeLoading() {
        if (activity is BaseActivity<*>) {
            val activity = activity as BaseActivity<*>?
            activity!!.closeLoading()
        }
    }

    override fun showToast(msg: Int) {
        if (activity is BaseActivity<*>) {
            val activity = activity as BaseActivity<*>?
            activity!!.showToast(msg)
        }
    }

    override fun showToast(msg: String?) {
        if (activity is BaseActivity<*>) {
            val activity = activity as BaseActivity<*>?
            activity!!.showToast(msg)
        }
    }

    /**
     * Call this when your activity is done and should be closed.  The
     * ActivityResult is propagated back to whoever launched you via
     * onActivityResult().
     */
    fun finish() {
        if (null != activity) {
            activity!!.finish()
        }
    }
}