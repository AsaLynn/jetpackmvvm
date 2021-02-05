package com.wanandroid.model.http

/**
 *响应结果1
 */
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)


