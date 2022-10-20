package com.hao.androidrecord.activity.webviewOptimize;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.webviewOptimize.utils.L;
import com.hao.androidrecord.activity.webviewOptimize.utils.LoadUrlUtils;
import com.hao.androidrecord.activity.webviewOptimize.utils.TT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

//https://github.com/yuweiguocn/TouTiaoH5
//https://yuweiguocn.github.io/android-h5/
public class WebActivity extends AppCompatActivity {

    private WebView mWebView;
    private Handler mHandler = new Handler();
    private int delay = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        LinearLayout ll_content = findViewById(R.id.ll_content);

        mWebView = PreloadWebView.getInstance().getWebView(WebActivity.this);
//        setDrawDuringWindowsAnimating(mWebView);
        ll_content.addView(mWebView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                L.d("onPageStarted:  url="+ url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                L.d("onPageFinished:  url="+ url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                L.d("shouldOverrideUrlLoading: url=" + url);
                return shouldIntercept(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(view, request.getUrl().toString());
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                L.d("shouldInterceptRequest: url="+ url);
                return super.shouldInterceptRequest(view, url);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return shouldInterceptRequest(view,request.getUrl().toString());
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                L.d("onConsoleMessage:" + consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }
        });
        loadContent(0);

    }

    private void loadContent(long delay) {
        L.d("loadContent:" + delay);
        delay += 100;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadUrlUtils.loadUrl(mWebView, getJsContent());
            }
        }, delay);
    }

    private boolean shouldIntercept(WebView webView, String url) {
        if (!url.startsWith("bytedance://") && !url.startsWith("sslocal://")) {
            return false;
        }
        Uri parse = null;
        try {
            parse = Uri.parse(url);
        } catch (Exception e) {
            L.e(e);
        }
        if (parse != null) {
            boolean detectJs="detectJs".equals(parse.getHost());
            boolean setcontent="setContent".equals(parse.getQueryParameter("function"));
            boolean result="false".equals(parse.getQueryParameter("result"));
            if (detectJs && setcontent && result) {
                PreloadWebView.loadBaseHtml(webView);
                loadContent(delay);
            }
            TT.s(parse.getHost());
        }
        return true;
    }


    private String getJsContent() {
        InputStream is = null;
        StringBuilder content = new StringBuilder();
        try {
            is = getAssets().open("jscontent.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String temp;
            while ((temp = br.readLine()) != null) {
                content.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null){
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }



    /**
     * 让 activity transition 动画过程中可以正常渲染页面
     */
    private void setDrawDuringWindowsAnimating(View view) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // 1 android n以上  & android 4.1以下不存在此问题，无须处理
            return;
        }
        // 4.2不存在setDrawDuringWindowsAnimating，需要特殊处理
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            handleDispatchDoneAnimating(view);
            return;
        }
        try {
            // 4.3及以上，反射setDrawDuringWindowsAnimating来实现动画过程中渲染
            ViewParent rootParent = view.getRootView().getParent();
            Method method = rootParent.getClass()
                    .getDeclaredMethod("setDrawDuringWindowsAnimating", boolean.class);
            method.setAccessible(true);
            method.invoke(rootParent, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * android4.2可以反射handleDispatchDoneAnimating来解决
     */
    private void handleDispatchDoneAnimating(View paramView) {
        try {
            ViewParent localViewParent = paramView.getRootView().getParent();
            Class localClass = localViewParent.getClass();
            Method localMethod = localClass.getDeclaredMethod("handleDispatchDoneAnimating");
            localMethod.setAccessible(true);
            localMethod.invoke(localViewParent);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

}
