package com.zxn.mvvm.model


/**
 *  Created by zxn on 2020/11/5.
 */
interface IBaseModel<T> {

    var mApiService: T

    fun clear()

}