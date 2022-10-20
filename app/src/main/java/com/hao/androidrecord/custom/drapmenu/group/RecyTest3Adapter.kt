package com.hao.androidrecord.custom.drapmenu.group

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.drapmenu.DropMenuActivity
import kotlinx.android.synthetic.main.item_demo_tes3.view.*
import kotlinx.android.synthetic.main.item_demo_tes3_title.view.*

class RecyTest3Adapter(var context:Context, var list: ArrayList<DropMenuActivity.TestData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var clickListener:DemoItemClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0){
            return RecyTestTitleHolder(LayoutInflater.from(context).inflate(R.layout.item_demo_tes3_title,parent,false))
        }else{
            return RecyTestHolder(LayoutInflater.from(context).inflate(R.layout.item_demo_tes3,parent,false))
        }

    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = list[position]
        if(holder is RecyTestTitleHolder){
            holder.itemView.apply {
                tv_demo_name_title.text = data.des
                setOnClickListener {
                    clickListener?.let {
                        it.onDemoItemClick(position)
                    }
                }
            }
        }else if(holder is RecyTestHolder){
            holder.itemView.apply {
                tv_demo_name.text = data.des
                setOnClickListener {
                    clickListener?.let {
                        it.onDemoItemClick(position)
                    }
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position].title){
            0
        }else{
            1
        }
    }


    class RecyTestHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    class RecyTestTitleHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    interface DemoItemClickListener{
        fun onDemoItemClick(position: Int)
    }
}