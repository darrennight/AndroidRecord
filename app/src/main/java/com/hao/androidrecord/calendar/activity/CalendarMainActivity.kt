package com.hao.androidrecord.calendar.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.calendar.activity.lib.CalendarActivity
import com.hao.androidrecord.calendar.activity.lib01.CalendarLib01Activity
import kotlinx.android.synthetic.main.activity_calendar_main.*

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
    }
}