package com.zxn.mvvm.view

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 *  Updated by zxn on 2020/10/23.
 */
abstract class BaseViewHolder(protected var mContext: FragmentActivity?) {
    var view: View? = null
        protected set

    /**
     * @param fragment 所依附的acitivty必须是FragmentActivity
     */
    constructor(fragment: Fragment) : this(fragment.activity) {

    }

    private fun initView() {
        if (layoutResId == 0) {
            return
        }
        view = LayoutInflater.from(mContext).inflate(layoutResId, null, false)
        onInitView(view)
    }

    abstract fun onInitView(rootView: View?)

    fun setVisibility(visibility: Boolean) {
        if (null != view) {
            view!!.visibility = if (visibility) View.VISIBLE else View.GONE
        }
    }

    protected abstract val layoutResId: Int

    init {
        initView()
    }
}