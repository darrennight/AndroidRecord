package com.hao.androidrecord.custom

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import com.hao.androidrecord.R
import com.hao.androidrecord.util.ViewUtils

class SettingPermissonDialog (var context:Context,var tipTxt:String = ""){

  private var mDialog: Dialog?=null
  var sureClickListener:SureClickListener? = null
  init {
    init(context)
  }

  private fun init(context: Context){
    val view = LayoutInflater.from(context).inflate(R.layout.layout_setting_permission,null)
    val cancel = view.findViewById<TextView>(R.id.tv_cancel)
    val sure = view.findViewById<TextView>(R.id.tv_sure)
    val tips = view.findViewById<TextView>(R.id.tv_permission_tips)
    if (tipTxt.isNotEmpty()){
      tips.text = tipTxt
    }

    cancel?.let {
      it.setOnClickListener { dismissDialog()
        sureClickListener?.cancel()
      }
    }

    sure?.let {
      it.setOnClickListener {
        dismissDialog()
        sureClickListener?.sureClick()
      }
    }

    mDialog = Dialog(context,R.style.dialog_lock)
    mDialog?.let {
      it.setContentView(view)
      it.setCancelable(false)
      it.window?.setLayout(ViewUtils.dp2px(270f), ViewUtils.dp2px(127f))
      it.window?.setGravity(Gravity.CENTER)
    }

  }

  fun showDialog(){
    mDialog?.let {
      it.show()
    }
  }
  fun dismissDialog(){
    mDialog?.let {
      it.dismiss()
    }
  }

  interface SureClickListener{
    fun sureClick()
    fun cancel()
  }
}