package com.hao.androidrecord.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

/**
 * ViewModel里面使用要在onCleared方法里面cancel掉协程
 *
 * SupervisorJob()
 * 为了解决上述问题，可以使用SupervisorJob替代Job，SupervisorJob与Job基本类似，区别在于不会被子协程的异常所影响。
 */
class TestCoroutineActivity:AppCompatActivity() {
    //主线程
    private val mainScope: CoroutineScope by lazy {
        MainScope()
    }
    //io线程
    private val asyncScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("========","start")

        asyncScope.launch {
            //io线程执行
            //不会阻塞主线程 阻塞当前协程 会先执行 end打印
            delay(5000)
            Log.e("========","delay")

            mainScope.launch {
                //切换到主线程
                Log.e("========","mainScope")
            }
        }
        Log.e("========","end")
    }

    override fun onDestroy() {
        super.onDestroy()
        //协程停止 里面的代码终止执行
        asyncScope.cancel()
    }
}