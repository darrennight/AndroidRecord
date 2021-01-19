package com.hao.androidrecord.custom.StickyHeaderViewPager.scroll;

import android.widget.AbsListView;
import android.widget.ScrollView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sj on 15/11/20.
 */
public class ScrollHolderFragment extends Fragment implements ScrollHolder {

    protected ScrollHolder mScrollTabHolder;

    public void bindScrollTabHolder(ScrollHolder mScrollTabHolder){
        this.mScrollTabHolder = mScrollTabHolder;
    }

    @Override
    public void onDetach() {
        mScrollTabHolder = null;
        super.onDetach();
    }

    @Override
    public void adjustScroll(int scrollHeight, int headerHeight) {}

    @Override
    public void onListViewScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {}

    @Override
    public void onScrollViewScroll(ScrollView view, int x, int y, int oldX, int oldY, int pagePosition) {}

    @Override
    public void onRecyclerViewScroll(RecyclerView view, int scrollY, int pagePosition) { }
}