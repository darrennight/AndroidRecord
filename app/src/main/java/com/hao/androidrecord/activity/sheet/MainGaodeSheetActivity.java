package com.hao.androidrecord.activity.sheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.sheet.gaode.GaoDeDetailActivity;
import com.hao.androidrecord.activity.sheet.gaode.GaoDeIndexActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGaodeIndoex(View view) {
        startActivity(new Intent(this, GaoDeIndexActivity.class));
    }


    public void onGaodeDetail(View view) {
        startActivity(new Intent(this, GaoDeDetailActivity.class));
    }

}
