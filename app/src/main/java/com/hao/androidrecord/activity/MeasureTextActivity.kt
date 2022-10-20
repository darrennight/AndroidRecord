package com.hao.androidrecord.activity

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.util.ViewUtils
import kotlinx.android.synthetic.main.activity_measure_text.*

class MeasureTextActivity:AppCompatActivity() {

    private val paint: Paint by lazy{
        val paint = Paint()
        paint.textSize = ViewUtils.sp2px(12f).toFloat()
        paint
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_text)
        //测量textview设置文字后的宽度，可以判断是超过限定宽度
        val txtWidth = paint.measureText(tv_test_measure.text as String)

    }
}