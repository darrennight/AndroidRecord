package cn.bpking.app.activity

import android.app.Activity
import android.os.Bundle
import cn.bpking.app.helper.AppDeepLinkModule
import cn.bpking.app.helper.AppDeepLinkModuleRegistry
import com.airbnb.deeplinkdispatch.DeepLinkHandler

@DeepLinkHandler(AppDeepLinkModule::class)
class DeepLinkActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(AppDeepLinkModuleRegistry())
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}