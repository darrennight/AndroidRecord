package com.hao.androidrecord.activity.coffeeView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;

//https://github.com/samlss/CoffeeView
public class CoffeeViewActivity extends AppCompatActivity {
    private CoffeeView coffeeView1;
    private CoffeeView coffeeView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_view);
        coffeeView1 = findViewById(R.id.cv1);
        coffeeView2 = findViewById(R.id.cv2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        coffeeView1.release();
        coffeeView2.release();
    }

    public void onStart(View view) {
        coffeeView1.start();
        coffeeView2.start();
    }

    public void onStop(View view) {
        coffeeView1.stop();
        coffeeView2.stop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        ViewGroup.LayoutParams layoutParams = coffeeView1.getLayoutParams();
//        layoutParams.width = 100;
//        layoutParams.height = 100;
//        coffeeView1.setLayoutParams(layoutParams);

//        coffeeView1.setCoasterColor(Color.RED);
//        coffeeView1.setCupColor(Color.BLUE);
//        coffeeView1.setVaporColor(Color.GREEN);
        return super.onKeyDown(keyCode, event);
    }
}
