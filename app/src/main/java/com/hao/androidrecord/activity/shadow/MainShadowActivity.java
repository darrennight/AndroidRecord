package com.hao.androidrecord.activity.shadow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;

/**
 * https://github.com/android-notes/shadow
 * 目前阴影适配有问题
 */
public class MainShadowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_main);

        final EditText lt = (EditText) findViewById(R.id.lt);
        final EditText rt = (EditText) findViewById(R.id.rt);
        final EditText rb = (EditText) findViewById(R.id.rb);
        final EditText lb = (EditText) findViewById(R.id.lb);
        final EditText x = (EditText) findViewById(R.id.xoffset);
        final EditText y = (EditText) findViewById(R.id.yoffset);
        final EditText r = (EditText) findViewById(R.id.radius);
        final EditText color = (EditText) findViewById(R.id.color);
        final RadiusView radiusView = (RadiusView) findViewById(R.id.rv);

        View ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float ltRadius = parseFloat(lt.getText().toString());
                float rtRadius = parseFloat(rt.getText().toString());
                float rbRadius = parseFloat(rb.getText().toString());
                float lbRadius = parseFloat(lb.getText().toString());
                radiusView.setRadius(ltRadius, rtRadius, rbRadius, lbRadius);


                int xOffset = parseInt(x.getText().toString());
                int yOffset = parseInt(y.getText().toString());
                int radius = parseInt(r.getText().toString());
                int c = Color.parseColor("#" + color.getText().toString().trim());
                radiusView.setShadow(xOffset, yOffset, radius, c);
            }
        });

        View container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor((int) (Math.random() * Integer.MAX_VALUE));
            }
        });

        ok.performClick();

    }

    private float parseFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return 0;
        }
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

}