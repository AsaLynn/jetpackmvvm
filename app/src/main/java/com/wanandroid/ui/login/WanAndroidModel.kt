package com.wanandroid.ui.login

import android.util.Log
import com.wanandroid.model.http.ResponseResult
import com.wanandroid.model.http.WanResponse
import com.wanandroid.model.http.WanRetrofitClient
import com.wanandroid.model.http.WanService
import com.wanandroid.model.repository.LoginRepository
import com.wanandroid.model.resultbean.User
import com.zxn.mvvm.model.IBaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * MyLoginModel
 * WanAndroidModel
 */
class WanAndroidModel : IBaseModel<WanService> {

    private val TAG: String = LoginRepository::class.java.simpleName

    override var mApiService: WanService = WanRetrofitClient.service

    override fun clear() {

    }

    suspend fun login(userName: String, passWord: String): ResponseResult<User> {
        return safeApiCall(call = { requestLogin(userName, passWord) }, errorMessage = "")
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> ResponseResult<T>, errorMessage: String): ResponseResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            ResponseResult.Error(errorMessage)
        }
    }

    private suspend fun requestLogin(userName: String, passWord: String): ResponseResult<User> {
        val response = WanRetrofitClient.service.login(userName, passWord)

        return executeResponse(response, {
            Log.d(TAG, "请求成功的回调  $response")
        }, { Log.d(TAG, "请求失败的回调  $response") })

    }

    suspend fun <T : Any> executeResponse(response: WanResponse<T>,
                                          successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null): ResponseResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                ResponseResult.Error(response.errorMsg)
            } else {
                successBlock?.let { it() }
                ResponseResult.Success(response.data)
            }
        }
    }

}