package com.hao.androidrecord.activity.NotchTools;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.util.NotchTools.NotchTools;


public class FullScreenNoUseNotchActivity extends BaseActivity {

    private ImageView mBackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_no_use_notch);
        mBackView = findViewById(R.id.img_back);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        NotchTools.getFullScreenTools().fullScreenDontUseStatusForActivityOnCreate(this);
    }

    /**
     * onWindowFocusChanged最好也进行全屏适配，防止失去焦点又重回焦点时的flag不正确。
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            NotchTools.getFullScreenTools().fullScreenDontUseStatusForOnWindowFocusChanged(this);
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
