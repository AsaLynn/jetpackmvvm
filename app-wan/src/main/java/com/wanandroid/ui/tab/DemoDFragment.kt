package com.wanandroid.ui.tab

import android.util.Log
import com.wanandroid.R
import com.wanandroid.base.MyBaseFragment


class DemoDFragment : MyBaseFragment() {

    override val layoutResId: Int = R.layout.fragment_demo_a

    companion object {
        private const val TAG = "DemoDFragment"
        @JvmStatic
        fun newInstance() =
            DemoDFragment().apply {

            }
    }

    override fun lazyLoadData() {
        Log.i(TAG, "lazyLoadData: $TAG")
    }
}