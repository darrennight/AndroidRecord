package com.hao.androidrecord.activity.scalingLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;
//https://github.com/iammert/ScalingLayout
public class ScalingLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaling_layout);


        findViewById(R.id.demo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScalingLayoutActivity.this, CollapsingDemoActivity.class));
            }
        });

        findViewById(R.id.demo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScalingLayoutActivity.this, FABDemo.class));
            }
        });

        findViewById(R.id.demo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScalingLayoutActivity.this, SearchBarDemoActivity.class));
            }
        });
    }
}
