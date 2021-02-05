package com.wanandroid.model.http

import com.zxn.mvvm.model.IResponseEntity

/**
 * Created by zxn on 2021/2/5.
 */
data class WanResponseV1<T>(val errorCode: Int, val errorMsg: String, val data: T) : IResponseEntity<T> {

    //, val data: DATA
    override fun succeed(): Boolean = errorCode == 0

    override val dataEntity: T = data

    override val message: String = errorMsg

    override val code: Int = errorCode


}
