package com.zxn.mvvm.model;


import androidx.annotation.IntDef;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zxn on 2019/4/30.
 */
public interface IApiFactory {

    /**
     * 默认的缓存大小.
     */
    long DEFAULT_MAX_CACHE_SIZE = 10 * 1024 * 1024;

    int MODE_ON_LINE = 0;//线上
    int MODE_ON_TEST = 1;//测试
    int MODE_ON_GRAY = 2;//灰度


    @IntDef({MODE_ON_LINE, MODE_ON_TEST, MODE_ON_GRAY})
    @Retention(RetentionPolicy.SOURCE)
    @interface ModeDef {
    }

    /**
     * 切换开发模式,
     *
     * @param mode
     */
    void setMode(@ModeDef int mode);

    /**
     * 设置缓存的路径.
     *
     * @return File
     */
    File cacheFile();

    /**
     * 设置缓存的最大值.
     *
     * @return long, 存的最大值
     */
    long maxCacheSize();

    /**
     * 控制日志输出.
     *
     * @return true的时候打印日志.
     */
    boolean isShowLog();


}
