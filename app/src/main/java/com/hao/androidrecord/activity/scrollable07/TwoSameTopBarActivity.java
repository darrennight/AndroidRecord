package com.hao.androidrecord.activity.scrollable07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.scrollable07.adapter.NormalAdapter;
import com.hao.androidrecord.activity.scrollable07.util.ViewUtils;
import com.hao.androidrecord.activity.scrollable07.widget.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lihongxin on 2019/1/18
 */
public class TwoSameTopBarActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.scrollView)
    MyScrollView scrollView;
    @BindView(R.id.ll_outside_fixed)
    LinearLayout llOutsideFixed;
    @BindView(R.id.inside_fixed_bar)
    LinearLayout insideFixedBar;


    private List<String> list;
    private NormalAdapter normalAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int topHeight;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, TwoSameTopBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_bar);
        ButterKnife.bind(this);
        initList();
        initView();
    }

    private void initView() {
        topHeight = ViewUtils.dip2px(this, 200);

        linearLayoutManager = new LinearLayoutManager(this);
        normalAdapter = new NormalAdapter(list);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(normalAdapter);

        scrollView.setScrollChangeListener(new MyScrollView.ScrollChangedListener() {
            @Override
            public void onScrollChangedListener(int x, int y, int oldX, int oldY) {
                Log.d("test", "当前x：" + x + "当前y:" + y);
                if (y >= topHeight) {
                    //重点 通过距离变化隐藏内外固定栏实现
                    llOutsideFixed.setVisibility(View.VISIBLE);
                    insideFixedBar.setVisibility(View.GONE);
                    recyclerView.setNestedScrollingEnabled(true);
                } else {
                    llOutsideFixed.setVisibility(View.GONE);
                    insideFixedBar.setVisibility(View.VISIBLE);
                    recyclerView.setNestedScrollingEnabled(false);
                }

            }
        });
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i + "");
        }
    }
}

