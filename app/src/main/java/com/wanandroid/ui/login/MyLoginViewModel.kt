package com.wanandroid.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wanandroid.model.http.ResponseResult
import com.wanandroid.model.resultbean.User
import com.wanandroid.util.SharedPreferencesData
import com.zxn.mvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * MyLoginModel
 */
class MyLoginViewModel : BaseViewModel<WanAndroidModel>() {

    val userLiveData = MutableLiveData<User>()

    /**
     *登录.
     */
    fun login(name: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                mModel?.login(name, password)
            }
            if (result is ResponseResult.Success) {
                Log.d("MyLoginViewModel", result.data.toString())
                val user = result.data
                SharedPreferencesData.name = user.username
                SharedPreferencesData.password = user.password
                SharedPreferencesData.isLogin = true
                //emitLoginModel(isLoading = false, successData = user, isLogin = true)
                userLiveData.postValue(user)
            } else if (result is ResponseResult.Error) {
                SharedPreferencesData.isLogin = false
                //emitLoginModel(isLoading = false,showError=result.errorMsg,isLogin=true)
            }
        }
    }
}