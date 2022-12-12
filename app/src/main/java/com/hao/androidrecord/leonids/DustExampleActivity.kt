package com.hao.androidrecord.leonids

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hao.androidrecord.R
import com.plattysoft.leonids.ParticleSystem
import com.plattysoft.leonids.modifiers.AlphaModifier
import com.plattysoft.leonids.modifiers.ScaleModifier
import kotlinx.android.synthetic.main.activity_dust_example.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class DustExampleActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dust_example)
        findViewById<View>(R.id.button1).setOnClickListener(this)

        ps =   ParticleSystem(this, 10, R.drawable.dust, 3000)
        .setSpeedByComponentsRange(-0.01f, 0.01f, -0.06f, -0.1f)
            .setAcceleration(0.00001f, 30)
            .setInitialRotationRange(0, 360)
            .addModifier(AlphaModifier(255, 0, 1000, 3000))
            .addModifier(
                ScaleModifier(
                    0.2f,
                    0.5f,
                    0,
                    1000
                )
            )
//        ps.cancel()
    }

    override fun onClick(arg0: View) {

        lifecycleScope.launch {
            for (i in 0..1000){
                delay(50)
//                ps.oneShot(emiter_bottom, 10);

                ParticleSystem(this@DustExampleActivity, 10, R.drawable.dust, 2000)
                    .setSpeedByComponentsRange(-0.01f, 0.01f, -0.06f, -0.1f)
                    .setAcceleration(0.00001f, 30)
                    .setInitialRotationRange(0, 360)
                    .addModifier(AlphaModifier(5, 0, 1000, 2000))
                    .addModifier(
                        ScaleModifier(
                            0.2f,
                            0.5f,
                            0,
                            1000
                        )
                    ).oneShot(emiter_bottom, 10);
            }
        }

//        countDownCoroutines(3,{
//
//                                Log.e("=====shot","sssssssss")
//        },{ },lifecycleScope)



    }


   lateinit var ps:ParticleSystem
       //		.oneShot(findViewById(R.id.emiter_bottom), 10);

    @OptIn(DelicateCoroutinesApi::class)
    private fun countDownCoroutines(total:Int, onTick:(Int)->Unit, onFinish:()->Unit,
                                    scope: CoroutineScope = GlobalScope
    ): Job {
        return flow{
            for (i in total downTo 0){
                emit(i)
                delay(1000)
            }
        }.flowOn(Dispatchers.IO)
            .onCompletion { onFinish.invoke() }
            .onEach { onTick.invoke(it) }
            .launchIn(scope)
    }
}

