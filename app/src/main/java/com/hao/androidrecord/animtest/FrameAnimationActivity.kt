package com.hao.androidrecord.animtest

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_anim_frame.*


/**
 * 帧动画
 * 使用简单，但是图片多容易oom
 * 优点：使用简单

缺点：图片是全部加载到内存中，可能导致 OOM。

应用场景
基本上很少使用，可以看成 GIF 图。
 */
class FrameAnimationActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_frame)


        testFrameXML()
//        testFrameCode()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //代码设置使用
    private fun testFrameCode(){
        val mAnimationDrawable = AnimationDrawable()

        getDrawable(R.drawable.ic_vip)?.let { mAnimationDrawable.addFrame(it, 100) }
        getDrawable(R.drawable.e1)?.let { mAnimationDrawable.addFrame(it, 100) }
        getDrawable(R.drawable.e2)?.let { mAnimationDrawable.addFrame(it, 100) }
        getDrawable(R.drawable.e3)?.let { mAnimationDrawable.addFrame(it, 100) }
        getDrawable(R.drawable.e4)?.let { mAnimationDrawable.addFrame(it, 100) }

        iv_frame_ani.setImageDrawable(mAnimationDrawable)
        mAnimationDrawable.start()
    }
    //xml方式
    private fun testFrameXML(){
        iv_frame_ani.setImageResource(R.drawable.anim_frame)
        val drawable = iv_frame_ani.drawable as AnimationDrawable
        drawable.start()
//        drawable.stop()
    }
}