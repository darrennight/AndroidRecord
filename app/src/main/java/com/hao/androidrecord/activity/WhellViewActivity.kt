package com.hao.androidrecord.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.wheelview.adapter.ArrayWheelAdapter
import kotlinx.android.synthetic.main.activity_wheel_view.*

class WhellViewActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel_view)

        val list = arrayListOf("2021","2022","2023","2024","2025","2026","2027")
        year.adapter = ArrayWheelAdapter(list)
    }
}