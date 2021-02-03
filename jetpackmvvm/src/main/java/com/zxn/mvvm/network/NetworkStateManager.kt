package com.zxn.mvvm.network

import com.zxn.mvvm.event.EventLiveData

/**
 * 描述　: 网络变化管理者
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}

/**
 * 描述　: 网络变化实体类
 */
class NetState(
        var isSuccess: Boolean = true
)