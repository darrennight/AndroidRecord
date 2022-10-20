package com.hao.androidrecord.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.util.ToastUtil
import com.hao.androidrecord.util.notchlib.INotchScreen
import com.hao.androidrecord.util.notchlib.NotchScreenManager
import kotlinx.android.synthetic.main.activity_check_notch.*

class CheckNotchActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_notch)
        //全面屏 false 刘海水滴true
        btn_check.setOnClickListener {
            NotchScreenManager.getInstance().getNotchInfo(this) { notchScreenInfo ->
                ToastUtil.toastShortMessage("${notchScreenInfo?.hasNotch}")
            }
        }

    }
}