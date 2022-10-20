package com.hao.androidrecord.animtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_anim_test_main.*

class AnimationTestMainActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_test_main)
        btn_frame_anim.setOnClickListener {
            startActivity(Intent(this,FrameAnimationActivity::class.java))
        }
        btn_tween_anim.setOnClickListener {
            startActivity(Intent(this,TweenAnimtionActivity::class.java))
        }
        btn_animator.setOnClickListener {
            startActivity(Intent(this,AnimatorActivity::class.java))
        }
    }
}