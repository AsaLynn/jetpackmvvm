package com.wanandroid.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wanandroid.R
import com.wanandroid.model.db.dao.ReadHistoryModel
import com.wanandroid.model.resultbean.Article

/**
 * Created by Donkey
 * on 2020/8/12
 */
open class MyQuestionAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_my_read_history) {

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.articleTitle,item.title)
        /*helper.binding.run {
            setVariable(_br, item)
            executePendingBindings()
        }*/
    }

}
