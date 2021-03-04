package com.hao.androidrecord.activity.tiktokComments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.tiktokComments.sheet.VideoCommentDialog
import kotlinx.android.synthetic.main.activity_comment_multi.*

/**
 * https://github.com/ArcGhh/CommentDialog
 * https://github.com/TracyEminem/TikTokComment
 * https://github.com/wuqingsen/DouYinWu
 */
class CommentMultiActivity:AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_multi)


        btn_show_comments.setOnClickListener {
            val dialog = VideoCommentDialog.getInstance()
            dialog.show(supportFragmentManager, "")
        }
    }



}