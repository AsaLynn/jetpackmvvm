package com.zxn.mvvm.model

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by zxn on 2019/4/30.
 */
interface IApiFactory {
    @IntDef(MODE_ON_LINE, MODE_ON_TEST, MODE_ON_GRAY)
    @Retention(RetentionPolicy.SOURCE)
    annotation class ModeDef

    /**
     * 切换开发模式,
     *
     * @param mode
     */
    fun setMode(@ModeDef mode: Int)

    companion object {
        /**
         * 默认的缓存大小.
         */
        const val DEFAULT_MAX_CACHE_SIZE = 10 * 1024 * 1024.toLong()
        const val MODE_ON_LINE = 0 //线上
        const val MODE_ON_TEST = 1 //测试
        const val MODE_ON_GRAY = 2 //灰度
    }
}