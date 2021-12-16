package com.hao.androidrecord.activity.bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.bottom_02.*

class MainBottomActivity02 :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_02)

        bottom_appbar.replaceMenu(R.menu.bottom_menu_02)
        toggle_alignment.setOnClickListener {
            bottom_appbar.toggleAlignment()
        }
    }


    fun BottomAppBar.toggleAlignment() {
        val current = fabAlignmentMode
        fabAlignmentMode = current.xor(1)
    }
}