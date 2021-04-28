package com.hao.androidrecord.activity.cusTab;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.cusTab.autoselect.DemoAutoSelectActivity;
import com.hao.androidrecord.activity.cusTab.basic.DemoBasicActivity;
import com.hao.androidrecord.activity.cusTab.customview01.DemoCustomView01Activity;
import com.hao.androidrecord.activity.cusTab.customview02.DemoCustomView02Activity;
import com.hao.androidrecord.activity.cusTab.imitationloop.DemoImitationLoopActivity;
import com.hao.androidrecord.activity.cusTab.rtl.DemoRtlActivity;
import com.hao.androidrecord.activity.cusTab.tabonscreenlimit.DemoTabOnScreenLimitActivity;
import com.hao.androidrecord.activity.cusTab.tabscrolldisabled.DemoTabScrollDisabledActivity;
import com.hao.androidrecord.activity.cusTab.years.DemoYearsActivity;

public class TabMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        ListView listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        for (Demo demo : Demo.values()) {
            adapter.add(getString(demo.titleResId));
        }

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Demo demo = Demo.values()[position];
        switch (demo) {
            case BASIC:
                DemoBasicActivity.startActivity(this, demo);
                break;

            case AUTO_SELECT:
                DemoAutoSelectActivity.startActivity(this, demo);
                break;

            case CUSTOM_VIEW01:
                DemoCustomView01Activity.startActivity(this, demo);
                break;

            case CUSTOM_VIEW02:
                DemoCustomView02Activity.startActivity(this, demo);
                break;

            case YEARS:
                DemoYearsActivity.startActivity(this, demo);
                break;

            case IMITATION_LOOP:
                DemoImitationLoopActivity.startActivity(this, demo);
                break;

            case RTL:
                DemoRtlActivity.startActivity(this, demo);
                break;

            case TAB_SCROLL_DISABLED:
                DemoTabScrollDisabledActivity.startActivity(this, demo);
                break;

            case TAB_ON_SCREEN_LIMIT:
                DemoTabOnScreenLimitActivity.startActivity(this, demo);
                break;
        }
    }

    private void openGitHub() {
        Uri uri = Uri.parse(getString(R.string.app_github_url));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
