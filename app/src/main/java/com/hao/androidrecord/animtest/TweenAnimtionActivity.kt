package com.hao.androidrecord.animtest

import android.os.Bundle
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_tween_anim.*


class TweenAnimtionActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tween_anim)
        btn_translate.setOnClickListener {  translatexml() }

        btn_rotate.setOnClickListener {
            rotateAnim()
        }
        btn_rotate_swing.setOnClickListener {
            rotateAnimSwing()
        }
        btn_scale.setOnClickListener {
            scaleAnim()
        }
        btn_alpha.setOnClickListener {
            alphaAnim()
        }
    }

    private fun translatexml(){
        val mAnimation = AnimationUtils.loadAnimation(this, R.anim.test_trans);
        btn_translate.startAnimation(mAnimation);
    }
    /*
          Animation还有几个方法
          setFillAfter(boolean fillAfter)
          如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
          setFillBefore(boolean fillBefore)
          如果fillBefore的值为真的话，动画结束后，控件停留在动画开始的状态
          setStartOffset(long startOffset)
          设置动画控件执行动画之前等待的时间
          setRepeatCount(int repeatCount)
          设置动画重复执行的次数
       */
    private fun translateCode(){
        val animation = TranslateAnimation(0f, 100f, 0f, 100f)
        animation.duration = 1000
        animation.fillAfter = true
        btn_translate.startAnimation(animation)
    }

    private fun rotateAnim(){
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        val lin = LinearInterpolator()
        rotate.interpolator = lin
        rotate.duration = 2000 //设置动画持续周期

        rotate.repeatCount = -1 //设置重复次数 -1循环运行

        rotate.fillAfter = true //动画执行完后是否停留在执行完的状态

        rotate.startOffset = 10 //执行前的等待时间

        btn_rotate.startAnimation(rotate)
    }

    private fun rotateAnimSwing(){
        val rotate = RotateAnimation(
            -45f,
            45f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0f
        )
        val lin = LinearInterpolator()
        rotate.interpolator = lin
        rotate.duration = 1000 //设置动画持续周期

        rotate.repeatCount = -1 //设置重复次数 -1循环运行

        rotate.fillAfter = true //动画执行完后是否停留在执行完的状态

        rotate.startOffset = 10 //执行前的等待时间
        rotate.repeatMode = Animation.REVERSE

        btn_rotate_swing.startAnimation(rotate)
    }

    private fun scaleAnim(){
        val scaleAnim = ScaleAnimation(1f, 1.5f, 1f, 1.5f)
//        scaleAnim.fillAfter = true
        scaleAnim.fillBefore = true
        scaleAnim.duration = 1000
        btn_scale.startAnimation(scaleAnim)

    }

    private fun alphaAnim(){
        val alphaAnim = AlphaAnimation(1f, 0f)
        alphaAnim.duration = 3000
        alphaAnim.fillAfter = true
        btn_alpha.startAnimation(alphaAnim)

    }
}