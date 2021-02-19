package com.zxn.mvvm.model

import com.zxn.mvvm.model.http.ResponseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

abstract class BaseModel<T> : IBaseModel<T> {

    override fun clear() {

    }

    /**
     * 线程安全模式执行网络请求调用
     */
    suspend fun <T : Any> safeApiCall(call: suspend () -> ResponseResult<T>, errorMessage: String): ResponseResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResult.Error(errorMessage)
        }
    }

    /**
     * 解析响应结果.
     */
    open suspend fun <T : Any> executeResponse(response: IResponseEntity<T>,
                                               successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                               errorBlock: (suspend CoroutineScope.() -> Unit)? = null): ResponseResult<T> {
        return coroutineScope {
            if (response.succeed()) {
                successBlock?.let { it() }
                ResponseResult.Success(response.getEntity())
            } else {
                errorBlock?.let { it() }
                ResponseResult.Error(response.message)
            }
        }
    }

}