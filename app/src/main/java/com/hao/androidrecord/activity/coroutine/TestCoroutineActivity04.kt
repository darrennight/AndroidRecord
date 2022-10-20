package com.hao.androidrecord.activity.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_coroutine02.*
import kotlinx.coroutines.*

class TestCoroutineActivity04:AppCompatActivity(){
    private val mainScope: CoroutineScope by lazy {
        MainScope()
    }
    private val asyncScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine02)
        //launch 是mainscope 协程运行的线程是主线程
        Log.e("========","start")

        asyncScope.launch {
            delay(5000)
            Log.e("========","asyncScope::${Thread.currentThread().name}")

            launch {
                Log.e("========","launch::${Thread.currentThread().name}")
            }

            mainScope.launch() {

                tv_text.text = "hahahha"
                Log.e("========","mainScope")
                Log.e("========","launch::${Thread.currentThread().name}")
            }
        }
        Log.e("========","end")

    }

    override fun onDestroy() {
        super.onDestroy()
        //取消 协程中的执行--进入此界面后关闭界面 launch里面的打印停止
        mainScope.cancel()
    }
}