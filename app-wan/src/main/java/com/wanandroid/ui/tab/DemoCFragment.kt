package com.wanandroid.ui.tab

import android.util.Log
import com.wanandroid.R
import com.wanandroid.base.MyBaseFragment


class DemoCFragment : MyBaseFragment() {

    override val layoutResId: Int = R.layout.fragment_demo_a


    companion object {
        private const val TAG = "DemoCFragment"
        @JvmStatic
        fun newInstance() =
            DemoCFragment().apply {
            }
    }

    override fun lazyLoadData() {
        Log.i(TAG, "lazyLoadData: $TAG")
    }
}