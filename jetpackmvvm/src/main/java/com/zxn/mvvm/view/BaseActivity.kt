package com.zxn.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.zxn.mvvm.R
import com.zxn.mvvm.ext.getVmClazz
import com.zxn.mvvm.model.IBaseModel
import com.zxn.mvvm.viewmodel.BaseViewModel
import com.zxn.utils.UIUtils
import java.lang.reflect.ParameterizedType

/**
 *  Updated by zxn on 2020/10/23.
 */
abstract class BaseActivity<VM : BaseViewModel<out IBaseModel<*>>> : AppCompatActivity(), IBaseView,
        ILoadingView {


    override var usedViewBinding = false
    lateinit var mViewModel: VM
    override var cancelable: Boolean = true
    override lateinit var mContext: AppCompatActivity
    override var usedEventBus: Boolean = false
    override var usedImmersionBar: Boolean = false
    override var titleBar: View? = null
    override var usedStatusBarDarkFont: Boolean = false

    protected inline fun <reified T : ViewDataBinding> binding(): Lazy<T> = lazy {
        DataBindingUtil.setContentView<T>(this, layoutResId).apply {
            lifecycleOwner = this@BaseActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        if (!usedViewBinding) {
            if (layoutResId != 0) {
                setContentView(layoutResId)
            }
        }

        if (usedEventBus) {
            registerEventBus(true)
        }

        if (this.javaClass.genericSuperclass is ParameterizedType) {
            mViewModel = createViewModel()!!
        }

        if (isViewModelInitialized()) {
            createObserver()
        }

        onInitView()

        onInitImmersionBar()

    }

    override fun onDestroy() {
        registerEventBus(false)
        super.onDestroy()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM? {
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

    protected fun isViewModelInitialized(): Boolean = ::mViewModel.isInitialized

//    /**
//     * 供子类BaseVmDbActivity 初始化Databinding操作
//     */
//    open fun initViewDataBinding() {}
}


//    override fun initBaseView() {
//        //让ViewModel拥有View的生命周期感应
//        lifecycle.addObserver(viewModel)
//        //注入RxLifecycle生命周期
//        viewModel.injectLifecycleProvider(this)
//        //私有的ViewModel与View的契约事件回调逻辑
////        registorUIChangeLiveDataCallBack()
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//        //解除ViewModel生命周期感应
//        lifecycle.removeObserver(viewModel)
//    }
//

//    //注册ViewModel与View的契约UI回调事件
////    private fun registorUIChangeLiveDataCallBack() {
////        //加载对话框显示
////        viewModel.getUC().getShowLoadingEvent().observe(this, Observer {
////            showLoadingUI(it[BaseViewModel.ParameterField.MSG].toString(),
////                    it[BaseViewModel.ParameterField.IS_CANCLE] as Boolean
////            )
////        })
////        //加载对话框消失
////        viewModel.getUC().getHideLoadingEvent().observe(this, Observer {
////            hideLoadingUI()
////        })
////        //Toast显示
////        viewModel.getUC().getShowToastEvent().observe(this, Observer {
////            toast(it)
////        })
////        //跳入新页面
////        viewModel.getUC().getStartActivityEvent()
////                .observe(this, Observer {
////                    fun onChanged(@Nullable params: Map<String, Any>) {
////                        val clz = params[BaseViewModel.ParameterField.CLASS] as Class<*>
////                        val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
////                        startActivity(clz, bundle)
////                    }
////                })
////        //关闭界面
////        viewModel.getUC().getFinishEvent().observe(this, Observer {
////            setResult(Activity.RESULT_OK)
////            finish()
////        })
////        //关闭上一层
////        viewModel.getUC().getOnBackPressedEvent().observe(this, Observer {
////            onBackPressed()
////        })
////    }
//}