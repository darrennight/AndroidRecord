package com.hao.androidrecord.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_alpha_widow.*

class AlphaActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alpha_widow)
        btn_start.setOnClickListener {
            /** 窗口背景变暗*/
            dimBackground(1.0f,0.5f);


            /** 窗口背景变亮*/
//            dimBackground(0.5f,1.0f);
        }
    }

    /**
     * 调整窗口的透明度
     * @param from>=0&&from<=1.0f
     * @param to>=0&&to<=1.0f
     *
     */
    private fun dimBackground(from: Float, to: Float) {
        val window: Window = window
        val valueAnimator: ValueAnimator = ValueAnimator.ofFloat(from, to)
        valueAnimator.setDuration(500)
        valueAnimator.addUpdateListener { animation ->
            val params: WindowManager.LayoutParams = window.getAttributes()
            params.alpha = animation.getAnimatedValue() as Float
            window.setAttributes(params)
        }
        valueAnimator.start()
    }
}