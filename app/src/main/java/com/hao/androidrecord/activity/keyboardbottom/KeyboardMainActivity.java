package com.hao.androidrecord.activity.keyboardbottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;

/**
 * https://github.com/leavesCZY/Keyboard
 */
public class KeyboardMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_key_board);
        findViewById(R.id.btn_unresolved).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KeyboardMainActivity.this, UnresolvedActivity.class));
            }
        });
        findViewById(R.id.btn_resolved).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KeyboardMainActivity.this, ResolvedActivity.class));
            }
        });
    }

}
