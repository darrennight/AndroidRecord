package com.hao.androidrecord.activity

import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_test_webview.*

/**
 * //激活WebView为活跃状态，能正常执行网页的响应
webView.onResume() ；

//当页面被失去焦点被切换到后台不可见状态，需要执行onPause
//通过onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。
webView.onPause()；

//当应用程序(存在webview)被切换到后台时，这个方法不仅仅针对当前的webview而是全局的全应用程序的webview
//它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗。
webView.pauseTimers()

//恢复pauseTimers状态
webView.resumeTimers()；

//销毁Webview
//在关闭了Activity时，如果Webview的音乐或视频，还在播放。就必须销毁Webview
//但是注意：webview调用destory时,webview仍绑定在Activity上
//这是由于自定义webview构建时传入了该Activity的context对象
//因此需要先从父容器中移除webview,然后再销毁webview:
rootLayout.removeView(webView);
webView.destroy();

//生成一个WebView组件(两种方式)
//方式1：直接在在Activity中生成
WebView webView = new WebView(this)
//方法2：在Activity的layout文件里添加webview控件：
WebView webview = (WebView) findViewById(R.id.webView1);

//声明WebSettings子类
WebSettings webSettings = webView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
webSettings.setJavaScriptEnabled(true);

//支持插件
webSettings.setPluginsEnabled(true);

//设置自适应屏幕，两者合用（下面这两个方法合用）
webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
webSettings.setAllowFileAccess(true); //设置可以访问文件
webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


//设置WebView缓存（当加载 html 页面时，WebView会在/data/data/包名目录下生成 database 与 cache 两个文件夹，请求的 URL记录保存在 WebViewCache.db，而 URL的内容是保存在 WebViewCache 文件夹下）
//优先使用缓存:
WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//缓存模式如下：
//LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
//LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
//LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
//LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

//不使用缓存:
WebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

//步骤2. 选择加载方式
//方式a. 加载一个网页：
webView.loadUrl("http://www.google.com/");
//方式b：加载apk包中的html页面
webView.loadUrl("file:///android_asset/test.html");
//方式c：加载手机本地的html页面
webView.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");

作者：AWeiLoveAndroid
链接：https://www.jianshu.com/p/b9164500d3fb
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class WebViewActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_webview)
        setting()

        wv_webview.loadUrl("https://app-v2.dianjing360.cn/doc/tsm/privacy_protection.html")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv_webview.canGoBack()) {
            wv_webview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event)
    }

    private fun setting(){
        wv_webview.webViewClient = object : WebViewClient(){
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()//等待证书响应
            }
        }

        val webSettings: WebSettings = wv_webview.settings
        //设置开启javascript支持
        webSettings.javaScriptEnabled = true
        //设置支持缩放
        webSettings.setSupportZoom(true)
        //开启缩放工具（会出现放大缩小的按钮）
        webSettings.builtInZoomControls = false
        //WebView两种缓存（网页、H5）方式，此处网页不缓存
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        //允许JS打开新窗口（默认false）
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        //打开本地缓存供JS调用
        webSettings.domStorageEnabled = true
        //webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        //H5缓存路径
        val absolutePath = applicationContext.cacheDir.absolutePath
        //H5缓存大小
        webSettings.setAppCachePath(absolutePath)
        //是否允许WebView访问内部文件（默认true）
        webSettings.allowFileAccess = true
        //支持存储H5缓存
        webSettings.setAppCacheEnabled(true)
        //启动概述模式浏览界面，当页面宽度超过WebView显示宽度时，缩小页面适应WebView(默认false)
        webSettings.loadWithOverviewMode = true

        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        //Android 5.0 之后 WebView 禁止加载 http 与 https 混合内容
        if (Build.VERSION.SDK_INT> Build.VERSION_CODES.LOLLIPOP){
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }
    }

    private fun setWebViewSettings(webView: WebView) {
        val webSettings: WebSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true) //支持 JS
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true) //支持通过 JS 打开新的窗口
        //设置自适应屏幕
        webSettings.setUseWideViewPort(true)
        webSettings.setLoadWithOverviewMode(true)
        webSettings.setLoadsImagesAutomatically(true) //设置自动加载图片
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE) //不使用缓存
        //...
    }
}