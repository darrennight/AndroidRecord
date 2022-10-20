package com.hao.androidrecord.animtest

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_animator_test.*

/**
 * Animator:AnimatorSet ValueAnimator
 * ValueAnimator:ObjectAnimator TimeAnimator
 * ObjectAnimator 在每次更新的时候会自动走 setXxx 方法，
 * 所以就不需要像 ValueAnimator 一样手动添加监听器，但是 ValueAnimator 的灵活性更好。
 * TimeAnimator
同样是继承至 ValueAnimator，但是它只做一件事：提供一个时间流，每 18ms 回调一次。

AnimatorSet().apply {
play(aAnimator).before(bAnimator)//a 在b之前播放
play(bAnimator).with(cAnimator)//b和c同时播放动画效果
play(dAnimator).after(cAnimator)//d 在c播放结束之后播放
start()
AnimatorSet().apply {
playSequentially(aAnimator,bAnimator,cAnimator,dAnimator) //顺序播放
start()
}

AnimatorSet().apply {
playTogether(animator,bAnimator,cAnimator,dAnimator) //同时播放
start()
}
AnimatorSet ().apply {
play(aAnimator).after(1000) //1秒后播放a动画
start()
}


 */
class AnimatorActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_test)

        btn_v_anim.setOnClickListener {
//            testValueAnimator()
            testValueAnimator01()
        }
        btn_object_translation.setOnClickListener {
            testObjectAnimatorT()
        }
        btn_object_alpha.setOnClickListener {
            testObjectAlpha()
        }
        btn_object_scale.setOnClickListener {
            testObjectScale()
        }
        btn_object_scale_set.setOnClickListener {
            testAnimaSet()
        }
        btn_object_rotate.setOnClickListener {
            testRotate()
        }
        btn_view_pro_ani.setOnClickListener {

            btn_view_pro_ani.animate().translationX(100f).translationY(100f).start()
//            btn_view_pro_ani.animate().translationZ(100f).start()

//            val animator = btn_view_pro_ani.animate()
//            animator.duration=1000
//            animator.translationXBy(100f)//每次点击都会向右偏移
//            animator.start()

        }
        btn_time_ani.setOnClickListener {
            testTimeAnim()
        }
    }

    private fun testValueAnimator() {
       val mValueAnimator = ValueAnimator()
        mValueAnimator.setIntValues(0, 500)
        mValueAnimator.setDuration(2000)
        mValueAnimator.addUpdateListener { animation ->
            Log.i("=======TAG", "onAnimationUpdate: " + animation.animatedValue)
            val y = animation.animatedValue as Int * 1.0f
            btn_v_anim.translationY = y
        }
        mValueAnimator.start()
    }

    private fun testValueAnimator01(){
        ValueAnimator.ofFloat(0f,500f).apply {
            duration = 2000
            addUpdateListener {
                Log.i("=======TAG", "onAnimationUpdate: " + it.animatedValue)
                btn_v_anim.translationY = it.animatedValue as Float
            }
        }.start()

    }
    private fun testObjectAnimatorT(){
        val animator: ObjectAnimator = ObjectAnimator
            .ofFloat(btn_object_translation, "translationY", 0f, 200f)
            .setDuration(2000)
        animator.start()
    }

    private fun testObjectAlpha(){
        val objectAnimation =ObjectAnimator.ofFloat(btn_object_alpha, "alpha", 1f,0f,1f)
        objectAnimation.duration=3000
        objectAnimation.start()

    }
    private fun testObjectScale(){

        val objectAnimation =ObjectAnimator.ofFloat(btn_object_scale, "scaleX", 1f,2f)
        objectAnimation.duration=3000
        objectAnimation.repeatCount=2
        objectAnimation.repeatMode=ValueAnimator.REVERSE
        objectAnimation.start()



    }

    private fun testAnimaSet(){
        //利用AnimatorSet和ObjectAnimator实现缩放动画
        val animatorSet = AnimatorSet()
//        mImageView.setPivotX(mImageView.getWidth() / 2)
//        mImageView.setPivotY(mImageView.getHeight() / 2)
        animatorSet.playTogether(
//            ObjectAnimator.ofFloat(btn_object_scale_set, "scaleX", 1f, 1.5f).setDuration(3000),
//            ObjectAnimator.ofFloat(btn_object_scale_set, "scaleY", 1f, 1.5f).setDuration(3000)
            ObjectAnimator.ofFloat(btn_object_scale_set, "scaleX", 1f, 1.5f),
            ObjectAnimator.ofFloat(btn_object_scale_set, "scaleY", 1f, 1.5f)
        )
        animatorSet.duration = 3000
        animatorSet.start()
    }

    private fun testRotate(){
        val objectAnimation =
            ObjectAnimator.ofFloat(btn_object_rotate, "rotation", 0f,180f,0f)
        objectAnimation.duration=3000
        objectAnimation.start()


    }

    private fun testTimeAnim(){
        val mTimeAnimator = TimeAnimator()
        mTimeAnimator.setTimeListener { animation, totalTime, deltaTime -> //动画执行的总时间_动画从上一桢到当前桢的间隔时间，单位都是毫秒
            Log.i("=====TAG", "onTimeUpdate: $totalTime   $deltaTime")
        }
    }

}