package com.hao.androidrecord.activity.RichEdit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R

import com.shuyu.textutillib.model.UserModel
import kotlinx.android.synthetic.main.activity_user_list.*

import java.util.ArrayList


class UserListActivity : AppCompatActivity() {


    private val data = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        (0..49).mapTo(data) { UserModel("测试名字" + it, (it * 30).toString() + "") }

        val adapter = ArrayAdapter(this, R.layout.user_list_item, data)
        userList.adapter = adapter
        userList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent()
            intent.putExtra(DATA, data[position])
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED, intent)
        finish()
    }

    companion object {

        val DATA = "data"
    }


}
