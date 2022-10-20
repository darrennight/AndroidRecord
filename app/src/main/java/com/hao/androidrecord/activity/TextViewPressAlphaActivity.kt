package com.hao.androidrecord.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_tv_alpha.*

class TextViewPressAlphaActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_alpha)

        tv_alpha.setOnClickListener {  }
    }
}