package com.hao.androidrecord.activity.bnavigationbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_main_bottom.*


class MainBottomActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_bottom)
        oneBottomLayout.setMenu(R.menu.navigation_menu)
        oneBottomLayout.setFragmentManager(supportFragmentManager, mainFragment )
        oneBottomLayout.isReplace = false
        oneBottomLayout.addFragment(R.id.tab1, FirstFragment())
        oneBottomLayout.addFragment(R.id.tab4, FourFragment())
        oneBottomLayout.addFragment(R.id.tab5, FiveFragment())
        oneBottomLayout.setFloatingEnable(true)
        oneBottomLayout.setTopLineColor(Color.RED)
        oneBottomLayout.setItemColorStateList(R.drawable.item_check)
        oneBottomLayout.setMsgCount(0, 32)
        oneBottomLayout.setMsgCount(2, 1)
        oneBottomLayout.setMsgCount(1, -1)
        oneBottomLayout.setOnItemSelectedListener { item, position ->
//            if (position == 1) {
//                oneBottomLayout.setFloatingEnable(true)
//            } else {
//                oneBottomLayout.setFloatingEnable(false)
//            }
            false
        }

    }


}
