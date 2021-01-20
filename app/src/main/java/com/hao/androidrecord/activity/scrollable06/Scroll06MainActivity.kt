package com.hao.androidrecord.activity.scrollable06

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.scrollable06.adapter.MultiTypeAdapter
import com.hao.androidrecord.activity.scrollable06.bean.CategoryBean
import kotlinx.android.synthetic.main.activity_main_scroll06.*
import java.util.*


/**
 * https://github.com/JasonGaoH/NestedRecyclerView
 */
class Scroll06MainActivity : BaseMenuActivity() {

    private val mDataList = ArrayList<Any>()

    private val strArray = arrayOf("推荐", "视频", "直播", "图片", "精华", "热门")

    var lastBackPressedTime = 0L

    private val multiTypeAdapter =
        MultiTypeAdapter(mDataList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_scroll06)

        kttRecyclerView.initLayoutManager()
        kttRecyclerView.adapter = multiTypeAdapter

        refresh()

        swipeRefreshLayout.setColorSchemeColors(Color.RED)
        swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }

    }

    private fun refresh() {
        mDataList.clear()
        for (i in 0..8) {
            mDataList.add("parent item text $i")
        }
        val categoryBean = CategoryBean()
        categoryBean.tabTitleList.clear()
        categoryBean.tabTitleList.addAll(strArray.asList())
        mDataList.add(categoryBean)
        multiTypeAdapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastBackPressedTime < 2000) {
            super.onBackPressed()
        } else {
            kttRecyclerView.scrollToPosition(0)
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show()
            lastBackPressedTime = System.currentTimeMillis()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        multiTypeAdapter.destroy()
    }
}
