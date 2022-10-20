package com.hao.androidrecord.activity.consecutivescroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hao.androidrecord.R;

public class MainScrollerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrollor);

        findViewById(R.id.btn_sample).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this,SampleActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_sticky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this,StickyActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_sink_sticky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this,SinkStickyActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_consecutive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this,ConsecutiveActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_scroll_child).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this,ScrollChildActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_viewpager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this,ViewPagerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_viewpager2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this,ViewPager2Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_sticky_permanent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this, PermanentStickyActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScrollerActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }

}
