package com.wanandroid

import android.content.Intent
import com.wanandroid.base.MyBaseActivity


/**
 * Created by Donkey
 * on 2020/9/15
 */
class SplashActivity : MyBaseActivity/*<Nothing>*/() {

//    override fun getLayoutResId() = R.layout.activity_splash

    override fun initView() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initData() {
    }

    override val layoutResId: Int = R.layout.activity_splash
//
//    override fun onInitView() {
//        val intent = Intent(this@SplashActivity, MainActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
}