package com.wanandroid.ui.question

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanandroid.App
import com.wanandroid.R
import com.wanandroid.adapter.MyQuestionAdapter
import com.wanandroid.base.MyBaseFragment
import com.wanandroid.binding.isRefresh
import com.wanandroid.databinding.ActivityMyLoginBinding
import com.wanandroid.databinding.FragmentMyQuestionBinding
import com.wanandroid.ui.readhistory.MyReadHistoryViewModel
import com.zxn.mvvm.view.BaseFragment
import kotlinx.android.synthetic.main.question_project.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyQuestionFragment : MyBaseFragment() {

    private val mViewBinding by lazy {
        FragmentMyQuestionBinding.inflate(layoutInflater)
    }

    override val layoutRoot: View by lazy { mViewBinding.root }

    private val questionArticleAdapter by lazy { MyQuestionAdapter() }

    private val mViewModel by viewModel<MyQuestionViewModel>()

    override fun lazyLoadData() {

    }

    override fun registerEventBus(isRegister: Boolean) {

    }

//    override val layoutResId: Int = R.layout.fragment_my_question

    override fun onInitView() {

        mViewBinding.questionRecycleView.run {
            layoutManager = LinearLayoutManager(mContext)
            setHasFixedSize(true)
            adapter = questionArticleAdapter
        }
        mViewBinding.questionRefreshLayout.setOnRefreshListener {
            questionArticleAdapter.setEnableLoadMore(false)
            refresh()
        }
        mViewBinding.searchBtn.setOnClickListener {
            NavHostFragment.findNavController(this@MyQuestionFragment)
                    .navigate(R.id.searchActivity)
        }
        refresh()
    }

    override fun createObserver() {
        mViewModel.mLoadingDataEvent.observe(viewLifecycleOwner, {
            questionRefreshLayout.isRefresh(it.isLoading)
            it.successData?.let { list ->

                questionArticleAdapter.run {
                    setEnableLoadMore(false)
                    if (it.isRefresh) {
                        replaceData(list.datas)
                    } else {
                        addData(list.datas)
                    }
                    setEnableLoadMore(true)
                    loadMoreComplete()
                }
            }

            if (it.showEnd) questionArticleAdapter.loadMoreEnd()

            it.showError?.let { message ->
                Toast.makeText(App.getContext(), if (message.isBlank()) "网络异常" else message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onLoading(isLoading: Boolean) {

    }

    private fun refresh() {
        mViewModel.getQuestionList(true)
    }
}