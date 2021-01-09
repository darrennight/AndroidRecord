package com.hao.androidrecord.activity.MultiScroll.activity;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @Created SiberiaDante
 * @Describe：
 * @CreateTime: 2017/12/5
 * @UpDateTime:
 * @Email: 2654828081@qq.com
 * @GitHub: https://github.com/SiberiaDante
 */


public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
    }

    public void beforeSetContentView() {
    }

    public abstract int setLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initData();
}