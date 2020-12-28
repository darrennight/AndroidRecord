package com.hao.androidrecord.activity.nestedscrolling.fragment;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hao.androidrecord.activity.nestedscrolling.adapter.RecyclerNestAdapter;

public class DataBean implements MultiItemEntity {

    public  String text;
    public   String url;

    public DataBean(String text, String url) {
        this.text = text;
        this.url = url;
    }

    @Override
    public int getItemType() {
        return RecyclerNestAdapter.NORMAL_TYPE;
    }
}
