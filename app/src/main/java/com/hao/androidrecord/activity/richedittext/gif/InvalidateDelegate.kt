package com.hao.androidrecord.activity.richedittext.gif

import android.graphics.drawable.Drawable

/**
 * Created by sunhapper on 2019-07-19 .
 */
class InvalidateDelegate : InvalidateDrawable, Drawable.Callback {
    override var mRefreshListeners: MutableCollection<RefreshListener> = mutableListOf()

    override fun addRefreshListener(callback: RefreshListener) {
        mRefreshListeners.add(callback)
    }

    override fun removeRefreshListener(callback: RefreshListener) {
        mRefreshListeners.remove(callback)
    }

    override fun refresh() {
        mRefreshListeners = mRefreshListeners.filter {
            it.onRefresh()
        }.toMutableList()
    }


    override fun unscheduleDrawable(who: Drawable, what: Runnable) {

    }

    override fun invalidateDrawable(who: Drawable) {
        refresh()
    }

    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {

    }

}
