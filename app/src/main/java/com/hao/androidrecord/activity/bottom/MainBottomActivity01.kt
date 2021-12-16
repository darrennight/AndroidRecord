package com.hao.androidrecord.activity.bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.layout_bottom_activity01.*

class MainBottomActivity01 :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bottom_activity01)

        customBottomBar.inflateMenu(R.menu.bottom_menu_02)
    }
}