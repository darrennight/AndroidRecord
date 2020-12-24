package com.hao.androidrecord.activity.parallaxdecoration

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R

/**
 * https://github.com/seagazer/parallaxdecoration
 */
class ParallaxMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_parallax)
    }

    fun horizontal(view: View) {
        startActivity(Intent(this, HorizontalActivity::class.java))
    }

    fun vertical(view: View) {
        startActivity(Intent(this, VerticalActivity::class.java))
    }
}
