package com.hao.androidrecord.threestateswitch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;


//https://github.com/abbas-oveissi/ThreeStateSwitch
public class ThreeStateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_state);


        ThreeStateSwitch threeState= (ThreeStateSwitch) findViewById(R.id.threeState);
        ThreeStateSwitch threeState1= (ThreeStateSwitch) findViewById(R.id.threeState1);

        threeState.setNormalTextTypeface(FontHelper.get(this,"vazir.ttf"));
        threeState.setSelectedTextTypeface(FontHelper.get(this,"vazir_b.ttf"));

        threeState1.setNormalTextTypeface(FontHelper.get(this,"vazir.ttf"));
        threeState1.setSelectedTextTypeface(FontHelper.get(this,"vazir_b.ttf"));
    }
}
