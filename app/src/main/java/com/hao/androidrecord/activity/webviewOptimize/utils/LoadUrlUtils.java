package com.hao.androidrecord.activity.webviewOptimize.utils;

import android.text.TextUtils;
import android.webkit.WebView;

public class LoadUrlUtils {

    private LoadUrlUtils(){}


    public static void loadUrl(final WebView web, final String url) {
        if (web == null || TextUtils.isEmpty(url)) {
            return;
        }
        L.d("loadurl:" + url);
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                if ("javascript:".startsWith(url)) {
                    try {
                        web.evaluateJavascript(url, null);
                        i = 1;
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
                if (i == 0) {
                    try {
                        web.loadUrl(url);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

}
