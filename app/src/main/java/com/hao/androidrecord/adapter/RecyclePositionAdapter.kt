package com.hao.androidrecord.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.RecyclePositonActivity
import kotlinx.android.synthetic.main.item_position_content.view.*
import kotlinx.android.synthetic.main.item_positon_title.view.*

class RecyclePositionAdapter(val context: Context,val list:ArrayList<RecyclePositonActivity.TypeData>)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 0){
            return RecyclePositionTitleHolder(LayoutInflater.from(context).inflate(R.layout.item_positon_title,parent,false))
        }else{
            return RecyclePositionContentHolder(LayoutInflater.from(context).inflate(R.layout.item_position_content,parent,false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
     if (holder is RecyclePositionTitleHolder ){
         holder.itemView.tv_title.text = data.content
     }else if (holder is RecyclePositionContentHolder){
         holder.itemView.tv_content.text = data.content
     }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position].isTitle){
            return 0
        }else{
           return 1
        }
    }
    inner class RecyclePositionTitleHolder(itemView: View):ViewHolder(itemView)
    inner class RecyclePositionContentHolder(itemView: View):ViewHolder(itemView)
}