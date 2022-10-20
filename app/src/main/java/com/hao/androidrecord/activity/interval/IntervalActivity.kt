package com.hao.androidrecord.activity.interval

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drake.interval.Interval
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_interval.*
import java.util.concurrent.TimeUnit

class IntervalActivity:AppCompatActivity() {

    private lateinit var interval: Interval // 轮询器
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interval)

        interval = Interval(0, 1, TimeUnit.SECONDS, 20).life(this) // 自定义计数器个数的轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时
        // interval = Interval(1, TimeUnit.SECONDS) // 每秒回调一次, 不会自动结束
        interval.subscribe {
            tvFragment.text = it.toString()
        }.finish {
            tvFragment.text = "计时完成"
        }.start()



        btn_start.setOnClickListener {
            interval.start()
        }

        btn_pause.setOnClickListener {
            interval.pause()
        }
        btn_resume.setOnClickListener {
            interval.resume()
        }
        btn_reset.setOnClickListener {
            interval.reset()
        }
        btn_switch.setOnClickListener {
            interval.switch()
        }
        btn_stop.setOnClickListener {
            interval.stop()
        }
        btn_cancel.setOnClickListener {
            interval.cancel()
        }

    }
}