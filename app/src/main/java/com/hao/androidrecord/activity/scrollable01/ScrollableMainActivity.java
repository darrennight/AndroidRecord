package com.hao.androidrecord.activity.scrollable01;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.scrollable01.fragment.PagerHeaderFragment;
import com.hao.androidrecord.activity.scrollable01.fragment.ParallaxImageHeaderFragment;
import com.hao.androidrecord.activity.scrollable01.fragment.SimpleDemoFragment;


public class ScrollableMainActivity extends AppCompatActivity {

    private View scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_main);
        scrollView = findViewById(R.id.scrollView);
    }

    public void showParallaxFragment(View view) {
        showFragment(ParallaxImageHeaderFragment.class);
    }

    public void showPagerHeaderFragment(View view) {
        showFragment(PagerHeaderFragment.class);
    }

    public void showSimpleDemoFragment(View view) {
        showFragment(SimpleDemoFragment.class);
    }

    public <T extends Fragment> void showFragment(Class<T> clzz) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        try {
            if (fragment == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, clzz.newInstance(), clzz.getSimpleName()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, clzz.newInstance(), clzz.getSimpleName()).commit();
            }
            getSupportActionBar().setTitle(clzz.getSimpleName());
            scrollView.setVisibility(View.GONE);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (!removeFragment()) {
            super.onBackPressed();
        } else {
            scrollView.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }

    public boolean removeFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            return true;
        } else {
            return false;
        }
    }
}