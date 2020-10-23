package com.zxn.mvvm.demo

import android.os.Bundle
import com.zxn.mvvm.view.BaseActivity

class TestActivity : BaseActivity<Nothing>() {

    companion object {
        @JvmStatic
        fun jumpTo(){
            //ARouter.getInstance().build(RouterConfig.MALLMAIN_ACTIVITY).navigation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    override val layoutResId: Int
        get() = R.layout.activity_test

    override fun createObserver() {

    }

    override fun registerEventBus(isRegister: Boolean) {

    }
}