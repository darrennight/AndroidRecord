package com.hao.androidrecord.activity

import android.os.Bundle
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.KeyboardChangeListener
import com.hao.androidrecord.custom.VideoDesDialog
import kotlinx.android.synthetic.main.activity_keyboard_pop.*

class KeyBoardPopDialog :AppCompatActivity(), KeyboardChangeListener.KeyboardListener{

    private val mKeyboardChangeListener: KeyboardChangeListener by lazy {
        KeyboardChangeListener.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard_pop)
        edit_video_des.setOnClickListener {
            initInputTextMsgDialog(edit_video_des.text.toString().trim())
        }
        mKeyboardChangeListener.setKeyboardListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mKeyboardChangeListener.destroy()
    }
    override fun onKeyboardChange(isShow: Boolean, keyboardHeight: Int) {
        if (!isShow){
            inputTextMsgDialog?.dismiss()
        }
    }


    private var inputTextMsgDialog: VideoDesDialog? = null
    private fun initInputTextMsgDialog(msg:String){
        dismissInputDialog()

        if (inputTextMsgDialog == null) {
            inputTextMsgDialog = VideoDesDialog(this, R.style.dialog_pop)
            inputTextMsgDialog?.setmOnTextSendListener(object : VideoDesDialog.OnTextSendListener {
                override fun onTextSend(msg: String?) {

                }

                override fun dismiss(msg: String?) {
                    msg?.let {
                        edit_video_des.setText(it)
                    }
                }
            })
        }
        inputTextMsgDialog?.setMsgText(msg)
        inputTextMsgDialog?.show()

    }
    private fun dismissInputDialog() {
        inputTextMsgDialog?.let {
            if (it.isShowing){
                it.dismiss()
            }
            it.cancel()
            inputTextMsgDialog = null
        }
    }
}