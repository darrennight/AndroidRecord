package com.hao.androidrecord.activity

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.TextClickSpans
import com.hao.androidrecord.custom.TextMovementMethods
import kotlinx.android.synthetic.main.activity_click_span.*

class ClickSpanActivity:AppCompatActivity() {

    private val textMovementMethods:TextMovementMethods by lazy{
        TextMovementMethods()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_span)

        tv_txt_click.text = makeReplyCommentSpan("atSomeone","didid","commentContent")
        tv_txt_click.movementMethod = textMovementMethods
    }

    fun makeReplyCommentSpan(atSomeone: String,id: String,commentContent: String): SpannableString {
        val richText = String.format("回复 %s : %s", atSomeone, commentContent)
        val builder = SpannableString(richText)
        if (!TextUtils.isEmpty(atSomeone)) {
            val childStart = 2
            val childEnd = childStart + atSomeone.length + 1
            builder.setSpan(object : TextClickSpans() {

                override fun onClick(widget: View) {
                    Toast.makeText(this@ClickSpanActivity, "$atSomeone id: $id", Toast.LENGTH_LONG).show()
                }
            }, childStart, childEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return builder
    }
}