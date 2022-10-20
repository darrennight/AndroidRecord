package com.hao.androidrecord.activity.flexbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.flowRecycle.ShowItem
import kotlinx.android.synthetic.main.item_flex_recycle.view.*

class FlexRecycleAdapter(val context: Context,val list:List<ShowItem>):RecyclerView.Adapter<FlexRecycleAdapter.FlexRecycleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexRecycleHolder {
        return FlexRecycleHolder(LayoutInflater.from(context).inflate(R.layout.item_flex_recycle,parent,false))
    }

    override fun onBindViewHolder(holder: FlexRecycleHolder, position: Int) {
        holder.itemView.apply {
            item_text.text = list[position].des
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FlexRecycleHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}