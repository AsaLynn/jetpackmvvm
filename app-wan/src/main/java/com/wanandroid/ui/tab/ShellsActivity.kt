package com.wanandroid.ui.tab

import android.content.Context
import com.wanandroid.R
import com.wanandroid.base.MyBaseActivity
import com.zxn.mvvm.ext.jumpInTo
import kotlinx.android.synthetic.main.activity_shells.*

/**
 *  积分.
 *  Created by zxn on 2021/5/19.
 */
class ShellsActivity : MyBaseActivity() {

    companion object {
        @JvmStatic
        fun jumpTo(context: Context, shells: String) {
            jumpInTo<ShellsActivity>(context) {
                putExtra("shells", shells)
            }
        }
    }

    override val layoutResId: Int = R.layout.activity_shells


    override fun onInitView() {

        prominentTabLayout.run {
            setTabData(
                mutableListOf(
                    TabEntity("鱼宠"),
                    TabEntity("道具"),
                    TabEntity("背景"),
                    TabEntity("贝币")
                ), this@ShellsActivity, R.id.flContainer, mutableListOf(
                    DemoAFragment.newInstance(),
                    DemoBFragment.newInstance(),
                    DemoCFragment.newInstance(),
                    DemoDFragment.newInstance(),
                )
            )
        }

        tvBack.run {
            setOnClickListener {
                finish()
            }
        }
    }


}