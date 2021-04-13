package com.wanandroid.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.model.resultbean.User
import com.wanandroid.util.SharedPreferencesData
import com.zxn.mvvm.model.http.LoadingModel
import com.zxn.mvvm.model.http.ResponseResult
import com.zxn.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * MyLoginModel
 */
class MyLoginViewModel : BaseViewModel<WanAndroidModel>() {

    val mLoadingDataEvent = MutableLiveData<LoadingModel<User?>>()

    /**
     *登录.LoadingModel
     */
    fun login(name: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            emitLoading<User>(isLoading = true).run {
                mLoadingDataEvent.postValue(this)
            }
            val result = withContext(Dispatchers.IO) {
                mModel?.login(name, password)
            }

            when (result) {
                is ResponseResult.Success -> {
                    Log.d("MyLoginViewModel", result.data.toString())
                    val user: User = result.data
                    user?.let {
                        SharedPreferencesData.name = user.username
                        SharedPreferencesData.password = user.password
                    }
                    SharedPreferencesData.isLogin = true
                    emitLoading(isLoading = false, successData = user).run {
                        mLoadingDataEvent.postValue(this)
                    }
                }

                is ResponseResult.BaseSuccess -> {
                    SharedPreferencesData.isLogin = false
                    emitLoading<User>(isLoading = false, showError = result.data.message()).run {
                        mLoadingDataEvent.postValue(this)
                    }
                }

                is ResponseResult.Error -> {
                    SharedPreferencesData.isLogin = false
                    emitLoading<User>(isLoading = false, showError = result.errorMsg).run {
                        mLoadingDataEvent.postValue(this)
                    }
                }
            }

        }
    }
}
