package com.hao.androidrecord.activity.testviewvgroup

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_test_view_viewgroup.*

class TestViewVGroupActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view_viewgroup)
        view_mnv.setOnClickListener {
            //此view设置了click 不会回调到activity onTouchEvent
            Log.e("=======v","click")
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        //return true false 消费掉事件，activity里面view获取不到事件
        // return super.dispatchTouchEvent(ev) 事件往下传递

        Log.e("=======v","dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("=======v","onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()

    }
}