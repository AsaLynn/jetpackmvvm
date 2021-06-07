package com.zxn.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment
import com.zxn.mvvm.R

/**
 *  Updated by zxn on 2021.05.29.
 */
abstract class BaseDialogFragment : RxAppCompatDialogFragment(), IView, IToastView {

    companion object {
        private const val TAG = "BaseDialogFragment"
    }

    override lateinit var mContext: AppCompatActivity

    override val layoutRoot: View? by lazy {
        onCreateRootView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, initTheme())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity as AppCompatActivity

        createObserver()

        onInitView()
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

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    fun showNow(manager: FragmentManager) {
        super.showNow(manager, TAG)
    }

    fun show(transaction: FragmentTransaction?): Int {
        return super.show(transaction!!, TAG)
    }

    override fun showToast(msg: Int) {
        if (activity is BaseActivity) {
            val activity = activity as BaseActivity
            activity.showToast(msg)
        }
    }

    override fun showToast(msg: String) {
        if (activity is BaseActivity) {
            val activity = activity as BaseActivity
            activity.showToast(msg)
        }
    }

}