package com.hao.androidrecord.activity.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_coroutine02.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/*whenResumed和launchWhenResumed执行时机一样，区别在于：
    whenResumed 可以有返回结果
    launchWhenResumed 返回的是Job对象*/
class TestCoroutineActivity07:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine02)
        Log.e("========","start")

        //create方法执行后执行里面的代码逻辑
        lifecycleScope.launchWhenCreated {  }
        lifecycleScope.launch {
            whenResumed {
                // do
            }
        }
        //Resume方法执行后执行里面的代码逻辑 第一次resume调用不会每次resume都会调用
        lifecycleScope.launchWhenResumed {
            Log.e("=======launch","launchWhenResumedlaunchWhenResumed")
        }
        //Starte方法执行后执行里面的代码逻辑
        lifecycleScope.launchWhenStarted {  }

        lifecycleScope.launch {
            delay(5000)
            Log.e("========","lifecycleScope")
        }

        //lifecycleScope默认主线程，可以通过withContext来指定线程。
        lifecycleScope.launch {
            // do
            withContext(Dispatchers.IO) {
                // do
            }
        }
        lifecycleScope.launch(Dispatchers.IO){
            // do io线程
        }

        Log.e("========","end")
    }



}