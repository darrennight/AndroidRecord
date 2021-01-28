package com.hao.androidrecord.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_coroutine02.*
import kotlinx.coroutines.*

class TestCoroutineActivity02:AppCompatActivity(){
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
            //io线程执行
            //不会阻塞主线程 阻塞当前协程 会先执行 end打印
            delay(5000)
            Log.e("========","delay")
            //改协程是运行在io线程不能修改UI，只有运行在主线程的才能修改UI
//            tv_text.text = "asyncScope"

            mainScope.launch {
                //切换到主线程
                //没有取消 退出activity还会继续执行
                Log.e("========","mainScope")
                tv_text.text = "mainScope"
            }
        }
        Log.e("========","end")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}