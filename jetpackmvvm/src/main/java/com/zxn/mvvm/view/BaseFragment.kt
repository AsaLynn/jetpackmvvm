package com.zxn.mvvm.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.zxn.mvvm.ext.getVmClazz
import com.zxn.mvvm.model.IBaseModel
import com.zxn.mvvm.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * Updated by zxn on 2020/10/23.
 */
abstract class BaseFragment<VM : BaseViewModel<out IBaseModel<*>>> : Fragment(), IBaseView,ILoadingView {

    private var mPageTitle = ""
    lateinit var mViewModel: VM
    override var titleBar: View? = null
    override var usedImmersionBar: Boolean = false
    override var usedStatusBarDarkFont: Boolean = false
    override var usedEventBus: Boolean = false
    override lateinit var mContext: AppCompatActivity
    override var cancelable: Boolean = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this.javaClass.genericSuperclass is ParameterizedType) {
            mViewModel = createViewModel()!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = if (layoutResId <= 0) super.onCreateView(inflater, container, savedInstanceState)
    else inflater.inflate(layoutResId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()

        createObserver()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (usedEventBus) registerEventBus(true)
        onInitImmersionBar()
    }

    override fun onDestroy() {
        registerEventBus(false)
        super.onDestroy()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this)!!)
    }

    val pageTitle: CharSequence = mPageTitle

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

    override fun showToast(msg: String) {
        if (activity is BaseActivity<*>) {
            val activity = activity as BaseActivity<*>?
            activity!!.showToast(msg)
        }
    }

    fun finish() {
        if (null != activity) {
            activity!!.finish()
        }
    }

    override fun onInitImmersionBar() {
        if (usedImmersionBar) {
            if (usedStatusBarDarkFont) {
                setStatusBarDarkFont()
            } else {
                ImmersionBar.with(this)
                    .titleBar(titleBar)
                    .init()
            }
        }
    }

    /**
     * 白色状态栏,黑色字体,黑色导航栏,解决了白色状态栏无法看见状态栏字体颜色问题
     */
    open fun setStatusBarDarkFont() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .navigationBarDarkIcon(true)
            .titleBar(titleBar)
            .init()
    }
}