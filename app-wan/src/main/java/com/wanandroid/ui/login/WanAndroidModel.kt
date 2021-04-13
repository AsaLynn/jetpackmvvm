package com.wanandroid.ui.login

import android.util.Log
import com.wanandroid.model.http.WanRetrofitClient
import com.wanandroid.model.http.WanService
import com.wanandroid.model.repository.LoginRepository
import com.wanandroid.model.resultbean.ArticleList
import com.wanandroid.model.resultbean.User
import com.zxn.mvvm.model.BaseModel
import com.zxn.mvvm.model.IResponseEntity
import com.zxn.mvvm.model.http.ResponseResult

/**
 * MyLoginModel
 * WanAndroidModel.
 */
class WanAndroidModel : BaseModel<WanService>() {

    private val TAG: String = LoginRepository::class.java.simpleName

    override var mApiService: WanService = WanRetrofitClient.service

    suspend fun login(userName: String, passWord: String): ResponseResult<out User> {
        return safeApiCall(call = { requestLogin(userName, passWord) }, errorMessage = "")
    }

    private suspend fun requestLogin(userName: String, passWord: String): ResponseResult<out User> {
        val response = WanRetrofitClient.service.loginV1(userName, passWord)
        return executeResponse(response, {
            Log.d(TAG, "请求成功的回调  $response")
        }, { Log.d(TAG, "请求失败的回调  $response") })
        //return executeResponse(response, null, null)
    }

    suspend fun getQuestionList(page: Int): ResponseResult<out ArticleList> {
        return safeApiCall(call = { requestQuestionList(page) }, errorMessage = "")
    }

    private suspend fun requestQuestionList(page: Int): ResponseResult<out ArticleList> {
        val response = WanRetrofitClient.service.getQuestionsV1(page)
        return executeResponse(response)
    }

//    override suspend fun <T : Any> onExecuteResponse(response: IResponseEntity<T>): ResponseResult<T> {
//        if (response.succeed()) {
//            successBlock?.let { it() }
//            ResponseResult.Success(response.entity())
//        } else {
//            errorBlock?.let { it() }
//            ResponseResult.Error(response.message)
//        }
//    }

//    override suspend fun <T : Any> onExecuteResponse(): ResponseResult<T> {
//        if (response.succeed()) {
//            successBlock?.let { it() }
//            ResponseResult.Success(response.getEntity())
//        } else {
//            errorBlock?.let { it() }
//            ResponseResult.Error(response.message)
//        }
//    }

}


//suspend fun <T : Any> executeResponse(response: WanResponse<T>,
//                                      successBlock: (suspend CoroutineScope.() -> Unit)? = null,
//                                      errorBlock: (suspend CoroutineScope.() -> Unit)? = null): ResponseResult<T> {
//    return coroutineScope {
//        if (response.errorCode == -1) {
//            errorBlock?.let { it() }
//            ResponseResult.Error(response.errorMsg)
//        } else {
//            successBlock?.let { it() }
//            ResponseResult.Success(response.data)
//        }
//    }
//}

//private suspend fun requestLogin(userName: String, passWord: String): ResponseResult<User> {
//    val response = WanRetrofitClient.service.login(userName, passWord)
//
//    /*return executeResponse(response, {
//        Log.d(TAG, "请求成功的回调  $response")
//    }, { Log.d(TAG, "请求失败的回调  $response") })*/
//
//    return executeResponse(response, null, null)
//}

//suspend fun <T : Any> safeApiCall(call: suspend () -> ResponseResult<T>, errorMessage: String): ResponseResult<T> {
//    return try {
//        call()
//    } catch (e: Exception) {
//        ResponseResult.Error(errorMessage)
//    }
//}

