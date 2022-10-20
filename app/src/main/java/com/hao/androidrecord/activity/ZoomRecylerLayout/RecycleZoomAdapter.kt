package com.hao.androidrecord.activity.ZoomRecylerLayout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R

class RecycleZoomAdapter(val context: Context):RecyclerView.Adapter<RecycleZoomAdapter.RecycleZoomHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleZoomHolder {
        return RecycleZoomHolder(LayoutInflater.from(context).inflate(R.layout.item_zoom_recycle,parent,false))
    }

    override fun onBindViewHolder(holder: RecycleZoomHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }

    class RecycleZoomHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}