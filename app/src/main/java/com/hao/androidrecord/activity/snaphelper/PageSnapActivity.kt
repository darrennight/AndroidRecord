package com.hao.androidrecord.activity.snaphelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.snaphelper.adapter.LinearSnapHoriAdapter
import kotlinx.android.synthetic.main.activity_linear_veri.*

/**
 * item居中，控制item屏幕中间
 * 垂直
 */
class PageSnapActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_veri)
        rv_snap_ver.adapter = LinearSnapHoriAdapter(this)


        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        rv_snap_ver.setLayoutManager(manager)
        // 将SnapHelper attach 到RecyclrView
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_snap_ver)
    }
}