package com.hao.androidrecord.activity.snaphelper

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.snaphelper.adapter.AppAdapter
import com.hao.androidrecord.activity.snaphelper.model.App


class GridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val adapter = AppAdapter(R.layout.adapter_vertical)

        val apps = arrayListOf<App>()
        repeat(5) {
            apps.addAll(GravitySnapHelperActivity.getApps())
        }

        adapter.setItems(apps)
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}
