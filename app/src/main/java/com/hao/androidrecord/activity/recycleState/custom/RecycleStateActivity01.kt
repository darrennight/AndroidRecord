package com.hao.androidrecord.activity.recycleState.custom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_recycle_state_01.*


//https://juejin.cn/post/6934810205968400398
//https://www.jianshu.com/p/27b8126cea4d
class RecycleStateActivity01:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_state_01)
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        rv_recy_state.layoutManager = manager
        rv_recy_state.adapter = RecycleState01Adapter(this)


    }
}