package com.hao.androidrecord.activity.scrollable06.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R

class SimpleTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mTv: TextView = itemView.findViewById(R.id.textView) as TextView
}
