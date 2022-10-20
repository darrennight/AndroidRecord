package com.hao.androidrecord.activity.robustwebview.base

import android.app.Application
import android.content.Context
import com.hao.androidrecord.activity.robustwebview.utils.log
import com.hao.androidrecord.activity.robustwebview.utils.showToast
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk

/**
 * @Author: leavesCZY
 * @Date: 2021/9/20 23:47
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
object WebViewInitTask {

    fun init(application: Application) {
        initWebView(application)
        WebViewCacheHolder.init(application)
        WebViewInterceptRequestProxy.init(application)
    }

    private fun initWebView(context: Context) {
        QbSdk.setDownloadWithoutWifi(true)
        val map = mutableMapOf<String, Any>()
        map[TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(map)
        val cb: QbSdk.PreInitCallback = object : QbSdk.PreInitCallback {
            override fun onViewInitFinished(arg0: Boolean) {
                showToast("onViewInitFinished: $arg0")
                log("onViewInitFinished: $arg0")
            }

            override fun onCoreInitFinished() {
                log("onCoreInitFinished")
            }
        }
        QbSdk.initX5Environment(context, cb)
    }

}
