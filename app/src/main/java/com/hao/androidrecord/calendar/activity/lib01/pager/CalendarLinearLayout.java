package com.hao.androidrecord.calendar.activity.lib01.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.hao.androidrecord.calendar.activity.lib01.base.fragment.FragmentAdapter;
import com.hao.androidrecord.calendar.lib01.CalendarLayout;


/**
 * 如果嵌套各种View出现事件冲突，可以实现这个方法即可
 */
public class CalendarLinearLayout extends LinearLayout implements CalendarLayout.CalendarScrollView {

    private FragmentAdapter mAdapter;

    public CalendarLinearLayout(Context context) {
        super(context);
    }

    public CalendarLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 如果你想让下拉无效，return false
     *
     * @return isScrollToTop
     */
    @Override
    public boolean isScrollToTop() {
        if(mAdapter == null){
            if (getChildCount() > 1 && getChildAt(1) instanceof ViewPager) {
                ViewPager viewPager = (ViewPager) getChildAt(1);
                mAdapter = (FragmentAdapter) viewPager.getAdapter();
            }
        }
        return mAdapter != null && ((PagerFragment) mAdapter.getCurFragment()).isScrollTop();
    }

}
