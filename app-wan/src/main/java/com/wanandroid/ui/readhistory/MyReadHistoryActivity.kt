package com.wanandroid.ui.readhistory

import android.content.Context
import android.content.Intent
import com.wanandroid.R
import com.wanandroid.adapter.MyBaseBindAdapter
import com.zxn.mvvm.view.BaseActivity
import kotlinx.android.synthetic.main.activity_read_history.*

/**
 *
 * MyReadHistoryViewModel
 * 阅读历史新版本.
 */
class MyReadHistoryActivity : BaseActivity<MyReadHistoryViewModel>() {

    companion object {
        @JvmStatic
        fun jumpTo(context: Context) {
            context.startActivity(Intent(context, MyReadHistoryActivity::class.java))
        }
    }

    //private val readHistoryViewModel by viewModel<ReadHistoryViewModel>()

    private val readHistoryAdapter by lazy { MyBaseBindAdapter() }

    override fun registerEventBus(isRegister: Boolean) {

    }

    override val layoutResId: Int = R.layout.activity_my_read_history

    override fun onInitView() {
        readHistoryRefreshLayout.setOnRefreshListener {
            //readHistoryAdapter.setEnableLoadMore(false)
            refresh()
        }
        back_bt.setOnClickListener {
            finish()
        }
        clear_bt.setOnClickListener {
            //readHistoryViewModel.clearAllHistory()
        }
        refresh()
    }

    override fun createObserver() {
        mViewModel.mLoadingDataEvent.observe(this, {
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

    private fun refresh() {
        mViewModel.findAllHistory(true)
    }
}