package com.hao.androidrecord.activity.slidingTab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hao.androidrecord.R;

public class MainSlidingActivity extends AppCompatActivity {
    SlidingTab slidingTab;
    ViewPager viewPager;
    Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sliding);

        viewPager = findViewById(R.id.viewpager);
        slidingTab = findViewById(R.id.sliding_tab);

        mAdapter = new Adapter(this);
        viewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        slidingTab.setTitles("课程", "文档");
        slidingTab.bindViewPager(viewPager);

        slidingTab.setOnTabClickListener(new SlidingTab.OnTabClickListener() {
            @Override
            public void onTabClick(int position) {
                viewPager.setCurrentItem(position);
            }
        });
    }


    class Adapter extends PagerAdapter {
        Context mContext;

        public Adapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return 2;
        }

        int colors[]=new int[]{0xfff3aa0b,0xff1F8F70};
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_item, null);
            view.setBackgroundColor(colors[position%colors.length]);
            //不添加不显示
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }
}
