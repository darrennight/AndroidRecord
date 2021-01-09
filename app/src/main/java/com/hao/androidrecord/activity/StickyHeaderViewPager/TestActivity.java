package com.hao.androidrecord.activity.StickyHeaderViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;
import com.hao.androidrecord.custom.StickyHeaderViewPager.StickHeaderViewPager;
import com.hao.androidrecord.custom.StickyHeaderViewPager.tab.SlidingTabLayout;

//https://github.com/w446108264/StickyHeaderViewPager
//https://github.com/w446108264/StickHeaderLayout
//https://github.com/wenlai521/viewpagerDemo
public class TestActivity extends AppCompatActivity {

    StickHeaderViewPager shvp_content;
    SlidingTabLayout stl_stick;
    TextView tv_github;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test01);


        shvp_content = (StickHeaderViewPager) findViewById(R.id.shvp_content);
        stl_stick = (SlidingTabLayout) findViewById(R.id.stl_stick);
        tv_github = (TextView) findViewById(R.id.tv_github);

        tv_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(tv_github.getText().toString());
                Intent intent = new  Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        setData();
    }

    private void setData() {
        shvp_content.getViewPager().setOffscreenPageLimit(5);

        StickHeaderViewPager.StickHeaderViewPagerBuilder.stickTo(shvp_content)
                .setFragmentManager(getSupportFragmentManager())
                .addScrollFragments(
                        //recyclerView列表有问题
//                        RecyclerViewSimpleFragment.newInstance("RecyclerView"),
//                        RecyclerViewGridSimpleFragment.newInstance("GridView"),
                        ScrollViewSimpleFragment.newInstance("Scroll"),
                        WebViewSimpleFragment.newInstance("WebView"),
                        ListViewSimpleFragment.newInstance("ListView"))
                .notifyData();

        stl_stick.setViewPager(shvp_content.getViewPager());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, 0, 0, "Simple Activity").setIcon(R.drawable.ic_launcher_background);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 0:
                SimpleActivity.openActivity(TestActivity.this);
                break;
        }
        return true;
    }
}