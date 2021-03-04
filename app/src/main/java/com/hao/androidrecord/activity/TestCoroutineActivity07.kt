package com.hao.androidrecord.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_coroutine02.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class TestCoroutineActivity07:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine02)
        Log.e("========","start")

        lifecycleScope.launchWhenCreated {  }
        lifecycleScope.launchWhenResumed {  }
        lifecycleScope.launchWhenStarted {  }

        lifecycleScope.launch {
            delay(5000)
            Log.e("========","lifecycleScope")
        }

        Log.e("========","end")
    }


}