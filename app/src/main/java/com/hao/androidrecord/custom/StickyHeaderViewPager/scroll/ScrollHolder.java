package com.hao.androidrecord.custom.StickyHeaderViewPager.scroll;

import android.widget.AbsListView;
import android.widget.ScrollView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sj on 15/11/20.
 */
public interface ScrollHolder {

    void adjustScroll(int scrollHeight, int headerHeight);

    void onListViewScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition);

    void onScrollViewScroll(ScrollView view, int x, int y, int oldX, int oldY, int pagePosition);

    void onRecyclerViewScroll(RecyclerView view, int scrollY, int pagePosition);
}
