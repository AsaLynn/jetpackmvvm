package com.zxn.mvvm.demo

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : MvpActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv!!.layoutManager = LinearLayoutManager(this)
        rv!!.setHasFixedSize(true)
        val adapter = MainAdapter()
        val data = ArrayList<String>()
        for (i in 0..9) {
            data.add("this is item:$i")
        }
        adapter.setNewData(data)
        rv!!.adapter = adapter
    }

    override fun getLayoutResId(): Int = R.layout.activity_main
}