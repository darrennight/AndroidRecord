package com.hao.androidrecord.calendar.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.calendar.activity.lib.CalendarActivity
import com.hao.androidrecord.calendar.activity.lib01.CalendarLib01Activity
import com.hao.androidrecord.calendar.activity.lib02.CalendarLib02Activity
import kotlinx.android.synthetic.main.activity_calendar_main.*
import kotlinx.android.synthetic.main.activity_luck_wheel.*

class CalendarMainActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_main)
        btn_calendar.setOnClickListener {
            startActivity(
                Intent(this@CalendarMainActivity,
                CalendarActivity::class.java)
            )
        }
        btn_calendar_lib01.setOnClickListener {
            startActivity(
                Intent(this@CalendarMainActivity,
                    CalendarLib01Activity::class.java)
            )
        }

        btn_calendar_lib02.setOnClickListener {
            startActivity(Intent(this, CalendarLib02Activity::class.java))
        }
    }
}