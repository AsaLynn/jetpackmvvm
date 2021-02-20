package com.wanandroid.ui.question

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanandroid.App
import com.wanandroid.R
import com.wanandroid.adapter.MyQuestionAdapter
import com.wanandroid.binding.isRefresh
import com.zxn.mvvm.view.BaseFragment
import kotlinx.android.synthetic.main.question_project.*

/**
 * MyQuestionViewModel
 * MyQuestionFragment
 * A simple [Fragment] subclass.
 */
class MyQuestionFragment : BaseFragment<MyQuestionViewModel>() {

    private val questionArticleAdapter by lazy { MyQuestionAdapter() }

    override fun lazyLoadData() {

    }

    override fun registerEventBus(isRegister: Boolean) {

    }

    override val layoutResId: Int = R.layout.fragment_my_question

    override fun onInitView() {
        questionRecycleView.run {
            layoutManager = LinearLayoutManager(mContext)
            setHasFixedSize(true)
            adapter = questionArticleAdapter
        }
        questionRefreshLayout.setOnRefreshListener {
            questionArticleAdapter.setEnableLoadMore(false)
            refresh()
        }
        search_btn.setOnClickListener {
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
        //articleViewModel.getQuestionList(true)
        mViewModel.getQuestionList(true)
    }
}


//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

//private var param1: String? = null
//private var param2: String? = null

//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    arguments?.let {
//        param1 = it.getString(ARG_PARAM1)
//        param2 = it.getString(ARG_PARAM2)
//    }
//}
//companion object {
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MyQuestionFragment.
//     */
//    @JvmStatic
//    fun newInstance(param1: String, param2: String) =
//            MyQuestionFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//}