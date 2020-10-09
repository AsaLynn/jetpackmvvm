package com.zxn.mvvm.demo;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * Created by zxn on 2019/4/2.
 */
class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MainAdapter() {
        super(R.layout.item_main);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
