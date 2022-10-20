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
                //这个是挂起函数，挂起函数就是非阻塞式
                delay(500)

                //不能执行下面耗时相关代码，协程是运行在线程中，这个是运行在主线程中
                //会阻塞主线程，可以执行 挂起函数做耗时操作
                //如果协程执行在主线程中直接运行耗时的代码，也是阻塞主线程，需要使用挂起函数
//                Thread.sleep(10000)

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