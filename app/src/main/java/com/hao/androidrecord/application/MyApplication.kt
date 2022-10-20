package com.hao.androidrecord.application

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Process
import com.github.gzuliyujiang.oaid.DeviceIdentifier
import com.hao.androidrecord.activity.chatview.chatlib.AppUtils
import com.hao.androidrecord.activity.robustwebview.base.WebViewInitTask
import com.hao.androidrecord.activity.robustwebview.utils.ContextHolder
import com.hao.androidrecord.activity.rv_vp.DiscreteScrollViewOptions
import com.hao.androidrecord.activity.webviewOptimize.PreloadWebView
import com.hjq.toast.ToastUtils
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates


@HiltAndroidApp
class MyApplication:Application() {


    companion object {
        var instance: MyApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.application = this
        WebViewInitTask.init(this)

        // 初始化 Toast 框架
        ToastUtils.init(this);

        DeviceIdentifier.register(this)
        AppUtils.init(this);

        if (getCurrentProcessName() == packageName){
            preloadWebView()
            DiscreteScrollViewOptions.init(this);
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this

    }

    private fun preloadWebView() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                PreloadWebView.getInstance().preload()
            }

            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {
                PreloadWebView.getInstance().preload()
            }
        })
    }


    private fun getCurrentProcessName(): String {
        val pid = Process.myPid()
        var currentProcessName = ""
        val activityManager =
            applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses =
            activityManager.runningAppProcesses
        for (processInfo in runningAppProcesses) {
            if (pid == processInfo.pid) {
                currentProcessName = processInfo.processName
            }
        }
        return currentProcessName
    }

    /**
     * 获得指定进程名称
     * @param context
     * @param pid
     * @return
     */
    fun getProcessName(context: Context, pid: Int): String {
        val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val runningApps = am.runningAppProcesses ?: return ""
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return ""
    }
}