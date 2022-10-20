package com.hao.androidrecord.activity.ticket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hao.androidrecord.R


class ExampleActivity : BaseActivity() {

    fun launchActivity(startingActivity: Context) {
        val intent = Intent(startingActivity, ExampleActivity::class.java)
        startingActivity.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        isDisplayHomeAsUpEnabled = true
    }
}
