package com.zxn.mvvm.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zxn.mvvm.view.BaseFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MyMainFragment : BaseFragment<Nothing>() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun registerEventBus(isRegister: Boolean) {

    }

    companion object {

        /**
         * 进入
         */
        @JvmStatic
        fun newInstance() = MyMainFragment()

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyMainFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MyMainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override val layoutResId: Int
        get() = R.layout.fragment_main_my

    override fun onInitView() {
        //finish()
    }

    override fun createObserver() {
    }
}