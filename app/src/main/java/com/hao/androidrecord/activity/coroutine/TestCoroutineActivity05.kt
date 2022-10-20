package com.hao.androidrecord.activity.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class TestCoroutineActivity05:AppCompatActivity() ,CoroutineScope by MainScope(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //launch 是mainscope 协程运行的线程是主线程
        Log.e("========","start")

        launch {

            /*val tem = withContext(Dispatchers.IO){
                //通过此种方式让运行在主线程的协程里面也可以执行耗时操作
                var i=0
                while (i>=0){

                    Thread.sleep(10000)
                    Log.e("========","delay$i")
                    i++
                }
                "haha"
            }*/

            //提取为方法
            testSleep()

            launch {

                Log.e("========","mainScope")
            }
        }
        Log.e("========","end")
    }


    /**
     * suspend 挂起函数
     * 只能运行在 协程里面或者suspend修饰的挂起函数里面
     * 是非阻塞式的不会阻塞线程
     */
    private suspend fun testSleep(){
        //lifecycleScope默认主线程，可以通过withContext来指定线程。
        val tem = withContext(Dispatchers.IO){
            //通过此种方式让运行在主线程的协程里面也可以执行耗时操作
            var i=0
            while (i>=0){

                Thread.sleep(10000)
                Log.e("========","delay$i")
                i++
            }
            "haha"
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //取消 协程中的执行--进入此界面后关闭界面 launch里面的打印停止
        cancel()
    }



}