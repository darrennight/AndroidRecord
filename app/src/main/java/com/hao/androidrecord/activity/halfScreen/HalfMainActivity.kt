package com.hao.androidrecord.activity.halfScreen

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_main_half.*

class HalfMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_half)
        val url = "https://www.baidu.com"

        setSettings(webView)
        webView.loadUrl(url)
        imageView.setOnClickListener {
            hidePageWithAnim(topLayout)
        }
    }

    private fun hidePageWithAnim(parentView: ViewGroup) {
        val anim = ObjectAnimator.ofFloat(
            parentView,
            "translationY",
            parentView.translationY,
            parentView.height.toFloat()
        )
        anim.duration = 200
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(p0: Animator?) {
                Log.i("MainActivity", "closed")
            }

            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })
        anim.start()
    }

    private fun setSettings(webView: DragWebView) {
        val settings = webView.settings
        settings.javaScriptEnabled = true//设置WebView属性，能够执行Javascript脚本
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        settings.allowFileAccess = true //设置可以访问文件
        settings.builtInZoomControls = false //设置支持缩放
        settings.setSupportZoom(true)
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }
}