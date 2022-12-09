package com.hao.androidrecord.table

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_luna_table.*
import kotlinx.android.synthetic.main.item_luna_table.view.*

class LunaActivity:AppCompatActivity() {

    var strTitle = arrayOf(
        "摇一摇",
        "积分商城",
        "VIP",
        "V在线客服",
        "sss",
        "更改",
        "ssd",
        "多岁的",
        "的地方",
        "换行",
        "摇一摇",
        "积分商城",
        "VIP",
        "V在线客服",
        "sss",
        "更改",
        "ssd",
        "多岁的",
        "的地方",
        "的地方",
        "的地方",
        "的地方",
        "的地方",
        "的地方",
        "换行"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luna_table)
        rv_table_view.layoutManager = TableLayoutManager()
//        rv_table_view.addItemDecoration(TableDividerItemDecoration(2, Color.BLUE,this))
        rv_table_view.adapter = TableAdapter(this,strTitle)
    }



    class TableAdapter(val context: Context,val list:Array<String>):Adapter<TableAdapter.TableHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
            return TableHolder(LayoutInflater.from(context).inflate(R.layout.item_luna_table,parent,false))
        }

        override fun onBindViewHolder(holder: TableHolder, position: Int) {

            holder.itemView.tv_table_name.text = "${list[position]}$position"
        }

        override fun getItemCount(): Int {
            return list.size
        }

        class TableHolder(view: View):ViewHolder(view)
    }
}