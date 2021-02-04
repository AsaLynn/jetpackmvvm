package com.wanandroid.ui.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.wanandroid.R
import com.zxn.mvvm.view.BaseActivity
import kotlinx.android.synthetic.main.activity_my_login.*

/**
 *
 *  Created by ny on 2021/2/4.
 */
class MyLoginActivity : BaseActivity<MyLoginViewModel>() {

    companion object {
        @JvmStatic
        fun jumpTo(context: Context) {
            context.startActivity(Intent(context, MyLoginActivity::class.java))
        }
    }

    override fun registerEventBus(isRegister: Boolean) {

    }

    override val layoutResId: Int = R.layout.activity_my_login

    override fun onInitView() {
        login_bt.setOnClickListener {
            val name = user_edt.text.toString()
            val password = pwd_edt.text.toString()
            mViewModel.login(name, password)
        }
    }

    override fun createObserver() {
        mViewModel.userLiveData.observe(this, {
            Toast.makeText(mContext, "登录成功", Toast.LENGTH_LONG).show()
            finish()
        })
    }

}