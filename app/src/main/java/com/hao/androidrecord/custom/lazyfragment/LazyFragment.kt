package com.hao.androidrecord.custom.lazyfragment

import androidx.fragment.app.Fragment


abstract class LazyFragment :Fragment() {

    protected var isLoaded = false

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()
}