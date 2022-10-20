package com.hao.androidrecord.activity.webviewOptimize

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.hao.androidrecord.R

//https://www.jianshu.com/p/f6d581a51e0d
//https://github.com/yale8848/CacheWebView
class H5WebViewActivity :AppCompatActivity(){
    private val gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h5_web)
        findViewById<View>(R.id.btn_web).setOnClickListener { startActivity(Intent(this@H5WebViewActivity, WebActivity::class.java)) }

//        preloadImage();
    }






}