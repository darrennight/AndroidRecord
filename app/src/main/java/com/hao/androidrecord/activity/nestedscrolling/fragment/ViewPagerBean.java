package com.hao.androidrecord.activity.nestedscrolling.fragment;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hao.androidrecord.activity.nestedscrolling.adapter.RecyclerNestAdapter;

public class ViewPagerBean implements MultiItemEntity {
    @Override
    public int getItemType() {
        return RecyclerNestAdapter.VIEW_PAGE_TYPE;
    }
}
