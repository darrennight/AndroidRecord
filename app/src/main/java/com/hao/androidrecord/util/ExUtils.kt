package com.hao.androidrecord.util

import android.widget.ImageView
import coil.load
import coil.request.Disposable
import com.hao.androidrecord.R

fun ImageView.loadImage(url:String?): Disposable {
    return this.load(url){
        //加载时 渐显
        crossfade(true)
        crossfade(500)
        //
        placeholder(R.drawable.e19)
        error(R.drawable.e)
    }
}