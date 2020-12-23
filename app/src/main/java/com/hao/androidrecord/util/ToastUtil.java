package com.hao.androidrecord.util;

import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hao.androidrecord.application.MyApplication;

/**
 * UI通用方法类
 */
public class ToastUtil {

    @Nullable
    private static Toast mToast;

    public static final void toastLongMessage(final String message) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(MyApplication.Companion.getInstance(), message, Toast.LENGTH_LONG);
                mToast.show();
            }
        });
    }


    public static final void toastShortMessage(final String message) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(MyApplication.Companion.getInstance(), message, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }
}
