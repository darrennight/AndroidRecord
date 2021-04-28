package com.hao.androidrecord.activity.cusTab.customview01;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.cusTab.ColorItem;
import com.hao.androidrecord.activity.cusTab.Demo;
import com.hao.androidrecord.activity.cusTab.DemoColorPagerAdapter;
import com.hao.androidrecord.activity.cusTab.utils.DemoData;
import com.hao.androidrecord.custom.tab.RecyclerTabLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DemoCustomView01Activity extends AppCompatActivity {

    private static final String KEY_DEMO = "demo";

    public static void startActivity(Context context, Demo demo) {
        Intent intent = new Intent(context, DemoCustomView01Activity.class);
        intent.putExtra(KEY_DEMO, demo.name());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_custom_view01);

        Demo demo = Demo.valueOf(getIntent().getStringExtra(KEY_DEMO));

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(demo.titleResId);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<ColorItem> items = DemoData.loadDemoColorItems(this);
        Collections.sort(items, new Comparator<ColorItem>() {
            @Override
            public int compare(ColorItem lhs, ColorItem rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });

        DemoColorPagerAdapter adapter = new DemoColorPagerAdapter();
        adapter.addAll(items);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        RecyclerTabLayout recyclerTabLayout = findViewById(R.id.recycler_tab_layout);
        recyclerTabLayout.setUpWithAdapter(new DemoCustomView01Adapter(viewPager));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}