package com.hao.androidrecord.activity.ZoomRecylerLayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_zoom_rv.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_rv)


        val linearLayoutManager = ZoomRecyclerLayout(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        rv_recycler.layoutManager = linearLayoutManager // Add your recycler view to this ZoomRecycler layout

        rv_recycler.adapter = RecycleZoomAdapter(this)
        

    }
}
