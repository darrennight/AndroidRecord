package com.hao.androidrecord.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.hao.androidrecord.R

/**
 * adb shell
 * am start -W -a android.intent.action.VIEW -d recordLink://newsDetail/det
 * 可以启动这个界面
 */

//使用的是自定义注解
@AppDeepLink(LinkDetail.DP_NEWS_DETAIL)
//deeplink注解
//@DeepLink("recordLink://newsDetail/det")
//@DeepLink("https://dribbble.com/shots/{id}")
open class MainDeepLinkActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_deep_link)
    }
}