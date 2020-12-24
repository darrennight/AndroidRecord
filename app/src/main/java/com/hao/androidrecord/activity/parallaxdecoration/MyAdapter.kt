package com.hao.androidrecord.activity.parallaxdecoration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.item_parallax.view.*

/**
 *
 * Author: Seagazer
 * Date: 2020/12/6
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: String) {
            itemView.apply {
                item_text.text = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_parallax, parent, false)
        )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind("Item-$position")
    }

    override fun getItemCount(): Int = 100
}