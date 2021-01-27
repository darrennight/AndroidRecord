package com.hao.androidrecord.activity.NotchTools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;

/**
 * https://github.com/zhangzhun132/NotchTools
 */
public class NotchToolsMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notch_tool);
        findViewById(R.id.fullscreen_usenotch).setOnClickListener(this);
        findViewById(R.id.fullscreen_nonotch).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fullscreen_usenotch:
                intent.setClass(NotchToolsMainActivity.this, FullScreenUseNotchActivity.class);
                break;
            case R.id.fullscreen_nonotch:
                intent.setClass(NotchToolsMainActivity.this, FullScreenNoUseNotchActivity.class);
                break;
            default:
                break;
        }
        try {
            startActivity(intent);
        } catch (Exception e) {

        }
    }
}
