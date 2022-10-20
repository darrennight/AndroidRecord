package com.hao.androidrecord.activity.pinned.adapter

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import com.hao.androidrecord.R2.layout.item
import com.hao.androidrecord.activity.pinned.StockActivity
import com.hao.androidrecord.activity.pinned.entity.StockEntity
import com.hao.androidrecord.custom.pinned.util.FullSpanUtil
import kotlinx.android.synthetic.main.item_stock_data.view.*
import kotlinx.android.synthetic.main.item_stock_header.view.*


class StockAdapter(val context: Context,val list:List<StockEntity.StockInfo>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == StockEntity.StockInfo.TYPE_HEADER){
            StockHeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stock_header,parent,false))
        }else{
            StockViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stock_data,parent,false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = list[position]
        if(holder is StockHeaderViewHolder){
            holder.itemView.apply {
                tv_stock_name.text = data.pinnedHeaderName
            }
        }else if (holder is StockViewHolder){

            val stockNameAndCode: String = data.stock_name.toString() + "\n" + data.stock_code
//            val ssb = SpannableStringBuilder(stockNameAndCode)
//            ssb.setSpan(
//                ForegroundColorSpan(Color.parseColor("#a4a4a7")),
//                data.stock_name.length,
//                stockNameAndCode.length,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//            )
//            ssb.setSpan(
//                AbsoluteSizeSpan(StockActivity.dip2px(holder.itemView.context, 13f)),
//                data.stock_name.length,
//                stockNameAndCode.length,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//            )
            holder.itemView.apply {
                tv_stock_name_code.text = stockNameAndCode
                tv_current_price.text = data.current_price
                tv_rate.text = (if (data.rate < 0) java.lang.String.format(
                    "%.2f",
                    data.rate
                ) else "+" + java.lang.String.format("%.2f", data.rate)) + "%"
            }


        }

    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, StockEntity.StockInfo.TYPE_HEADER);
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        FullSpanUtil.onViewAttachedToWindow(holder, this, StockEntity.StockInfo.TYPE_HEADER);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position].itemType == StockEntity.StockInfo.TYPE_HEADER){
            StockEntity.StockInfo.TYPE_HEADER
        }else{
            StockEntity.StockInfo.TYPE_DATA
        }
    }

    class StockViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    class StockHeaderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}