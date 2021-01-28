package com.hao.androidrecord.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class TestCoroutineActivity01:AppCompatActivity() ,CoroutineScope by MainScope(){
    /*private val mainScope: CoroutineScope by lazy {
        MainScope()
    }
    private val asyncScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //launch 是mainscope 协程运行的线程是主线程
        Log.e("========","start")

        launch {
            var i=0
            while (i>=0){
                delay(500)

                Log.e("========","delay$i")
                i++
            }


            launch {

                Log.e("========","mainScope")
            }
        }
        Log.e("========","end")
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消 协程中的执行--进入此界面后关闭界面 launch里面的打印停止
        cancel()
    }
}