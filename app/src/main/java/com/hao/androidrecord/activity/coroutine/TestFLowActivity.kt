package com.hao.androidrecord.activity.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hao.androidrecord.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

/**
 * 每一个 Flow 其内部是按照顺序执行的，这一点跟 Sequences 很类似。

Flow 跟 Sequences 之间的区别是 Flow 不会阻塞主线程的运行，而 Sequences 会阻塞主线程的运行。

Flow是一种类似于序列的冷流，flow构建器中的代码直到流被收集的时候才运行。 当调用collect时才执行
channelFlow是热流！

冷指的是 无消费者时 则不会生产数据

热指的是 无观察者时 也会生产数据
 */
class TestFLowActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_flow)

        lifecycleScope.launch {
            flowTest03()
        }


    }


    //与 collect的区别是 ，有新值发出时，如果此时上个收集尚未完成，则会取消掉上个值的收集操作
    private suspend fun flowTest(){
       listOf(1,2,3).asFlow().collectLatest {  }

       listOf(1,2,3).asFlow().collect {  }

        flow {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }
            .onStart {  }
            .collect{
            println(it)
        }

    }

    private suspend fun flowTest01(){
        flowOf(1,2,3,4,5)
            .onEach {
                delay(100)
            }
            .collect{
                println(it)
            }
    }

    /**
     * 最后的 channelFlow builder 跟 flow builder 是有一定差异的。
    flow 是 Cold Stream。在没有切换线程的情况下，生产者和消费者是同步非阻塞的。
    channel 是 Hot Stream。而 channelFlow 实现了生产者和消费者异步非阻塞模型。

     */
    private suspend fun flowTest02(){
        channelFlow {
            for (i in 1..5) {
                delay(100)
                send(i)
            }
        }.collect{
            println(it)
        }


    }

    /**
     * 切换线程
     * flowOn，下面的例子中，展示了 flow builder 和 map 操作符都会受到 flowOn 的影响。
     * 而 collect() 指定哪个线程，则需要看整个 flow 处于哪个 CoroutineScope 下。
     * flow和map是Dispatchers.IO的线程
     * collect是执行这个方法时CoroutineScope所指的线程,也就是执行flowTest03()这个函数在那个线程下面
     */
    private suspend fun flowTest03(){
        flow {
            Log.e("=====flowon11111","${Thread.currentThread().name}")
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }.map {
            Log.e("=====flowon2222","${Thread.currentThread().name}:")
            it * it
        }.flowOn(Dispatchers.IO)
            .collect {
                Log.e("=====flowon","${Thread.currentThread().name}: $it")
            }


    }
}