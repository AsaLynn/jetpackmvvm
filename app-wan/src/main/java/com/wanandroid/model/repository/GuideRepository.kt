package com.wanandroid.model.repository


import com.wanandroid.model.http.WanRetrofitClient
import com.wanandroid.model.resultbean.Guide
import com.wanandroid.model.http.BaseRepository
import com.zxn.mvvm.model.http.ResponseResult

/**
 * Created by Donkey
 * on 2020/8/20
 */
class GuideRepository : BaseRepository() {
    suspend fun getGuide(): ResponseResult<List<Guide>> {
        return safeApiCall(call = { requestGuideList() }, errorMessage = "")
    }

    private suspend fun requestGuideList(): ResponseResult<List<Guide>> {
        val response = WanRetrofitClient.service.getGuide()

        return executeResponse(response)

    }
}