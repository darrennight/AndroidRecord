package com.hao.androidrecord.activity.scrollable03.holder;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.hao.androidrecord.R;

/**
 * Created by Administrator on 2018/9/5.
 * Description : ProductTabHolder
 */

public class ProductTabHolder extends RecyclerView.ViewHolder {
    public TabLayout tabLayout;

    public ProductTabHolder(View view) {
        super(view);
        tabLayout = view.findViewById(R.id.tablayout);
    }
}
