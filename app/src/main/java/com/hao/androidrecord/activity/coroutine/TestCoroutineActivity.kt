package com.hao.androidrecord.activity.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

/**
 * ViewModel里面使用要在onCleared方法里面cancel掉协程
 *
 * SupervisorJob()
 * 为了解决上述问题，可以使用SupervisorJob替代Job，SupervisorJob与Job基本类似，区别在于不会被子协程的异常所影响。
 *
 * GloalScope 生命周期 整个app
 * MainScope 在activity中使用 需要onDestroy需要手动取消的，否则会有内存泄露的风险。
 * viewmodelScope 绑定viewmodel生命周期
 * lifecycleScope 绑定activity fragment生命周期
 * */
class TestCoroutineActivity:AppCompatActivity() {
    //主线程 MainScope是需要手动取消的，否则会有内存泄露的风险。
    private val mainScope: CoroutineScope by lazy {
        MainScope()//就是 ContextScope(SupervisorJob() + Dispatchers.Main)和下面的async只是线程不一样
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
            Log.e("========","mainScope下面的代码")
        }
        Log.e("========","end")

//        01-20 11:06:53.690 12618 12618 E ========: start
//        01-20 11:06:53.725 12618 12618 E ========: end
//        01-20 11:06:58.736 12618 12731 E ========: delay
//        01-20 11:06:58.746 12618 12731 E ========: mainScope下面的代码
//        01-20 11:06:58.746 12618 12618 E ========: mainScope
    }

    override fun onDestroy() {
        super.onDestroy()
        //协程停止 里面的代码终止执行
        asyncScope.cancel()
    }
}