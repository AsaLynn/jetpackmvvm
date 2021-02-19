package com.wanandroid.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wanandroid.R
import com.wanandroid.model.db.dao.ReadHistoryModel

/**
 * Created by Donkey
 * on 2020/8/12
 */
open class MyBaseBindAdapter() : BaseQuickAdapter<ReadHistoryModel, BaseViewHolder>(R.layout.item_my_read_history) {

    override fun convert(helper: BaseViewHolder, item: ReadHistoryModel) {
        helper.setText(R.id.articleTitle,item.title)
        /*helper.binding.run {
            setVariable(_br, item)
            executePendingBindings()
        }*/
    }

//    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
//        val binding = DataBindingUtil.inflate<ViewDataBinding>(mLayoutInflater, layoutResId, parent, false)
//            ?: return super.getItemView(layoutResId, parent)
//        return binding.root.apply {
//            setTag(R.id.BaseQuickAdapter_databinding_support, binding)
//        }
//    }
//
//    class BindViewHolder(view: View) : BaseViewHolder(view) {
//        val binding: ViewDataBinding
//            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
//    }
}
