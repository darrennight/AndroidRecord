package com.hao.androidrecord.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.AnimLogoView


class AnimlogoActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_logo)
        val animLogoView = findViewById<AnimLogoView>(R.id.anim_logo)
        animLogoView.addOffsetAnimListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
            }
        })
        animLogoView.addGradientAnimListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
            }
        })
        animLogoView.startAnimation()
    }
}