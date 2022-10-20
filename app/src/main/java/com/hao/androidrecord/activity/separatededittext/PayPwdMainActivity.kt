package com.hao.androidrecord.activity.separatededittext

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_pay_pwd.*
//https://github.com/WGwangguan/SeparatedEditText
class PayPwdMainActivity : AppCompatActivity() {
    var showContent = false
    var showCursor = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_pwd)

        edit_solid.setTextChangedListener(object : SeparatedEditText.TextChangedListener {
            override fun textChanged(changeText: CharSequence?) {
            }

            override fun textCompleted(text: CharSequence?) {
                edit_solid.showError()
            }

        })
        edit_underline.setTextChangedListener(object : SeparatedEditText.TextChangedListener {
            override fun textChanged(changeText: CharSequence?) {
            }

            override fun textCompleted(text: CharSequence?) {
                edit_underline.showError()
            }

        })
    }

    fun handleContent(v: View?) {
        edit_solid.setPassword(!showContent)
        edit_hollow.setPassword(!showContent)
        edit_underline.setPassword(!showContent)
        showContent = !showContent
    }

    fun handleCursor(v: View?) {
        edit_solid.setShowCursor(!showCursor)
        edit_hollow.setShowCursor(!showCursor)
        edit_underline.setShowCursor(!showCursor)
        showCursor = !showCursor
    }
}
