package com.hao.androidrecord.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_coroutine02.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class TestCoroutineActivity06:AppCompatActivity(){
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

        //加上async异步执行 即两个请求同时执行
        //没有async同步执行，也就是一行一行串行执行

        asyncScope.launch {
            val time = measureTimeMillis{
                val one = async { one() }
                val two = async { two() }
                one.await() + two.await()
                Log.e("======","${one.await() + two.await()}")
            }
            Log.e("========Time","${time}")
        }
        Log.e("========","end")


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