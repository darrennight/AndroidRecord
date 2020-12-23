package com.hao.androidrecord.application

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class MyApplication:Application() {


    companion object {
        var instance: MyApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this

    }
}