package com.zxn.mvvm.ext

import android.content.Context
import android.content.Intent

/**
 *  跳转Activity方法.
 *  Created by zxn on 2020/12/26.
 */
inline fun <reified T> jumpInTo(tContext: Context, block: Intent.() -> Unit) {
    val intent = Intent(tContext, T::class.java)
    intent.block()
    tContext.startActivity(intent)
}