package com.zxn.mvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.zxn.mvvm.R
import com.zxn.mvvm.ext.getVmClazz
import com.zxn.mvvm.model.IBaseModel
import com.zxn.mvvm.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseDialogFragment<VM : BaseViewModel<out IBaseModel<*>>> : DialogFragment(), IView {
    protected var TAG = this.javaClass.simpleName
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
        createObserver()

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
    protected fun initTheme(): Int {
        //return R.style.BaseDialog_FullScreen;
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

}