package com.hao.androidrecord.util.toast

import android.content.Context
import android.content.ContextWrapper
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.BadTokenException
import android.widget.Toast
import com.hao.androidrecord.util.toast.BadTokenListener

class SafeToastContext :ContextWrapper{

    var toast:Toast
    var badTokenListener: BadTokenListener?=null

    constructor(base: Context?,toast: Toast) : super(base){
        this.toast = toast
    }

    override fun getApplicationContext():Context{
        return ApplicationContextWrapper(baseContext.applicationContext)
    }


    inner class ApplicationContextWrapper(base: Context?) : ContextWrapper(base){
        override fun getSystemService(name: String): Any? {
            if (Context.WINDOW_SERVICE == name){
                return WindowManagerWrapper(baseContext.getSystemService(name) as WindowManager)
            }
            return super.getSystemService(name)
        }
    }


    private inner class WindowManagerWrapper(var base:WindowManager) : WindowManager{

        override fun getDefaultDisplay(): Display {
            return base.defaultDisplay
        }

        override fun addView(view: View?, params: ViewGroup.LayoutParams?) {
            try {

                base.addView(view, params)
            } catch (e: BadTokenException) {
                if (badTokenListener != null) {
                    badTokenListener?.onBadTokenCaught(toast)
                }
            } catch (throwable: Throwable) {
            }
        }

        override fun updateViewLayout(view: View?, params: ViewGroup.LayoutParams?) {
            base.updateViewLayout(view, params)
        }

        override fun removeView(view: View?) {
            base.removeView(view)
        }

        override fun removeViewImmediate(view: View?) {
            base.removeViewImmediate(view)
        }
    }
}