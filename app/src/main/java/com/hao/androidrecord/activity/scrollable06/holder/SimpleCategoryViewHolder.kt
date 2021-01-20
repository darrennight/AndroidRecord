package com.hao.androidrecord.activity.scrollable06.holder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.scrollable06.CategoryView
import com.hao.androidrecord.activity.scrollable06.ChildRecyclerView
import com.hao.androidrecord.activity.scrollable06.adapter.CategoryPagerAdapter
import com.hao.androidrecord.activity.scrollable06.bean.CategoryBean
import com.hao.androidrecord.activity.scrollable06.tab.DynamicTabLayout

class SimpleCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTabLayout: DynamicTabLayout = itemView.findViewById(R.id.newTabLayout) as DynamicTabLayout
    private val mViewPager: ViewPager = itemView.findViewById(R.id.viewPager) as ViewPager

    val viewList = ArrayList<CategoryView>()

    var cacheVies = HashMap<String, CategoryView>()

    private var mCurrentRecyclerView : ChildRecyclerView? = null

    private var isTabExpanded = true

    init {
        mTabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT)
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                if(viewList.isEmpty().not()) {
                    mCurrentRecyclerView = viewList[position]
                    mCurrentRecyclerView?.apply {
                        addOnScrollListener(object :RecyclerView.OnScrollListener(){
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                if(dy != 0) {
                                    dealWithChildScrollEvents(this@apply.isScrollTop())
                                }
                            }
                        })
                    }
                }
            }
            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun dealWithChildScrollEvents(scrollTop: Boolean) {
        if(isTabExpanded.not() && scrollTop) {
            mTabLayout.changeDescHeightWithAnimation(false)
            mTabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT)
            isTabExpanded = true
        } else if(isTabExpanded && scrollTop.not()) {
            mTabLayout.changeDescHeightWithAnimation(true)
            mTabLayout.setSelectedTabIndicatorColor(Color.RED)
            isTabExpanded = false
        }

    }

    fun bindData(categoryBean: CategoryBean) {
        categoryBean.apply {
            viewList.clear()
            if(cacheVies.size > tabTitleList.size) {
                cacheVies.clear()
            }
            tabTitleList.forEach{
                var categoryView = cacheVies[it]
                if(categoryView == null || categoryView.parent != mViewPager) {
                    categoryView =
                        CategoryView(itemView.context)
                    cacheVies[it] = categoryView
                }
                viewList.add(categoryView)
            }
            mCurrentRecyclerView = viewList[mViewPager.currentItem]
            val lastItem = mViewPager.currentItem

            mViewPager.adapter = CategoryPagerAdapter(
                viewList,
                tabTitleList
            )
            mTabLayout.setupWithViewPager(mViewPager)
            mViewPager.currentItem = lastItem
            //默认bind第一个子RecyclerView的滑动，不然第一个tab不会执行动画
            bindDefaultChildRecyclerViewScrolling(viewList[0])
        }
    }

    private fun bindDefaultChildRecyclerViewScrolling(categoryView: CategoryView) {
        categoryView.apply {
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if(dy != 0) {
                        dealWithChildScrollEvents(this@apply.isScrollTop())
                    }
                }
            })
        }
    }

    fun getCurrentChildRecyclerView(): ChildRecyclerView? {
        return mCurrentRecyclerView
    }

    fun destroy() {
        cacheVies.clear()
    }
}
