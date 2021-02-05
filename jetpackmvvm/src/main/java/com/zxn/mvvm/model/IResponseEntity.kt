package com.zxn.mvvm.model

/**
 * Created by zxn on 2021/2/5.
 */
interface IResponseEntity<T> {

    /**
     *判断请求的结果状态.
     * @return true:网络数据请求成功,false:请求失败.
     */
    fun succeed(): Boolean

    /**
     * 实际要使用的数据,抽象到接口层面.
     * 实现该接口的时候,将实体类的抽象字段和该抽象字段指定为同一个即可.
     */
    val dataEntity: T

    /**
     * 响应结果信息描述
     */
    val message: String


}