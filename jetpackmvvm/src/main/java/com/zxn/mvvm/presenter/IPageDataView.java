package com.zxn.mvvm.presenter;

import java.util.List;

/**
 * Created by zxn on 2019/7/11.
 */
public interface IPageDataView<T> extends IView {

    void onLoadPageData(boolean isRefresh, List<T> dataList);

}
