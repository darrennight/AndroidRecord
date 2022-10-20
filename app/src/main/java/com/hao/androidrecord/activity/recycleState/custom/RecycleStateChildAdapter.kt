package com.hao.androidrecord.activity.recycleState.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.item_state_child.view.*

class RecycleStateChildAdapter(val context:Context,val list: ArrayList<String> = ArrayList()):RecyclerView.Adapter<RecycleStateChildAdapter.RecycleStateChildHolder>() {

    fun addItemData(data: List<String>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleStateChildHolder {
        return RecycleStateChildHolder(LayoutInflater.from(context).inflate(R.layout.item_state_child,parent,false))
    }

    override fun onBindViewHolder(holder: RecycleStateChildHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    class RecycleStateChildHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(position: Int){
            itemView.tv_position_text.text = "$position"
        }
    }
}