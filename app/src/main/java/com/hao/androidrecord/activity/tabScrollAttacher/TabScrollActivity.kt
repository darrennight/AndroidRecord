package com.hao.androidrecord.activity.tabScrollAttacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.tabScrollAttacher.data.Category
import com.hao.androidrecord.activity.tabScrollAttacher.data.DataFetcher
import com.hao.androidrecord.activity.tabScrollAttacher.data.Item
import kotlinx.android.synthetic.main.activity_tab_scroll_attache.*

//https://github.com/iammert/TabScrollAttacher
class TabScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_scroll_attache)

        val categories = DataFetcher.fetchData(applicationContext)

        /**
         * load recyclerview adapter
         */
        val adapter = ItemListAdapter()
        adapter.setItems(getAllItems(categories))
        recyclerView.adapter = adapter

        /**
         * Load tabs
         */
        TabLoader.loadTabs(tabLayout, categories)

        /**
         * SETUP ATTACHER
         */
        val indexOffsets = getCategoryIndexOffsets(categories)
        val attacher = TabScrollAttacher(tabLayout, recyclerView, indexOffsets) {
            scrollSmoothly()
        }

        attacher.attach()

        //attacher.detach()

    }

    /**
     * Calculate your index offset list.
     * Attacher will talk to recyclerview and tablayout
     * with offsets and indexes.
     */
    private fun getCategoryIndexOffsets(categories: List<Category>): List<Int> {
        val indexOffsetList = arrayListOf<Int>()
        categories.forEach { categoryItem ->
            if (indexOffsetList.isEmpty()) {
                indexOffsetList.add(0)
            } else {
                indexOffsetList.add(indexOffsetList.last() + categoryItem.itemList.size)
            }
        }
        return indexOffsetList
    }

    private fun getAllItems(categories: List<Category>): List<Item> {
        val items = arrayListOf<Item>()
        categories.forEach { items.addAll(it.itemList) }
        return items
    }
}
