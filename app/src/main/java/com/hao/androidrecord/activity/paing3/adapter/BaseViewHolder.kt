package com.hao.androidrecord.activity.paing3.adapter

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) :
    RecyclerView.ViewHolder(view),
    ViewHolderVisibleCallback {

    open fun bind(data: T,position:Int) {
    }

    override fun onVisibleChange(visible: Boolean) {
    }

    protected fun <V : View> findView(@IdRes id: Int) = itemView.findViewById(id) as V?
        ?: throw IllegalStateException("View ID $id not found.")
}