package com.hao.androidrecord.activity.expandable.expand01.markets

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.iterator
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.activity.expandable.expand01.dpToPx
import pokercc.android.expandablerecyclerview.ExpandableAdapter
import pokercc.android.expandablerecyclerview.ExpandableRecyclerView

class MarketsItemDecoration : RecyclerView.ItemDecoration() {
    private val linePaint = Paint().apply {
        color = 0xfff6f6f8.toInt()
        strokeWidth = 1.dpToPx()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        parent as ExpandableRecyclerView
        val adapter = parent.requireAdapter()
        val layoutManager = parent.layoutManager!!

        // Draw divide line between children item.
        for (view in parent) {
            val viewHolder = parent.getChildViewHolder(view)
            val params = viewHolder.itemView.layoutParams as RecyclerView.LayoutParams
            viewHolder as ExpandableAdapter.ViewHolder
            val (groupPosition, childPosition) = adapter.getItemLayoutPosition(viewHolder)
            val childCount = adapter.getChildCount(groupPosition)
            if (!adapter.isGroup(viewHolder.itemViewType) && childPosition != childCount - 1) {
                val y = layoutManager.getDecoratedBottom(view) + view.translationY
                parent.clipAndDrawChild(c, view) {
                    it.drawLine(
                        parent.paddingStart + 10.dpToPx() + params.marginStart, y,
                        parent.width - parent.paddingEnd.toFloat() - params.marginEnd, y,
                        linePaint
                    )
                }

            }
        }

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        parent as ExpandableRecyclerView
        val adapter = parent.requireAdapter()
        val viewHolder = parent.getChildViewHolder(view)

        //Give bottom margin when it is group type or it is the last one of group.
        val isGroup = adapter.isGroup(viewHolder.itemViewType)
        val firstChild = {
            adapter.getItemLayoutPosition(viewHolder as ExpandableAdapter.ViewHolder).childPosition == 0
        }
        if (isGroup || firstChild()) {
            outRect.top = 12.dpToPx().toInt()
        }

    }
}
