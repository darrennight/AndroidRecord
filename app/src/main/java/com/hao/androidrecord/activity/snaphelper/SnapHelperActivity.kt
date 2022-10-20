package com.hao.androidrecord.activity.snaphelper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_snaphelper.*

/**
 * snaphelp 让item对齐某个位置 例如item居中屏幕 顶部对齐底部对齐
 */
class SnapHelperActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snaphelper)
        btn_lin_ver.setOnClickListener {
            startActivity(Intent(this,LinearSnapVerActivity::class.java))
        }
        btn_lin_hor.setOnClickListener {
            startActivity(Intent(this,LinearSnapHoriActivity::class.java))
        }
        btn_page_hor.setOnClickListener {
            startActivity(Intent(this,PageSnapActivity::class.java))
        }
        btn_gravity.setOnClickListener {
            startActivity(Intent(this,GravitySnapHelperActivity::class.java))
        }
    }
}