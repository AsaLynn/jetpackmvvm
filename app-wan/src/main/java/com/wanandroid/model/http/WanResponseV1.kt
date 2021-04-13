package com.wanandroid.model.http

import com.zxn.mvvm.model.IResponseEntity

/**
 * Created by zxn on 2021/2/5.
 */
data class WanResponseV1<T>(val errorCode: Int, val errorMsg: String, val data: T) : IResponseEntity<T> {

    override fun succeed(): Boolean = errorCode == -1

//    override var dataEntity: T = data

//    override val message: String = errorMsg

//    override val code: Int = errorCode

    override fun entity(): T = data

    override fun code(): Int = errorCode

    override fun message(): String = errorMsg

}

//data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)
