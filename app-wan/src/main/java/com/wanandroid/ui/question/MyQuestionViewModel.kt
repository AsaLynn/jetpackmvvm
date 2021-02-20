package com.wanandroid.ui.question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.model.resultbean.ArticleList
import com.wanandroid.model.resultbean.User
import com.wanandroid.ui.first.ArticleViewModel
import com.wanandroid.ui.login.WanAndroidModel
import com.zxn.mvvm.model.http.LoadingModel
import com.zxn.mvvm.model.http.ResponseResult
import com.zxn.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  Created by ny on 2021/2/20.
 */
class MyQuestionViewModel : BaseViewModel<WanAndroidModel>() {

    private var currentPage = 0
    val mLoadingDataEvent = MutableLiveData<LoadingModel<ArticleList?>>()

    fun getQuestionList(isRefresh: Boolean = false) = getArticleList(ArticleViewModel.ArticleType.Question, isRefresh)

    private fun getArticleList(articleType: ArticleViewModel.ArticleType, isRefresh: Boolean = false, id: Int = 0, searchKey: String = "") {
        //刷新下拉的loading
        emitLoading<ArticleList>(isLoading = currentPage == 0).run {
            mLoadingDataEvent.postValue(this)
        }

        if (isRefresh) {
            //下拉刷新时将currentPage置0  //获取公众号数据页数是从1开始的
            currentPage = 0
        }
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                mModel?.getQuestionList(currentPage)
            }
            if (result is ResponseResult.Success) {
                currentPage++
                val articleList = result.data

                if (articleList.offset >= articleList.total) { //已经是最后一页了
                    emitLoading<ArticleList>(isLoading = false, successData = articleList, showEnd = true, isRefresh = isRefresh).run {
                        mLoadingDataEvent.postValue(this)
                    }
                    return@launch
                }
                emitLoading(isLoading = false, successData = articleList, isRefresh = isRefresh).run {
                    mLoadingDataEvent.postValue(this)
                }

            } else if (result is ResponseResult.Error) {
                emitLoading<ArticleList>(isLoading = false, showError = "").run {
                    mLoadingDataEvent.postValue(this)
                }
            }
        }
    }
}