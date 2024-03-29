package com.hao.androidrecord.activity.LuckyWheel;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.hao.androidrecord.R;

import java.util.ArrayList;
import java.util.List;
//https://github.com/mmoamenn/LuckyWheel_Android
public class LuckWheelActivity extends AppCompatActivity {

    private LuckyWheel lw;
    List<WheelItem> wheelItems ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_wheel);

        generateWheelItems();

        lw = findViewById(R.id.lwv);
        lw.addWheelItems(wheelItems);
        lw.setTarget(1);


        lw.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                Toast.makeText(LuckWheelActivity.this, "Target Reached", Toast.LENGTH_LONG).show();
            }
        });

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lw.rotateWheelTo(1);
            }
        });

    }

    private void generateWheelItems() {
        wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.parseColor("#fc6c6c"), BitmapFactory.decodeResource(getResources(),
                R.drawable.chat) , "100 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#00E6FF"), BitmapFactory.decodeResource(getResources(),
                R.drawable.coupon) , "0 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#F00E6F"), BitmapFactory.decodeResource(getResources(),
                R.drawable.ice_cream), "30 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#00E6FF"), BitmapFactory.decodeResource(getResources(),
                R.drawable.lemonade), "6000 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#fc6c6c"), BitmapFactory.decodeResource(getResources(),
                R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#00E6FF"), BitmapFactory.decodeResource(getResources(),
                R.drawable.shop), "20 $"));
    }
}
