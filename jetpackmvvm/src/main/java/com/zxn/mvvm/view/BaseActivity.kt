package com.zxn.mvvm.view

import BaseViewModel
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.zxn.mvvm.R
import com.zxn.mvvm.ext.getVmClazz
import com.zxn.utils.UIUtils
import java.lang.reflect.ParameterizedType

/**
 *  Updated by zxn on 2020/10/23.
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), IView, ILoadingView {

    lateinit var mViewModel: VM
    override var cancelable: Boolean = true
    override lateinit var mContext: AppCompatActivity
    override var usedEventBus: Boolean = false


//    lateinit var mContext: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        if (layoutResId != 0) {
            setContentView(layoutResId)
        }
        onInitImmersionBar()
        if (usedEventBus) {
            registerEventBus(true)
        }

        if (this.javaClass.genericSuperclass is ParameterizedType){
            mViewModel = createViewModel()
        }
        createObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        registerEventBus(false)
    }

    /**
     * 创建viewModel
     */
    fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 启动页面
     *
     * @param intent      启动意图
     * @param requestCode 请求码
     */
    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        // 返回桌面的时候不要动画,
        if (intent.categories == null || !intent.categories.toString().contains("HOME")) {
            startAnimation(true)
        }
    }

    /**
     * 关闭页面
     */
    override fun finish() {
        super.finish()
        startAnimation(false)
    }

    override fun showToast(msg: Int) {
        UIUtils.toast(UIUtils.getString(msg))
    }

    override fun showToast(msg: String?) {
        UIUtils.toast(msg)
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    open fun onInitImmersionBar() {
        if (usedStatusBarDarkFont()) {
            setStatusBarDarkFont()
        }
    }

    /**
     * 是否启用白色状态栏,黑色字体.
     *
     * @return false:关闭
     */
    open fun usedStatusBarDarkFont(): Boolean {
        return false
    }

    /**
     * 白色状态栏,黑色字体,黑色导航栏,解决了白色状态栏无法看见状态栏字体颜色问题
     */
    open fun setStatusBarDarkFont() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .navigationBarDarkIcon(true)
                .init()
    }

    /**
     * 指定开启动画或者关闭动画
     *
     * @param start true:执行开启动画,false:执行关闭动画.
     */
    open fun startAnimation(start: Boolean) {
        if (usedAnimation()) {
            if (start) {
                //启动动画
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out)
            } else {
                //关闭动画
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out)
            }
        }
    }

    /**
     * 是否启动动画,默认启用动画.
     *
     * @return true:启用,false,不启用.
     */
    open fun usedAnimation(): Boolean {
        return true
    }

    override fun showLoading() {

    }

    override fun showLoading(msg: String?) {

    }

    override fun showLoading(msgResId: Int) {

    }

    override fun closeLoading() {

    }

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }
}