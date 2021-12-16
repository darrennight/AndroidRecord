package com.hao.androidrecord.activity.RichEdit.utils

import android.app.Activity
import android.content.Intent
import com.hao.androidrecord.activity.RichEdit.TopicListActivity

import com.hao.androidrecord.activity.RichEdit.UserListActivity

open class JumpUtil {

    companion object {
        fun goToUserList(activity: Activity, code: Int) {
            val intent = Intent(activity, UserListActivity::class.java)
            activity.startActivityForResult(intent, code)
        }


        fun goToTopicList(activity: Activity, code: Int) {
            val intent = Intent(activity, TopicListActivity::class.java)
            activity.startActivityForResult(intent, code)
        }
    }

}
