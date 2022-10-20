package com.hao.androidrecord.activity.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_coroutine02.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class TestCoroutineActivity08:AppCompatActivity(){
    private val mainScope: CoroutineScope by lazy {
        MainScope()
    }
    private val asyncScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine02)





    }

    //SupervisorScope 一个协程失败了(异常了) 不会影响其他到协程
    private suspend fun TestSupervisorScope(){

        supervisorScope {
            val job1 = launch {
                delay(2000)
                Log.e("======scope","111111111")
            }

            val job2 = async {
                delay(2000)
                Log.e("======scope","111111111")
                throw NullPointerException()
            }
        }
    }
    //coroutineScope 一个协程失败了(异常了) 所有到协程都会被取消
    private suspend fun TestCoroutineScope(){

        coroutineScope {
            val job1 = launch {
                delay(2000)
                Log.e("======scope","111111111")
            }

            val job2 = async {
                delay(2000)
                Log.e("======scope","111111111")
                throw NullPointerException()
            }
        }
    }

    suspend fun one(): Int {
        delay(1500)
        return 1
    }

    suspend fun two(): Int {
        delay(1500)
        return 2
    }

    override fun onDestroy() {
        super.onDestroy()
        //取消 协程中的执行--进入此界面后关闭界面 launch里面的打印停止
        mainScope.cancel()
    }
}