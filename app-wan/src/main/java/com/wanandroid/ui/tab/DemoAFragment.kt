package com.wanandroid.ui.tab

import android.util.Log
import com.wanandroid.R
import com.wanandroid.base.MyBaseFragment
import com.zxn.mvvm.ext.jumpInTo
import kotlinx.android.synthetic.main.fragment_demo_a.*


class DemoAFragment : MyBaseFragment() {


    override val layoutResId: Int = R.layout.fragment_demo_a


    companion object {

        private const val TAG = "DemoAFragment"

        @JvmStatic
        fun newInstance() =
            DemoAFragment().apply {

            }
    }

    override fun onInitView() {
        btn0.setOnClickListener {
            jumpInTo<Main0Activity>(mContext)
        }
    }

    override fun lazyLoadData() {
        Log.i(TAG, "lazyLoadData: $TAG,")
    }
}