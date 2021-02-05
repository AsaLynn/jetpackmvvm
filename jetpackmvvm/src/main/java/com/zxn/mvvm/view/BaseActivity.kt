package com.zxn.mvvm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.zxn.mvvm.R
import com.zxn.mvvm.ext.getVmClazz
import com.zxn.mvvm.model.IBaseModel
import com.zxn.mvvm.network.NetState
import com.zxn.mvvm.network.NetworkStateManager
import com.zxn.mvvm.viewmodel.BaseViewModel
import com.zxn.utils.UIUtils
import java.lang.reflect.ParameterizedType

/**
 *  VM : BaseViewModel<out IBaseModel<*>>
 *  Updated by zxn on 2020/10/23.
 */
abstract class BaseActivity<VM : BaseViewModel<out IBaseModel<*>>> : RxAppCompatActivity(), IBaseView,
        ILoadingView {

    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false

    lateinit var mViewModel: VM
    override var cancelable: Boolean = true
    override lateinit var mContext: AppCompatActivity
    override var usedEventBus: Boolean = false
    override var usedImmersionBar: Boolean = false
    override var titleBar: View? = null
    override var usedStatusBarDarkFont: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        if (layoutResId != 0) {
            setContentView(layoutResId)
        }

        if (usedEventBus) {
            registerEventBus(true)
        }

        if (this.javaClass.genericSuperclass is ParameterizedType) {
            mViewModel = createViewModel()!!
        }



        if (isViewModelInitialized()) {
            //加载进度弹框.
            registerUiChange()
            createObserver()
            //让ViewModel拥有View的生命周期感应
            lifecycle.addObserver(mViewModel)
            //注入RxLifecycle生命周期
            mViewModel.injectLifecycleProvider(this)
            //私有的ViewModel与View的契约事件回调逻辑
            registorUIChangeLiveDataCallBack()
        }

        onInitView()

        onInitImmersionBar()

        NetworkStateManager.instance.mNetworkStateCallback.observeInActivity(this) {
            onNetworkStateChanged(it)
        }
    }

    override fun onDestroy() {
        registerEventBus(false)
        super.onDestroy()
        //解除ViewModel生命周期感应
        if (isViewModelInitialized()) {
            lifecycle.removeObserver(mViewModel)
        }
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this)!!)
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

    override fun showToast(msg: String) {
        UIUtils.toast(msg)
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
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

    override fun showLoading(msg: String?) {

    }

    override fun showLoading(msgResId: Int) {

    }

    override fun closeLoading() {

    }

    open fun isViewModelInitialized(): Boolean = ::mViewModel.isInitialized

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    private fun registorUIChangeLiveDataCallBack() {
        //加载对话框显示
        mViewModel.getUC().getShowLoadingEvent().observe(this, {
            //showLoadingUI(it[BaseViewModel.ParameterField.MSG].toString(), it[BaseViewModel.ParameterField.IS_CANCLE] as Boolean)
            cancelable = it[BaseViewModel.ParameterField.IS_CANCLE] as Boolean
            if (it[BaseViewModel.ParameterField.MSG] is String) {
                showLoading(it[BaseViewModel.ParameterField.MSG] as String)
            }
            if (it[BaseViewModel.ParameterField.MSG] is Int) {
                showLoading(it[BaseViewModel.ParameterField.MSG] as Int)
            }
        })
        //加载对话框消失
        mViewModel.getUC().getHideLoadingEvent().observe(this, {
            closeLoading()
        })
        //Toast显示
        mViewModel.getUC().getShowToastEvent().observe(this, {
            if (it is String) {
                showToast(it)
            }
            if (it is Int) {
                showToast(it)
            }
        })
        //跳入新页面
        mViewModel.getUC().getStartActivityEvent()
                .observe(this, {
                    fun onChanged(@Nullable params: Map<String, Any>) {
                        val clz = params[BaseViewModel.ParameterField.CLASS] as Class<*>
                        val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
                        startActivity(clz, bundle)
                    }
                })
        //关闭界面
        mViewModel.getUC().getFinishEvent().observe(this, {
            setResult(Activity.RESULT_OK)
            finish()
        })
        //关闭上一层
        mViewModel.getUC().getOnBackPressedEvent().observe(this, {
            onBackPressed()
        })
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 注册UI 事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observeInActivity(this) {
            showLoading(it)
        }
        //关闭弹窗
        mViewModel.loadingChange.dismissDialog.observeInActivity(this) {
            //dismissLoading()
            closeLoading()
        }
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }

    /**
     * 供子类BaseVmDbActivity 初始化Databinding操作
     */
    open fun initDataBind() {}

    /**
     * 将非该Activity绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel<*>) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInActivity(this) {
                showLoading()
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInActivity(this) {
                //dismissLoading()
                closeLoading()
            }
        }
    }

    override fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            closeLoading()
        }
    }

}
