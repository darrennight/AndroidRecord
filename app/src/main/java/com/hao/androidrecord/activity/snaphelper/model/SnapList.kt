package com.hao.androidrecord.activity.snaphelper.model

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.hao.androidrecord.R

data class SnapList(
    val gravity: Int,
    val title: String,
    val apps: List<App>,
    val layoutId: Int = R.layout.adapter_snap,
    val snapToPadding: Boolean = false,
    val showScrollButtons: Boolean = true,
    val maxFlingSizeFraction: Float = GravitySnapHelper.FLING_SIZE_FRACTION_DISABLE,
    val scrollMsPerInch: Float = 100f,
    val addStartDecoration: Boolean = false,
    val addEndDecoration: Boolean = false
)
