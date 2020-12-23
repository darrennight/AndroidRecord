package com.hao.androidrecord.util.toast

import android.widget.Toast

interface BadTokenListener {
    fun onBadTokenCaught(toast: Toast)
}