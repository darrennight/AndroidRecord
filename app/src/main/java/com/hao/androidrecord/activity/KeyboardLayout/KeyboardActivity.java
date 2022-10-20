package com.hao.androidrecord.activity.KeyboardLayout;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;


/**
 * Created by Liliang
 * Email: 53127822@qq.com
 * https://github.com/llwl1982/FloatOnKeyboardLayout
 */

public class KeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_key_board_layout);

        FloatOnKeyboardLayout floatOnKeyboardLayout = (FloatOnKeyboardLayout) findViewById(R.id.root_view);
        floatOnKeyboardLayout.setAnchor(findViewById(R.id.anchor));

        floatOnKeyboardLayout.setPopupListener(new FloatOnKeyboardLayout.OnKeyboardPopupListener() {
            @Override
            public void onKeyboardPopup(boolean isPop) {
                Toast.makeText(KeyboardActivity.this, "popup: " + isPop, Toast.LENGTH_SHORT).show();
            }
        });

        floatOnKeyboardLayout.setMarginKeyboard(100);
    }
}
