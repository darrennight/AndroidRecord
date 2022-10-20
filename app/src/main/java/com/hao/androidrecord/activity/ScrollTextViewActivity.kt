package com.hao.androidrecord.activity

import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.chatview.chatlib.BitmapUtils
import com.hao.androidrecord.activity.chatview.chatlib.CenteredImageSpan
import kotlinx.android.synthetic.main.activity_scroll_text.*


class ScrollTextViewActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_text)

        initPanel1()
        initPanel2()
        initPanel3()

        btn_simple_scroll_start.setOnClickListener {
            tv_simple_scroll_panel_1.startScroll()
            tv_simple_scroll_panel_2.startScroll()
            tv_simple_scroll_panel_3.startScroll()
        }
    }


    private fun initPanel1() {
        val builder1 = SpannableStringBuilder()
        var text = "This is a long string about testing the simple scroll text view!"
        val highText1 = "simple scroll text view"
        val format1 = "<font color=\"#fef500\">%1\$s</font>"
        val replaceText1 = String.format(format1, highText1)
        text = text.replace(highText1, replaceText1)
        builder1.append(" ").append(Html.fromHtml(text))
        tv_simple_scroll_panel_1.setText(Html.fromHtml(text))
    }

    private fun initPanel2() {
        val builder1 = SpannableStringBuilder()
        builder1.append("0 ")
        val bitmap1 = BitmapUtils.drawTextToBitmap(
            baseContext,
            BitmapUtils.decodeResToBitmap(R.drawable.label),
            "骑士",
            "#FED746"
        )
        val label1 = CenteredImageSpan(baseContext, bitmap1)
        builder1.setSpan(label1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        var text = "Jack is Coming! Welcome Jack to be a new Fans!"
        val highText1 = "Jack"
        val format1 = "<font color=\"#fef500\">%1\$s</font>"
        val replaceText1 = String.format(format1, highText1)
        text = text.replace(highText1, replaceText1)
        builder1.append(" ").append(Html.fromHtml(text))
        tv_simple_scroll_panel_2.setText(builder1)
    }

    private fun initPanel3() {
        val builder1 = SpannableStringBuilder()
        builder1.append("0 1 2")
        val bitmap1 = BitmapUtils.drawTextToBitmap(
            baseContext,
            BitmapUtils.decodeResToBitmap(com.hao.androidrecord.R.drawable.label),
            "骑士",
            "#FED746"
        )
        val bitmap2 = BitmapUtils.drawTextToBitmap(
            baseContext,
            BitmapUtils.decodeResToBitmap(R.drawable.label2),
            "伯爵",
            "#FFF255"
        )
        val bitmap3 = BitmapUtils.drawTextToBitmap(
            baseContext,
            BitmapUtils.decodeResToBitmap(R.drawable.label3),
            "公爵",
            "#FFEC55"
        )
        val label1 = CenteredImageSpan(baseContext, bitmap1)
        val label2 = CenteredImageSpan(baseContext, bitmap2)
        val label3 = CenteredImageSpan(baseContext, bitmap3)
        builder1.setSpan(label1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder1.setSpan(label2, 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder1.setSpan(label3, 4, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        var text = " James is Coming！"
        val highText1 = "James"
        val format1 = "<font color=\"#fef500\">%1\$s</font>"
        val replaceText1 = String.format(format1, highText1)
        text = text.replace(highText1, replaceText1)
        builder1.append(" ").append(Html.fromHtml(text))
        tv_simple_scroll_panel_3.setText(builder1)
    }
}