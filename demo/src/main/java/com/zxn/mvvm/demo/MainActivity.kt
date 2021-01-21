package com.zxn.mvvm.demo

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zxn.mvvm.demo.viewmodel.MainViewModel
import com.zxn.mvvm.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *  Created by zxn on 2021/1/14.
 */
class MainActivity : BaseActivity<MainViewModel>() {

    override var usedEventBus: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        val adapter = MainAdapter()
        val data = ArrayList<String>()
        for (i in 0..9) {
            data.add("this is item:$i")
        }
        adapter.setList(data)
        rv.adapter = adapter
        adapter.setOnItemClickListener { _, view, position ->
            //showToast(adapter.data[position])
            //mViewModel.showToast("showToast")
            //startActivity(Intent(this, TestActivity::class.java))
            MainDgFragment.newInstance().show(supportFragmentManager)
        }

        mViewModel.getData()
    }

    override val layoutResId: Int = R.layout.activity_main

    override fun onInitView() {

    }


    override fun createObserver() {

    }

    override fun registerEventBus(isRegister: Boolean) {

    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}