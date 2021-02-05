package com.zxn.mvvm.model.http

/**
 * 请求的结果.
 */
sealed class ResponseResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ResponseResult<T>()

    data class Error(val errorMsg: String) : ResponseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[errorMsg=$errorMsg]"
        }
    }
}
