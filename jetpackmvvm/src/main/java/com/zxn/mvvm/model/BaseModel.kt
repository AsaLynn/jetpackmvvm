package com.zxn.mvvm.model

import com.zxn.mvvm.model.http.ResponseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * Copyright(c) ${}YEAR} ZhuLi co.,Ltd All Rights Reserved.
 *
 * @className: BaseModel$
 * @description: TODO 类描述
 * @version: v0.0.1
 * @author: zxn < a href=" ">zhangxiaoning@17biyi.com</ a>
 * @date: 2021/2/5$ 21:09$
 * @updateUser: 更新者：
 * @updateDate: 2021/2/5$ 21:09$
 * @updateRemark: 更新说明：
 * @version: 1.0
 * */
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
                errorBlock?.let { it() }
                ResponseResult.Error(response.message)
            } else {
                successBlock?.let { it() }
                ResponseResult.Success(response.dataEntity)
            }
        }
    }
}