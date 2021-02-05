package com.zxn.mvvm.model.http

/**
 * Created by zxn on 2021/2/5.
 */
data class LoadingModel<T>(
        val isLoading: Boolean = false,
        val showError: String? = null,
        val successData: T? = null,
)