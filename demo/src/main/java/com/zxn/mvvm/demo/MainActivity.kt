package com.zxn.mvvm.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
//import com.zxn.mvvm.demo.base.BaseDemoActivity
//import com.zxn.mvvm.demo.viewmodel.MainViewModel
import com.zxn.mvvm.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity()/*BaseActivity<MainViewModel>()*//*BaseDemoActivity<MainViewModel>()*/ {
//    override var usedEventBus: Boolean = true
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        rv.layoutManager = LinearLayoutManager(this)
//        rv.setHasFixedSize(true)
//        val adapter = MainAdapter()
//        val data = ArrayList<String>()
//        for (i in 0..9) {
//            data.add("this is item:$i")
//        }
//        adapter.setList(data)
//        rv.adapter = adapter
//        adapter.setOnItemClickListener { adapter, view, position ->
//            startActivity(Intent(this, TestActivity::class.java))
//        }
//
//        mViewModel.getData()
//
//
//    }
//
//
//    override val layoutResId: Int = R.layout.activity_main
//
//
//    override fun createObserver() {
//
//    }
//
//    override fun registerEventBus(isRegister: Boolean) {
//
//    }

}