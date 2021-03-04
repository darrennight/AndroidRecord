package com.hao.androidrecord.activity.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_test_hilt.*
import javax.inject.Inject

@AndroidEntryPoint
class TestHiltActivity:AppCompatActivity() {

    @Inject lateinit var user:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_hilt)

        user.name = "newhaha"
        tv_user_info.text = "${user.name}的mood是${user.mood}"
    }
}