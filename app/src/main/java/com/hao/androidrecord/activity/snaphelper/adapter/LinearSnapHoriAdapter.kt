package com.hao.androidrecord.activity.snaphelper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R

class LinearSnapHoriAdapter(val context: Context):RecyclerView.Adapter<LinearSnapHoriAdapter.LinearSnapVerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearSnapVerHolder {
        return LinearSnapVerHolder(LayoutInflater.from(context).inflate(R.layout.item_lin_snap_hor,parent,false))
    }

    override fun onBindViewHolder(holder: LinearSnapVerHolder, position: Int) {
        holder.itemView.apply {

        }
    }

    override fun getItemCount(): Int {
        return 20
    }

    class LinearSnapVerHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}