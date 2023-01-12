package com.hao.androidrecord.activity.easyScollImage;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.easyScollImage.adapter.SplashAdapter;
import com.hao.androidrecord.activity.easyScollImage.view.ScollLinearLayoutManager;


//https://github.com/Vincent7Wong/EasyScollImage
public class SplashScrollImageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_scroll);

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setAdapter(new SplashAdapter(SplashScrollImageActivity.this));
        mRecyclerView.setLayoutManager(new ScollLinearLayoutManager(SplashScrollImageActivity.this));

        //smoothScrollToPosition滚动到某个位置（有滚动效果）
        mRecyclerView.smoothScrollToPosition(Integer.MAX_VALUE / 2);
    }


}
