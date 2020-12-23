package com.hao.androidrecord.util

import android.app.Activity
import android.content.Context
import android.view.Display
import android.view.WindowManager
import com.hao.androidrecord.application.MyApplication

object ViewUtils {

    fun dp2px(dpValue: Float): Int{
        val scale = MyApplication.instance.resources.displayMetrics.density
        return(dpValue * scale + 0.5f).toInt()
    }

    fun sp2px(spValue: Float): Int {
        val scale = MyApplication.instance.resources.displayMetrics.scaledDensity
        return (spValue * scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕宽度大小，单位px

     * @param context
     * *
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val display = getDisplay(context)
        val screenWidth = display.width
        return screenWidth
    }

    /**
     * 获取屏幕高度大小，单位px

     * @param context
     * *
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val display = getDisplay(context)
        val screenHeight = display.height
        return screenHeight
    }
    /**
     * 获取显示设备参数

     * @param context
     * *
     * @return
     */
    fun getDisplay(context: Context): Display {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay
    }

    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        //获取NavigationBar的高度
        return resources.getDimensionPixelSize(resourceId)
    }
}