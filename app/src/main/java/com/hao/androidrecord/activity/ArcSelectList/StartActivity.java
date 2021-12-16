package com.hao.androidrecord.activity.ArcSelectList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.ArcSelectList.ui.ArcActivity;
import com.hao.androidrecord.activity.ArcSelectList.ui.ArcSelectActivity;
import com.hao.androidrecord.activity.ArcSelectList.ui.ArcSelectRotateActivity;
import com.hao.androidrecord.activity.ArcSelectList.ui.AutoSelectActivity;
import com.hao.androidrecord.activity.ArcSelectList.ui.BottomArcActivity;
import com.hao.androidrecord.activity.ArcSelectList.ui.CircleArcActivity;
import com.hao.androidrecord.activity.ArcSelectList.ui.HorizontalSelectActivity;
import com.hao.androidrecord.activity.ArcSelectList.ui.PadingArcActivity;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.bt_arc_select_r).setOnClickListener(this);
        findViewById(R.id.bt_arc).setOnClickListener(this);
        findViewById(R.id.bt_arc2).setOnClickListener(this);
        findViewById(R.id.bt_auto_select).setOnClickListener(this);
        findViewById(R.id.bt_carc).setOnClickListener(this);
        findViewById(R.id.bt_arc_select_).setOnClickListener(this);
        findViewById(R.id.bt_auto_select_h).setOnClickListener(this);
        findViewById(R.id.bt_auto_select_h2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_arc_select_r:
                start(ArcSelectRotateActivity.class);
                break;
            case R.id.bt_arc:
                start(ArcActivity.class);
                break;
            case R.id.bt_arc2:
                start(PadingArcActivity.class);
                break;
            case R.id.bt_auto_select:
                start(AutoSelectActivity.class);
                break;
            case R.id.bt_carc:
                start(CircleArcActivity.class);
                break;
            case R.id.bt_arc_select_:
                start(ArcSelectActivity.class);
                break;
            case R.id.bt_auto_select_h:
                start(HorizontalSelectActivity.class);
                break;
                case R.id.bt_auto_select_h2:
                start(BottomArcActivity.class);
                break;
        }
    }

    private void start(Class clazz) {
        startActivity(new Intent(this, clazz));
    }
}
