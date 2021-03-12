package com.hao.androidrecord.activity.nine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.nine2.GlideUtil
import kotlinx.android.synthetic.main.activity_nine_main.*

class NineGridNewActivity:AppCompatActivity() {
    private lateinit var mData: MutableList<String>
    private lateinit var mAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nine_main)

        GlideUtil.init(this)

        mData = mutableListOf()
        mAdapter = MainAdapter(mData)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = mAdapter

        tv_get.setOnClickListener { v ->
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mData.add("")
            mAdapter.notifyDataSetChanged()
            tv_get.text = String.format("当前item数:%s\n点击获取更多", mData.size)
        }
    }
}