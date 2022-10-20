package com.hao.androidrecord.activity.expandrv

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.expandrv.check.MultiCheckActivity
import com.hao.androidrecord.activity.expandrv.expand.ExpandRecycleActivity
import kotlinx.android.synthetic.main.activity_expand_menu.*

class ExpandMenuActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_menu)
        btn_expand.setOnClickListener {
            startActivity(Intent(this, ExpandRecycleActivity::class.java))
        }

        btn_multi_check.setOnClickListener {
            startActivity(Intent(this,MultiCheckActivity::class.java))
        }
    }

}