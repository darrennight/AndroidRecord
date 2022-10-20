package com.hao.androidrecord.activity.expandable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_expandable_recycle.*
import kotlinx.android.synthetic.main.activity_main.*

class ExpandableRecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_recycle)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ItemAdapter(layoutManager)
    }
}
