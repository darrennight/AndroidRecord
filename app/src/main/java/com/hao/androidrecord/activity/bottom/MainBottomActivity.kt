package com.hao.androidrecord.activity.bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.layout_bottom_activity.*

class MainBottomActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bottom_activity)

        customBottomBar.inflateMenu(R.menu.bottom_menu)
    }
}