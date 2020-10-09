package com.zxn.mvvm.demo;

import com.zxn.mvvm.presenter.IView;

/**
 * Created by zxn on 2019/6/30.
 */
public class OrderBillPresenter extends MainPresenter<OrderBillPresenter.IOrderBillView> {


    public interface IOrderBillView extends IView {
        void onBillAll();
    }

//    @Override
//    public List<Object> getData() {
//        return super.getData();
//    }
}
