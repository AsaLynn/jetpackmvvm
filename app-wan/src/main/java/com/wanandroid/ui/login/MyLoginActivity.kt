package com.wanandroid.ui.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.wanandroid.App
import com.wanandroid.base.MyBaseActivity
import com.wanandroid.databinding.ActivityMyLoginBinding
import kotlinx.android.synthetic.main.activity_my_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by ny on 2021/2/4.
 */
class MyLoginActivity : MyBaseActivity() {

    companion object {
        @JvmStatic
        fun jumpTo(context: Context) {
            context.startActivity(Intent(context, MyLoginActivity::class.java))
        }

        private const val TAG = "MyLoginActivity"
    }

    private val mViewModel by viewModel<MyLoginViewModel>()

    private val mViewBinding by lazy {
        ActivityMyLoginBinding.inflate(layoutInflater)
    }

    override val layoutRoot: View by lazy { mViewBinding.root }

    override fun onInitView() {
        mViewBinding.loginBt.setOnClickListener {
            val name = user_edt.text.toString()
            val password = pwd_edt.text.toString()
            mViewModel.login(name, password)
        }
    }

    override fun createObserver() {
        mViewModel.mLoadingDataEvent.observe(this, {
            onLoading(it.isLoading)
            it.successData?.let { it1 ->
                if (!it1.username.isNullOrEmpty() && it1.id.toString().isNotEmpty()) {
                    val intent = Intent()
                    setResult(2, intent)
                    Toast.makeText(mContext, "登录成功", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            it.showError?.let { it1 ->
                Toast.makeText(App.getContext(), it1, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            closeLoading()
        }
    }

    override fun showLoading(msg: String?) {
        showProgressDialog()
        Log.i(TAG, "showLoading: ")
    }

    override fun closeLoading() {
        dismissProgressDialog()
        Log.i(TAG, "closeLoading: ")
    }

    private var progressDialog: ProgressDialog? = null

    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }


}