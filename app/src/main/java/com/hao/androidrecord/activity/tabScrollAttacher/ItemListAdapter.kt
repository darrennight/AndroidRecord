package com.hao.androidrecord.activity.tabScrollAttacher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.tabScrollAttacher.data.Item

class ItemListAdapter : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private val itemList = arrayListOf<Item>()

    fun setItems(itemList: List<Item>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {}

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {

            fun create(parent: ViewGroup): ItemViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_attache, parent, false)
                return ItemViewHolder(view)
            }
        }
    }
}
