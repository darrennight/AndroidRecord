package com.hao.androidrecord.activity.recycleState

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hao.androidrecord.R
//https://github.com/rubensousa/RecyclerViewNestedExample
class RecycleStateActivity:AppCompatActivity() {
    private lateinit var adapter: ParentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var scrollStateHolder: ScrollStateHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_state)
        recyclerView = findViewById(R.id.recyclerView)
        scrollStateHolder = ScrollStateHolder(savedInstanceState)
        adapter = ParentAdapter(scrollStateHolder)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        loadItems()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        scrollStateHolder.onSaveInstanceState(outState)
    }

    private fun loadItems() {
        val lists = arrayListOf<TitledList>()
        repeat(20) { listIndex ->
            val items = arrayListOf<String>()
            repeat(30) { itemIndex -> items.add(itemIndex.toString()) }
            lists.add(TitledList("List number $listIndex", items))
        }
        adapter.setItems(lists)
    }

}