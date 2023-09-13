package com.hao.androidrecord.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
import com.hao.androidrecord.adapter.RecyclePositionAdapter
import kotlinx.android.synthetic.main.actiivty_recycle_position.*

class RecyclePositonActivity:AppCompatActivity() {

    private val list:ArrayList<TypeData> = ArrayList<TypeData>()
    val manager = GridLayoutManager(this,2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actiivty_recycle_position)
        val manager = GridLayoutManager(this,2)
        manager.orientation = GridLayoutManager.VERTICAL
        manager.spanSizeLookup = object :SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                if (list[position].isTitle){
                    return 2
                }else{
                    return 1
                }
            }
        }

        rv_list.layoutManager = manager

        list.add(TypeData(true,"热门分类"))
        for (i in 0..10){
            list.add(TypeData(false,"热门$i"))
        }

        list.add(TypeData(true,"题材分类"))
        for (i in 0..10){
            list.add(TypeData(false,"题材$i"))
        }

        list.add(TypeData(true,"情节分类"))
        for (i in 0..10){
            list.add(TypeData(false,"情节$i"))
        }

        rv_list.adapter = RecyclePositionAdapter(this,list)

        rv_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val positon =  manager.findFirstVisibleItemPosition()
                Log.e("=====po","$positon")

                val content = list[positon].content
                if (content.contains("热门")){
                    tv_hot.setTextColor(Color.RED)
                    tv_tc.setTextColor(Color.BLACK)
                    tv_qingjie.setTextColor(Color.BLACK)
                }else if (content.contains("题材")){
                    tv_hot.setTextColor(Color.BLACK)
                    tv_tc.setTextColor(Color.RED)
                    tv_qingjie.setTextColor(Color.BLACK)
                }else if (content.contains("情节")){
                    tv_hot.setTextColor(Color.BLACK)
                    tv_tc.setTextColor(Color.BLACK)
                    tv_qingjie.setTextColor(Color.RED)
                }

            }
        })
    }


    data class TypeData(val isTitle:Boolean,val content:String)
}