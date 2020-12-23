package com.hao.androidrecord.util.toast

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast

object ToastUtil {

    private fun getToast(
        context: Context,
        content: String,
        time: Int
    ): ToastCompat? {
        var context = context
        context = context.applicationContext
        //        TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.toast_clarity, null);
//        view.setText(content);
//        toast.setView(view);
        return ToastCompat.makeText(context, content, time)
    }

    fun showLongToast(context: Context?, id: Int, position: Int) {
        if (context == null) {
            return
        }
        val str = context.getString(id) + position
        showToast(context, str, Toast.LENGTH_LONG)
    }

    fun showLongToast(context: Context?, id: Int) {
        if (context == null) {
            return
        }
        val str = context.getString(id)
        showToast(context, str, Toast.LENGTH_LONG)
    }

    fun showLongToast(context: Context?, content: String) {
        if (context == null) {
            return
        }
        showToast(context, content, Toast.LENGTH_LONG)
    }

    fun showShortToast(context: Context?, id: Int) {
        if (context == null) {
            return
        }
        val str = context.getString(id)
        showToast(context, str, Toast.LENGTH_SHORT)
    }

    fun showShortToast(context: Context?, id: Int, g: Int) {
        if (context == null) {
            return
        }
        val str = context.getString(id)
        showToast(context, str, Toast.LENGTH_SHORT, g)
    }

    fun showShortToast(
        context: Context?,
        id: Int,
        gravity: Gravity?
    ) {
        if (context == null) {
            return
        }
        val str = context.getString(id)
        showToast(context, str, Toast.LENGTH_SHORT)
    }

    fun showShortToast(
        context: Context?,
        content: String
    ) {
        if (context == null || TextUtils.isEmpty(content)) {
            return
        }
        showToast(context, content, Toast.LENGTH_SHORT)
    }

    fun showShortToast(
        context: Context?,
        content: String,
        gravity: Int
    ) {
        showToast(context, content, Toast.LENGTH_SHORT, gravity)
    }

    private fun showToast(
        context: Context,
        content: String,
        duration: Int
    ) {
        showToast(context, content, duration, Gravity.NO_GRAVITY)
    }

    private var mToast: ToastCompat? = null
    private fun showToast(
        context: Context?,
        content: String,
        duration: Int,
        gravity: Int
    ) {
        if (context == null) {
            return
        }
        if (mToast == null) {
            mToast = getToast(context, content, duration)
            mToast!!.setGravity(gravity, 0, 0)
        }


//        Toast t = getToast(context, content, duration);
//        t.setGravity(gravity, 0, 0);
        try {
//            t.show();
//            mToast.setText(content);
//            mToast.show();
            mToast?.showToast(content)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}