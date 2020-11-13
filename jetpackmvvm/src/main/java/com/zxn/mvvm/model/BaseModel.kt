package com.zxn.mvvm.model

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *  Created by zxn on 2020/11/5.
 */
abstract class BaseModel<T> {

    protected abstract var mApiService: T
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

//    constructor() {
//        mApiService = create()
//    }

    protected open fun add(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    open fun clear() {
        mCompositeDisposable.clear()
    }

    //abstract fun<T> create(): T
}