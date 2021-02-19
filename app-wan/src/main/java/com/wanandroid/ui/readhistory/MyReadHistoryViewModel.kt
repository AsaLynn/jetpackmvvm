package com.wanandroid.ui.readhistory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.App
import com.wanandroid.model.db.WanDatabase
import com.wanandroid.model.db.dao.ReadHistoryModel
import com.wanandroid.ui.login.WanAndroidModel
import com.zxn.mvvm.model.http.LoadingModel
import com.zxn.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  Created by ny on 2021/2/19.
 */
class MyReadHistoryViewModel : BaseViewModel<WanAndroidModel>() {

    val mLoadingDataEvent = MutableLiveData<LoadingModel<List<ReadHistoryModel>?>>()

    private var currentPage = 0
    private var currentSize = 10

    fun findAllHistory(isRefresh: Boolean) {
        emitLoading<List<ReadHistoryModel>?>(isLoading = isRefresh).run {
            mLoadingDataEvent.postValue(this)
        }
        if (isRefresh) {
            currentPage = 0
        }
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                WanDatabase.getInstance(App.getContext()).readHistoryDao().findAll(currentPage, currentSize)
            }
            currentPage++
            emitLoading<Any>(isLoading = false, successData = result, showEnd = (result.size < currentSize), isRefresh = isRefresh)
            Log.d("findAllHistory", result.size.toString())
        }
    }

}