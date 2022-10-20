package com.hao.androidrecord.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_coroutine02.*
import kotlinx.coroutines.*

class TestCoroutineActivity03:AppCompatActivity() ,CoroutineScope by MainScope(){
//    private val mainScope: CoroutineScope by lazy {
//        MainScope()
//    }
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


            /*launch {
                //这个方式 上层协程是IO线程所以这个也是默认跟随IO线程不能更新UI
                tv_text.text = "hahahha"
                Log.e("========","mainScope")
            }*/

            launch(Dispatchers.Main) {
                //修改协程执行在主线程，可以修改UI
                //onDestroy的cancel无效
                //猜测此时的launch是asyncScope中的 onDestroy的cancel是主线程的
                tv_text.text = "hahahha"
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