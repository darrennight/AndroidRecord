package com.hao.androidrecord.activity.autoscrollview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_auto_scroll.*

/**
 * https://github.com/hurryD/autoscrollview
 */
class AutoScrollActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_scroll)
    }

    override fun onResume() {
        super.onResume()
        asView_main.onResume()
    }

    override fun onPause() {
        super.onPause()
        asView_main.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        asView_main.onDestory()
    }
}