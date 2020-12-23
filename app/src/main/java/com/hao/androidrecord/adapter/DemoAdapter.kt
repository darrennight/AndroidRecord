package com.hao.androidrecord.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.item_demo.view.*

class DemoAdapter(var context:Context,var list: MutableList<String>):RecyclerView.Adapter<DemoAdapter.DemoHolder>() {

    var clickListener:DemoItemClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoHolder {
        return DemoHolder(LayoutInflater.from(context).inflate(R.layout.item_demo,parent,false))
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: DemoHolder, position: Int) {

        holder.itemView.apply {
            tv_demo_name.text = list[position]
            setOnClickListener {
                clickListener?.let {
                    it.onDemoItemClick(position)
                }
            }
        }
    }

    class DemoHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    interface DemoItemClickListener{
        fun onDemoItemClick(position: Int)
    }
}