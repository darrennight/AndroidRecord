package com.hao.androidrecord.activity.scrollable01.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.scrollable01.PagerSlidingTabStrip;
import com.hao.androidrecord.activity.scrollable01.ScrollableLayout;


public class ParallaxImageHeaderFragment extends BasePagerFragment {

    private ScrollableLayout mScrollLayout;
    private ImageView imageHeader;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_imageheader, container, false);
        ViewPager viewPager = (ViewPager) layout.findViewById(R.id.viewpager);
        imageHeader = (ImageView) layout.findViewById(R.id.imageHeader);
        // ScrollableLayout
        mScrollLayout = (ScrollableLayout) layout.findViewById(R.id.scrollableLayout);
        mScrollLayout.setOnScrollListener(new ScrollableLayout.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
//                ViewHelper.setTranslationY(imageHeader, (float) (currentY * 0.5));
                
            }
        });
        // 扩展点击头部滑动范围
//        int headHeight = getResources().getDimensionPixelSize(R.dimen.head_height);
//        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
//        mScrollLayout.setClickHeadExpand(headHeight + tabHeight);
        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) layout.findViewById(R.id.pagerStrip);
        initFragmentPager(viewPager, pagerSlidingTabStrip, mScrollLayout);
        return layout;
    }

}