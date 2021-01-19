package com.hao.androidrecord.activity.scrollable05

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_main_05.*

/**
 * https://github.com/iftechio/iftech-android-slide-layout
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_05)
        btnDebugScroll.setOnClickListener {
            startDebug(DebugSlideActivity.DEBUG_TYPE_SCROLL)
        }

        btnDebugSlide.setOnClickListener {
            startDebug(DebugSlideActivity.DEBUG_TYPE_SLIDE)
        }
    }

    private fun startDebug(debugType: String) {
        startActivity(Intent(this, DebugSlideActivity::class.java).apply {
            putExtra(DebugSlideActivity.DEBUG_TYPE, debugType)
        })
    }
}
