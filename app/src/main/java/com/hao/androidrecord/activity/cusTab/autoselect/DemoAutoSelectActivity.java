package com.hao.androidrecord.activity.cusTab.autoselect;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hao.androidrecord.activity.cusTab.Demo;
import com.hao.androidrecord.activity.cusTab.basic.DemoBasicActivity;

public class DemoAutoSelectActivity extends DemoBasicActivity {

    public static void startActivity(Context context, Demo demo) {
        Intent intent = new Intent(context, DemoAutoSelectActivity.class);
        intent.putExtra(KEY_DEMO, demo.name());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerTabLayout.setAutoSelectionMode(true);
    }
}