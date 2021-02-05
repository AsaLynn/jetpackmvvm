package com.zxn.mvvm.event

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zxn.mvvm.model.http.LoadingModel
import java.util.concurrent.atomic.AtomicBoolean

/**
 *
 *  Created by zxn on 2021/1/19.
 */
class LoadingLiveEvent : MutableLiveData<LoadingModel<Any>>() {

    private val TAG = "LoadingLiveEvent"

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in LoadingModel<Any>>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        super.observe(owner, {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(t: LoadingModel<Any>?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}