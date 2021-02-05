package com.wanandroid.model.repository

import com.wanandroid.model.http.WanRetrofitClient
import com.wanandroid.model.resultbean.ArticleList
import com.wanandroid.model.resultbean.BlogType
import com.wanandroid.model.http.BaseRepository
import com.zxn.mvvm.model.http.ResponseResult

/**
 * Created by Donkey
 * on 2020/9/5
 */
class BlogRepository : BaseRepository() {

    suspend fun getBlogType(): ResponseResult<List<BlogType>> {
        return safeApiCall(call = { requestBlogType() }, errorMessage = "")
    }

    suspend fun getBlogArticleList(page :Int,id: Int):ResponseResult<ArticleList> {
        return  safeApiCall( call = {requestBlogArticleList(page,id)},errorMessage = "")
    }

    private suspend fun requestBlogArticleList(page :Int,id: Int):ResponseResult<ArticleList> {
        val response = WanRetrofitClient.service.getBlogArticleList(page,id)

        return executeResponse(response)
    }
    private suspend fun requestBlogType(): ResponseResult<List<BlogType>> {
        val response = WanRetrofitClient.service.getBlogType()

        return executeResponse(response)

    }
}