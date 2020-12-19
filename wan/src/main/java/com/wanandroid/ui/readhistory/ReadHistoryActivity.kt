package com.wanandroid.ui.readhistory

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.wanandroid.BR
import com.wanandroid.BrowserActivity
import com.wanandroid.R
import com.wanandroid.adapter.BaseBindAdapter
import com.wanandroid.databinding.ActivityReadHistoryBinding
import com.wanandroid.model.db.dao.ReadHistoryModel
import com.wanandroid.view.CustomLoadMoreView
import com.zxn.mvvm.view.BaseActivity
import kotlinx.android.synthetic.main.activity_read_history.*

class ReadHistoryActivity : BaseActivity<ReadHistoryViewModel>() {

    override var usedViewBinding: Boolean = true

    private val binding by binding<ActivityReadHistoryBinding>()

    private val readHistoryAdapter by lazy { BaseBindAdapter<ReadHistoryModel>(R.layout.item_read_history, BR.readHistory) }

//    private val readHistoryViewModel by viewModel<ReadHistoryViewModel>()

    fun initView() {
        binding.run {
//            viewModel = readHistoryViewModel
            viewModel = mViewModel
            adapter = readHistoryAdapter
        }
        readHistoryAdapter.run {

            setOnItemClickListener { _, _, position ->
                val bundle = Bundle()
                bundle.putString(BrowserActivity.URL, readHistoryAdapter.data[position].link)
                bundle.putString(BrowserActivity.TITLE, readHistoryAdapter.data[position].title)
                val intent = Intent(this@ReadHistoryActivity, BrowserActivity::class.java)
                intent.putExtras(bundle);
                startActivity(intent);
            }
            setLoadMoreView(CustomLoadMoreView())//添加上拉加载更多布局
            setOnLoadMoreListener({ loadMore() }, readHistoryRecycleView) //上拉加载更多
        }
        readHistoryRefreshLayout.setOnRefreshListener {
            readHistoryAdapter.setEnableLoadMore(false)
            refresh()
        }
        back_bt.setOnClickListener {
            finish()
        }
        clear_bt.setOnClickListener {
            //readHistoryViewModel.clearAllHistory()
            mViewModel.clearAllHistory()
        }
    }

    private fun loadMore() {
        //readHistoryViewModel.findAllHistory(false)
        mViewModel.findAllHistory(false)
    }


    private fun refresh() {
        //readHistoryViewModel.findAllHistory(true)
        mViewModel.findAllHistory(true)
    }


    fun initData() {
        refresh()
    }

    fun startObserve() {
        //readHistoryViewModel
        mViewModel.uiState.observe(this@ReadHistoryActivity, Observer {
            it.successData?.let { list ->
                readHistoryAdapter.run {
                    setEnableLoadMore(false)
                    if (it.isRefresh) {
                        replaceData(list)
                    } else {
                        addData(list)
                    }
                    setEnableLoadMore(true)
                    loadMoreComplete()
                }
                if (it.showEnd) readHistoryAdapter.loadMoreEnd()
            }
        })
    }

    override fun registerEventBus(isRegister: Boolean) {

    }

    override val layoutResId: Int = R.layout.activity_read_history

    override fun onInitView() {
        initView()
        initData()
    }

    override fun createObserver() {
        startObserve()
    }

}