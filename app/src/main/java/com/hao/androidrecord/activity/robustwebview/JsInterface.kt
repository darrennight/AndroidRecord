package com.hao.androidrecord.activity.robustwebview

import android.webkit.JavascriptInterface
import com.hao.androidrecord.activity.robustwebview.utils.log
import com.hao.androidrecord.activity.robustwebview.utils.showToast

/**
 * @Author: leavesCZY
 * @Date: 2021/9/21 15:08
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class JsInterface {

    @JavascriptInterface
    fun showToastByAndroid(log: String) {
        log("showToastByAndroid:$log")
        showToast(log)
    }

}
