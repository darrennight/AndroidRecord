/*
 * Copyright  2019  zengp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hao.androidrecord.activity.AdvancedTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;

/**
 * https://github.com/devilist/AdvancedTextView
 */
public class SelectMainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_hori, tv_vert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);

        tv_hori = findViewById(R.id.tv_hori);
        tv_vert = findViewById(R.id.tv_vert);

        tv_hori.setOnClickListener(this);
        tv_vert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_hori:
                HoriActivity.start(this);
                break;
            case R.id.tv_vert:
                VertActivity.start(this);
                break;
        }
    }
}
