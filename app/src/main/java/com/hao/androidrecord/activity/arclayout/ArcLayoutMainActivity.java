package com.hao.androidrecord.activity.arclayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hao.androidrecord.R;

/**
 * CN:      BannerActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/29
 * Des:    弧度ImageView 支持渐变移动
 *
 * https://github.com/maiduoduo/IArcLayout
 *
 * 想要改变弧度的方向，内凹还是外凸，还是正常矩形都依赖app:arc_cropDirection参数
 *
 * 内凹：app:arc_cropDirection="cropInside"
 *
 * 外凸：app:arc_cropDirection="cropOutside"
 *
 * 正常：只需app:arc_height参数值设为0dp,即：app:arc_height="0dp",和app:arc_cropDirection参数无关
 */
public class ArcLayoutMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arclayout_activity_main);
        FloatingActionButton flb_banner = findViewById(R.id.flb_banner);
        flb_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArcLayoutMainActivity.this,BannerActivity.class));
            }
        });
    }
}
