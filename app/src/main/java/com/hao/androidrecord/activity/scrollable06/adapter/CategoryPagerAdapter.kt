package com.hao.androidrecord.activity.scrollable06.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.hao.androidrecord.activity.scrollable06.CategoryView
import com.hao.androidrecord.activity.scrollable06.tab.DynamicTabBean
import com.hao.androidrecord.activity.scrollable06.tab.DynamicTabLayout

class CategoryPagerAdapter(
    private val viewList: ArrayList<CategoryView>,
    private val tabTitleList: ArrayList<String>
) : PagerAdapter(), DynamicTabLayout.DynamicTabProvider {

    private var mCurrentPrimaryItem: CategoryView? = null

    override fun getCount(): Int {
        return viewList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = viewList[position]
        if (container == view.parent) {
            container.removeView(view)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //container.removeView((View) object);
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, obj: Any) {
        val item = obj as CategoryView
        if(item != mCurrentPrimaryItem) {
            mCurrentPrimaryItem?.onUserVisibleChange(false)
        }
        item.onUserVisibleChange(true)
        mCurrentPrimaryItem = item
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitleList[position]
    }

    override fun getPageTitleItem(position: Int): DynamicTabBean? {
        return DynamicTabBean("推荐", "精品推荐")
    }
}
