package com.hao.androidrecord.activity.switchButton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.switchButton.SwitchMultiButton
import kotlinx.android.synthetic.main.activity_mutil_switch.*


class MutilSwtichActivity:AppCompatActivity() {

    private val tabTexts1 = arrayOf("才子", "帅哥", "大湿", "猛将兄")
    private val tabTexts4 = arrayOf("已经", "在家", "等你")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutil_switch)

        switchmultibutton1.apply {
            setText(*tabTexts1)
            setOnSwitchListener(onSwitchListener)
        }

        switchmultibutton2.apply {
            setText("点个Star", "狠心拒绝")
            setOnSwitchListener(onSwitchListener)
        }

        switchmultibutton3.apply {
            setOnSwitchListener(onSwitchListener)
            selectedTab = 1
        }
        switchmultibutton4.apply {
            setText(*tabTexts4)
            setOnSwitchListener(onSwitchListener)
        }

        switchmultibutton6.apply {
            isEnabled = false
        }


    }

    private val onSwitchListener =
        SwitchMultiButton.OnSwitchListener { position, tabText ->
            Toast.makeText(
                this@MutilSwtichActivity,
                tabText,
                Toast.LENGTH_SHORT
            ).show()
        }
}