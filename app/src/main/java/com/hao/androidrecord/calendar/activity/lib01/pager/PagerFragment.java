package com.hao.androidrecord.calendar.activity.lib01.pager;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.hao.androidrecord.R;
import com.hao.androidrecord.calendar.activity.lib01.Article;
import com.hao.androidrecord.calendar.activity.lib01.ArticleAdapter;
import com.hao.androidrecord.calendar.activity.lib01.base.fragment.BaseFragment;
import com.hao.androidrecord.calendar.activity.lib01.group.GroupItemDecoration;
import com.hao.androidrecord.calendar.activity.lib01.group.GroupRecyclerView;

public class PagerFragment extends BaseFragment {

    private GroupRecyclerView mRecyclerView;


    public static PagerFragment newInstance() {
        return new PagerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lib01_pager;
    }

    @Override
    protected void initView() {
        mRecyclerView = (GroupRecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(mContext));
        mRecyclerView.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

    public boolean isScrollTop() {
        return mRecyclerView != null && mRecyclerView.computeVerticalScrollOffset() == 0;
    }
}
