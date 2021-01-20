package com.hao.androidrecord.activity.scrollable06.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.scrollable06.ChildRecyclerView
import com.hao.androidrecord.activity.scrollable06.bean.CategoryBean
import com.hao.androidrecord.activity.scrollable06.holder.SimpleCategoryViewHolder
import com.hao.androidrecord.activity.scrollable06.holder.SimpleTextViewHolder

class MultiTypeAdapter(private val dataSet:ArrayList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val  TYPE_TEXT = 0
        private const val TYPE_CATEGORY = 1
    }

    private var mCategoryViewHolder: SimpleCategoryViewHolder? = null

    override fun getItemViewType(position: Int): Int {
        return if(dataSet[position] is String) {
            TYPE_TEXT
        } else {
            TYPE_CATEGORY
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == TYPE_TEXT) {
            SimpleTextViewHolder(
                LayoutInflater.from(
                    viewGroup.context
                ).inflate(R.layout.layout_item_text, viewGroup, false)
            )
        } else {
            val categoryViewHolder =
                SimpleCategoryViewHolder(
                    LayoutInflater.from(viewGroup.context).inflate(
                        R.layout.layout_item_category,
                        viewGroup,
                        false
                    )
                )
            mCategoryViewHolder =  categoryViewHolder
            return categoryViewHolder
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        if(holder is SimpleTextViewHolder) {
            holder.mTv.text = dataSet[pos] as String
        } else if(holder is SimpleCategoryViewHolder){
            holder.bindData(dataSet[pos] as CategoryBean)
        }
    }

    fun getCurrentChildRecyclerView(): ChildRecyclerView? {
        mCategoryViewHolder?.apply {
           return this.getCurrentChildRecyclerView()
        }
        return null
    }

    fun destroy() {
        mCategoryViewHolder?.destroy()

    }


}
