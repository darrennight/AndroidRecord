package com.hao.androidrecord.custom.drapmenu.group

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.item_demo_test.view.*

class RecyTestAdapter(var context:Context,var list: ArrayList<String>):
    RecyclerView.Adapter<RecyTestAdapter.RecyTestHolder>() {

    var clickListener:DemoItemClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyTestAdapter.RecyTestHolder {
        return RecyTestHolder(LayoutInflater.from(context).inflate(R.layout.item_demo_test,parent,false))
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: RecyTestAdapter.RecyTestHolder, position: Int) {

        holder.itemView.apply {
            tv_demo_name.text = list[position]
            setOnClickListener {
                clickListener?.let {
                    it.onDemoItemClick(position)
                }
            }
        }
    }

    class RecyTestHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    interface DemoItemClickListener{
        fun onDemoItemClick(position: Int)
    }
}