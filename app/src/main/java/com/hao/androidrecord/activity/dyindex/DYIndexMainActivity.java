package com.hao.androidrecord.activity.dyindex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hao.androidrecord.R;

//https://github.com/lmxjw3/videoplay
public class DYIndexMainActivity extends Activity {
    private VideoPlayRecyclerView mRvVideo;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dy_index);
        initView();
    }

    private void initView() {
        findViewById(R.id.ibBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRvVideo = findViewById(R.id.rvVideo);
        adapter = new MainAdapter(this);
        mRvVideo.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.release();
    }
}
