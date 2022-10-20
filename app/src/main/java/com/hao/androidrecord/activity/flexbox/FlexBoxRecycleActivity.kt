package com.hao.androidrecord.activity.flexbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.flowRecycle.DataConfig
import kotlinx.android.synthetic.main.activity_flex_recycler.*

//https://www.jianshu.com/p/8060f7623f1c
class FlexBoxRecycleActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_recycler)


        val manager = FlexboxLayoutManager(this)
        //设置主轴排列方式
        manager.flexDirection = FlexDirection.ROW
        //设置是否换行
        manager.flexWrap = FlexWrap.WRAP

        rv_flex.setLayoutManager(manager)
        rv_flex.setItemAnimator(DefaultItemAnimator())
        rv_flex.adapter = FlexRecycleAdapter(this,DataConfig.getItems())
    }
}