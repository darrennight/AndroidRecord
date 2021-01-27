package com.hao.androidrecord.activity.NotchTools;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hao.androidrecord.R;
import com.hao.androidrecord.util.NotchTools.NotchTools;
import com.hao.androidrecord.util.NotchTools.core.NotchProperty;
import com.hao.androidrecord.util.NotchTools.core.OnNotchCallBack;

/**
 * https://github.com/smarxpan/NotchScreenTool
 */
public class FullScreenUseNotchActivity extends BaseActivity implements OnNotchCallBack {

    private ImageView mBackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_use_notch);
        mBackView = findViewById(R.id.img_back);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        NotchTools.getFullScreenTools().fullScreenUseStatusForActivityOnCreate(this, this);
    }

    @Override
    public void onNotchPropertyCallback(NotchProperty notchProperty) {
        int marginTop = notchProperty.getMarginTop();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mBackView.getLayoutParams();
        layoutParams.topMargin += marginTop;
        mBackView.setLayoutParams(layoutParams);
    }

    /**
     * onWindowFocusChanged最好也进行全屏适配，防止失去焦点又重回焦点时的flag不正确。
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            NotchTools.getFullScreenTools().fullScreenUseStatusForOnWindowFocusChanged(this);
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
