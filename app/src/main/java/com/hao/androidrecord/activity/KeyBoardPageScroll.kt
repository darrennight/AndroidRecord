package com.hao.androidrecord.activity

import android.os.Bundle
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.KeyboardChangeListener
import kotlinx.android.synthetic.main.activity_keyboard.*

class KeyBoardPageScroll:AppCompatActivity() ,KeyboardChangeListener.KeyboardListener{


    private val mKeyboardChangeListener: KeyboardChangeListener by lazy {
        KeyboardChangeListener.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard)
        mKeyboardChangeListener.setKeyboardListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mKeyboardChangeListener.destroy()
    }
    override fun onKeyboardChange(isShow: Boolean, keyboardHeight: Int) {
        if (isShow){
            sv_root.scrollTo(0,keyboardHeight/2)
            sv_root.fullScroll(ScrollView.FOCUS_DOWN)
            edit_video_des.requestFocus()
        }else{
            sv_root.scrollTo(0,0)
        }
    }
}