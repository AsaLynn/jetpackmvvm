package com.zxn.mvvm.demo

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Created by zxn on 2019/4/2.
 */
class MainAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main) {

    override fun convert(helper: BaseViewHolder, item: String) {}
}