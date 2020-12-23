package com.hao.androidrecord.util.toast

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes

class ToastCompat(context:Context,var toast:Toast) : Toast(context) {

    fun setBadTokenListener(listener: BadTokenListener): ToastCompat {
        val context = view.context
        if (context is SafeToastContext) {
            context.badTokenListener = listener
        }
        return this
    }

    override fun show() {
        toast.show()
    }

    fun showToast(content: String?) {
        toast.setText(content)
        toast.show()
    }

    override fun setDuration(duration: Int) {
        toast.duration = duration
    }


    override fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
        toast.setGravity(gravity, xOffset, yOffset)
    }


    override fun setMargin(horizontalMargin: Float, verticalMargin: Float) {
        toast.setMargin(horizontalMargin, verticalMargin)
    }


    override fun setText(resId: Int) {
        toast.setText(resId)
    }


    override fun setText(s: CharSequence?) {
        toast.setText(s)
    }


    override fun setView(view: View) {
        toast.view = view
        setContextCompat(view, SafeToastContext(view.context, this))
    }


    override fun getHorizontalMargin(): Float {
        return toast.horizontalMargin
    }


    override fun getVerticalMargin(): Float {
        return toast.verticalMargin
    }


    override fun getDuration(): Int {
        return toast.duration
    }


    override fun getGravity(): Int {
        return toast.gravity
    }


    override fun getXOffset(): Int {
        return toast.xOffset
    }


    override fun getYOffset(): Int {
        return toast.yOffset
    }


    override fun getView(): View {
        return toast.view
    }


    fun getBaseToast(): Toast {
        return toast
    }


    companion object{

        private fun setContextCompat(view: View, context: Context){
            try {
                val field =
                    View::class.java.getDeclaredField("mContext")
                field.isAccessible = true
                field[view] = context
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

        fun makeText(context: Context,text:CharSequence,duration:Int): ToastCompat {
            val toast = Toast.makeText(context,text,duration)
            setContextCompat(toast.view, SafeToastContext(context,toast))
            return ToastCompat(context,toast)
        }

        fun makeText(context: Context,@StringRes resId:Int,duration:Int):Toast{
            return makeText(context, context.resources.getText(resId), duration)
        }
    }
}