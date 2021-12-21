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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.AdvancedTextView.lib.ActionMenu;
import com.hao.androidrecord.activity.AdvancedTextView.lib.CustomActionMenuCallBack;
import com.hao.androidrecord.activity.AdvancedTextView.lib.SelectableTextView;
import com.hao.androidrecord.activity.AdvancedTextView.lib.StringContentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengp on 2017/12/2.
 */

public class HoriActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener, CustomActionMenuCallBack {

    private RadioGroup rg_text_gravity;
    private RadioGroup rg_text_content;

    private SelectableTextView selectableTextView;

    public static void start(Context context) {
        Intent starter = new Intent(context, HoriActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hori);
        initView();
    }

    private void initView() {
        selectableTextView = (SelectableTextView) findViewById(R.id.ctv_content);
        selectableTextView.setText(Html.fromHtml(StringContentUtil.str_hanzi).toString());
        selectableTextView.clearFocus();
        selectableTextView.setTextJustify(true);
        selectableTextView.setForbiddenActionMenu(false);
        selectableTextView.setCustomActionMenuCallBack(this);
        selectableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HoriActivity.this, "SelectableTextView 的onClick事件", Toast.LENGTH_SHORT).show();
            }
        });

        rg_text_gravity = (RadioGroup) findViewById(R.id.rg_text_gravity);
        rg_text_content = (RadioGroup) findViewById(R.id.rg_text_content);
        ((RadioButton) findViewById(R.id.rb_justify)).setChecked(true);
        ((RadioButton) findViewById(R.id.rb_hanzi)).setChecked(true);
        rg_text_gravity.setOnCheckedChangeListener(this);
        rg_text_content.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_justify:
                selectableTextView.setTextJustify(true);
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_left:
                selectableTextView.setTextJustify(false);
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_hanzi:
                selectableTextView.setText(Html.fromHtml(StringContentUtil.str_hanzi).toString());
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_en:
                selectableTextView.setText(Html.fromHtml(StringContentUtil.str_en).toString());
                selectableTextView.postInvalidate();
                break;
            case R.id.rb_muti:
                selectableTextView.setText(Html.fromHtml(StringContentUtil.str_muti).toString());
                selectableTextView.postInvalidate();
                break;
        }

    }

    @Override
    public boolean onCreateCustomActionMenu(ActionMenu menu) {
        menu.setActionMenuBgColor(0xff666666);  // ActionMenu背景色
        menu.setMenuItemTextColor(0xffffffff);  // ActionMenu文字颜色
        List<String> titleList = new ArrayList<>();
        titleList.add("翻译");
        titleList.add("分享");
        titleList.add("分享");
        menu.addCustomMenuItem(titleList);  // 添加菜单
        return false; // 返回false，保留默认菜单(全选/复制)；返回true，移除默认菜单
    }

    @Override
    public void onCustomActionItemClicked(String itemTitle, String selectedContent) {
        Toast.makeText(this, "ActionMenu: " + itemTitle, Toast.LENGTH_SHORT).show();
    }

}
