package com.zxn.mvvm.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.gyf.immersionbar.ImmersionBar
import com.trello.rxlifecycle2.components.support.RxFragment
import com.zxn.mvvm.network.NetState
import com.zxn.mvvm.network.NetworkStateManager
import com.zxn.mvvm.viewmodel.BaseViewModel

/**
 * Updated by zxn on 2020/10/23.
 */
abstract class BaseFragment : RxFragment(), IBaseView, ILoadingView {

    //是否第一次加载
    var isFirst: Boolean = true
        private set
    private var mPageTitle = ""

    override var titleBar: View? = null
    override var usedImmersionBar: Boolean = false
    override var usedStatusBarDarkFont: Boolean = false
    override var usedEventBus: Boolean = false
    override lateinit var mContext: AppCompatActivity
    override var cancelable: Boolean = true
    override val layoutResId: Int = 0
    override val layoutRoot: View? by lazy {
        onCreateRootView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as AppCompatActivity
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = if (layoutRoot != null) layoutRoot else (if (layoutResId <= 0) super.onCreateView(
        inflater,
        container,
        savedInstanceState
    )
    else inflater.inflate(layoutResId, container, false))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true

        createObserver()

        onInitView()

        initData()
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

    override fun onHiddenChanged(hidden: Boolean) {
        view?.post {
            if (!hidden) {
                lazyLoadData()
            }
        }
    }

    val pageTitle: CharSequence = mPageTitle

    override fun showLoading(msg: String?) {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.showLoading(msg)
        }
    }

    override fun showLoading(msgResId: Int) {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.showLoading(msgResId)
        }
    }


    override fun closeLoading() {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.closeLoading()
        }
    }

    override fun showToast(msg: Int) {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.showToast(msg)
        }
    }

    override fun showToast(msg: String) {
        if (activity is BaseActivity/*<*>*/) {
            val activity = activity as BaseActivity/*<*>?*/
            activity.showToast(msg)
        }
    }

    protected open fun finish() {
        activity?.finish()
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


    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    /**
     * 当可见的时候加载数据.
     */
    abstract fun lazyLoadData()

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //等待view加载后触发懒加载
            view?.post {
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observeInFragment(this) {
                    //不是首次订阅时调用方法，防止数据第一次监听错误
                    if (!isFirst) {
                        onNetworkStateChanged(it)
                    }
                }
                isFirst = false
            }
        }
    }

    /**
     * 将非该Fragment绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel<*>) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInFragment(this) {
                showLoading()
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInFragment(this) {
                closeLoading()
            }
        }
    }


}


//    /**
//     * 注册ViewModel与View的契约UI回调事件
//     */
//    private fun registorUIChangeLiveDataCallBack() {
//        //加载对话框显示
//        mViewModel.getUC().getShowLoadingEvent().observe(this, {
//            //showLoadingUI(it[BaseViewModel.ParameterField.MSG].toString(), it[BaseViewModel.ParameterField.IS_CANCLE] as Boolean)
//            cancelable = it[BaseViewModel.ParameterField.IS_CANCLE] as Boolean
//            if (it[BaseViewModel.ParameterField.MSG] is String) {
//                showLoading(it[BaseViewModel.ParameterField.MSG] as String)
//            }
//            if (it[BaseViewModel.ParameterField.MSG] is Int) {
//                showLoading(it[BaseViewModel.ParameterField.MSG] as Int)
//            }
//        })
//        //加载对话框消失
//        mViewModel.getUC().getHideLoadingEvent().observe(this, {
//            closeLoading()
//        })
//        //Toast显示
//        mViewModel.getUC().getShowToastEvent().observe(this, {
//            if (it is String) {
//                showToast(it)
//            }
//            if (it is Int) {
//                showToast(it)
//            }
//        })
//        //跳入新页面
//        mViewModel.getUC().getStartActivityEvent()
//                .observe(this, {
//                    fun onChanged(@Nullable params: Map<String, Any>) {
//                        val clz = params[BaseViewModel.ParameterField.CLASS] as Class<*>
//                        val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
//                        startActivity(clz, bundle)
//                    }
//                })
//        //关闭界面
//        mViewModel.getUC().getFinishEvent().observe(this, {
//            finish()
//        })
//关闭上一层
//        mViewModel.getUC().getOnBackPressedEvent().observe(this, {
//            onBackPressed()
//        })
//    }

//    /**
//     * 注册 UI 事件
//     */
//    private fun registorDefUIChange() {
//        mViewModel.loadingChange.showDialog.observeInFragment(this) {
//            showLoading(it)
//        }
//        mViewModel.loadingChange.dismissDialog.observeInFragment(this) {
//            closeLoading()
//        }
//    }