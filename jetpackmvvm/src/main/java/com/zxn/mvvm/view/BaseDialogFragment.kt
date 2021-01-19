package com.zxn.mvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment
import com.zxn.mvvm.R
import com.zxn.mvvm.ext.getVmClazz
import com.zxn.mvvm.model.IBaseModel
import com.zxn.mvvm.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 *  Created by zxn on 2021/1/15.
 */
abstract class BaseDialogFragment<VM : BaseViewModel<out IBaseModel<*>>> : RxAppCompatDialogFragment(), IView, IToastView {

    companion object {
        private const val TAG = "BaseDialogFragment"
    }

    lateinit var mViewModel: VM
    override lateinit var mContext: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, initTheme())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity as AppCompatActivity

        try {
            if (this.javaClass.genericSuperclass is ParameterizedType) {
                mViewModel = createViewModel()
            }
        } catch (e: Exception) {
            Log.i(TAG, "onActivityCreated: VM == null")
        }

        if (::mViewModel.isInitialized) {
            createObserver()
            lifecycle.addObserver(mViewModel)
            mViewModel.injectLifecycleProvider(this)
            registorUIChangeLiveDataCallBack()
        }

        onInitView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = if (layoutResId <= 0) super.onCreateView(inflater, container, savedInstanceState)
    else inflater.inflate(layoutResId, container, false)

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this)!!)
    }

    override fun createObserver() {

    }

    /**
     * 重写此方法可以更换主题
     *
     * @return
     */
    open fun initTheme(): Int {
        //return R.style.BaseDialog_FullScreen
        return R.style.BaseDialog_Nice
    }

    fun show(manager: FragmentManager?) {
        super.show(manager!!, TAG)
    }

    fun showNow(manager: FragmentManager?) {
        super.showNow(manager!!, TAG)
    }

    fun show(transaction: FragmentTransaction?): Int {
        return super.show(transaction!!, TAG)
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

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    private fun registorUIChangeLiveDataCallBack() {
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
        //Toast显示
        mViewModel.getUC().getShowToastEvent().observe(this, {
            if (it is String) {
                showToast(it)
            }
            if (it is Int) {
                showToast(it)
            }
        })
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
//            setResult(Activity.RESULT_OK)
//            finish()
//        })
        //关闭上一层
//        mViewModel.getUC().getOnBackPressedEvent().observe(this, {
//            onBackPressed()
//        })
    }

}