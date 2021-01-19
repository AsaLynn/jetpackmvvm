package com.zxn.mvvm.demo

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.zxn.mvvm.demo.viewmodel.MainViewModel
import com.zxn.mvvm.view.BaseDialogFragment
import kotlinx.android.synthetic.main.fragment_main_dg.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainDgFragment : BaseDialogFragment<MainViewModel>() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                MainDgFragment()

        /**
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainDgFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainDgFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override val layoutResId: Int = R.layout.fragment_main_dg


    override fun onInitView() {
        tvDg.setOnClickListener {
            mViewModel.showToast("setOnClickListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setGravity(Gravity.BOTTOM)
        return dialog
    }

    override fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun initTheme(): Int = super.initTheme()
}