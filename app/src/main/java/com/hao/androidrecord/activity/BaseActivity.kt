package com.hao.androidrecord.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.hao.androidrecord.R
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity

abstract class BaseActivity :RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusColor()
        if (onStatusColor() == ContextCompat.getColor(this, R.color.color_FFFFFF) || isNeedStatusLight()){
            setStatusBackgoundLight()
        }
    }

    private fun setStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //状态栏背景颜色修改只支持5.0以上
            window.statusBarColor = onStatusColor()
        }

    }

    /***
     * 状态栏颜色
     */
    open fun onStatusColor(): Int {
        return ContextCompat.getColor(this, R.color.color_FFFFFF)
    }

    open fun isNeedStatusLight():Boolean{
        return false
    }
    /***
     * 当状态栏背景为白色时需要调用此方法
     */
    protected fun setStatusBackgoundLight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}