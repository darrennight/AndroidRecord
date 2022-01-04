package com.hao.androidrecord.activity.autoscroll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.item_txt.view.*

class ItemAutoAdapter(val context: Context, val data: ArrayList<String>):RecyclerView.Adapter<ItemAutoAdapter.ItemAutoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAutoHolder {
       return ItemAutoHolder(LayoutInflater.from(context).inflate(R.layout.item_txt,parent,false))
    }

    override fun onBindViewHolder(holder: ItemAutoHolder, position: Int) {
        holder.itemView.apply {
            mTv.setText(data[position % data.size])
        }
    }

    override fun getItemViewType(position: Int): Int {

        var count = data.size
        if(count<=0){
            count = 1
        }
        var newPosition = position % count

        return super.getItemViewType(newPosition)
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    class ItemAutoHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}