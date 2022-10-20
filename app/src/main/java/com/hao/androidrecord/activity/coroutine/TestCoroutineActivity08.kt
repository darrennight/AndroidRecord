package com.hao.androidrecord.activity.coroutine

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
                val result = one.await() + two.await()
//                Log.e("======Time","${one.await() + two.await()}")
                Log.e("======Time","$result")

                async { Log.e("======Time","async后面打代码11111") }
                Log.e("======Time","async后面打代码22222")


            }
            Log.e("========Time","${time}")
        }
        Log.e("========","end")
        //执行结果：async上面会同时异步执行 然后串行执行 await 和 log，然后asyncLog挂起到异步，挂起后面的代码继续执行，下面到log开始执行
        //await等待上面的执行后再执行

//        01-19 17:30:36.592 19527 19835 E ======Time: 3
//        01-19 17:30:36.593 19527 19835 E ======Time: async后面打代码22222
//        01-19 17:30:36.593 19527 19835 E ========Time: 1515
//        01-19 17:30:36.593 19527 19840 E ======Time: async后面打代码11111


        asyncScope.launch{
            val job = asyncScope.launch {
                delay(1000L)
                println("World!")
                delay(1000L)
            }
            println("Hello,")
            //join 等待job里面延时执行完才打应good
            job.join()
            println("Good！")
        }




        asyncScope.launch {
            val time = measureTimeMillis{
                Log.e("======async","111111111")
                val one = async { one() }
                Log.e("======async","22222")
                delay(5000L)
                val two = async { two() }
                val result = one.await() + two.await()
                Log.e("======async","$result")
                //await执行完了才能执行下面到代码
                Log.e("======async","33333333")


            }
            //delay 阻塞下面的代码执行包括下面的async 但是 one和delay同时执行 async是异步出去执行
            //协程里面的代码块也是串行执行的 但是async是可以让两个连续async同时异步执行
            //但是执行到async时 async下面到代码可以同时执行
            Log.e("========async","${time}")//=====async: 6513
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