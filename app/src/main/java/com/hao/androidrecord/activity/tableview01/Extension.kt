package com.hao.androidrecord.activity.tableview01

import android.content.res.Resources

val Int.dp get() = (Resources.getSystem().displayMetrics.density * this).toInt()