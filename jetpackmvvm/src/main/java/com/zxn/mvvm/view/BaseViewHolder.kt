package com.zxn.mvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 *  Updated by zxn on 2020/10/23.
 */
abstract class BaseViewHolder {

    lateinit var mContext: Context

    var view: View? = null
        protected set

    constructor(context: FragmentActivity) {
        mContext = context
        initView()
    }

    /**
     * @param fragment 所依附的acitivty必须是FragmentActivity
     */
    constructor(fragment: Fragment) {
        fragment.activity?.let {
            mContext = it
        }
        initView()
    }

    private fun initView() {
        if (layoutResId() == 0) {
            return
        }
        view = LayoutInflater.from(mContext).inflate(layoutResId(), null, false)
        onInitView(view)
    }

    abstract fun onInitView(rootView: View?)

    fun setVisibility(visibility: Boolean) {
        if (null != view) {
            view!!.visibility = if (visibility) View.VISIBLE else View.GONE
        }
    }

     abstract fun layoutResId(): Int

//    init {
//        initView()
//    }

    open fun setText(@IdRes viewId: Int, value: CharSequence?): BaseViewHolder {
        view?.findViewById<TextView>(viewId)?.text = value
        return this
    }

    open fun setGone(@IdRes viewId: Int, isGone: Boolean): BaseViewHolder {
        val view = view?.findViewById<View>(viewId)
        view?.visibility = if (isGone) View.GONE else View.VISIBLE
        return this
    }

    open fun setVisible(@IdRes viewId: Int, isVisible: Boolean): BaseViewHolder {
        val view = view?.findViewById<View>(viewId)
        view?.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        return this
    }

    open fun <T : View> getView(@IdRes viewId: Int): T {
        val v = getViewOrNull<T>(viewId)
        checkNotNull(v) { "No view found with id $viewId" }
        return v
    }

    @Suppress("UNCHECKED_CAST")
    open fun <T : View> getViewOrNull(@IdRes viewId: Int): T? {
        //val view = views.get(viewId)
        val v = view?.findViewById<View>(viewId)
        return v as? T
    }
}