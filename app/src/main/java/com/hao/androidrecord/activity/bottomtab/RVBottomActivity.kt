package com.hao.androidrecord.activity.bottomtab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_rv_bottom.*

class RVBottomActivity:AppCompatActivity() , NavigationAdapter.OnNavigationListener{

    private var navigationAdapter: NavigationAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_bottom)

        navigationAdapter = NavigationAdapter(this).apply {
            addItem(NavigationAdapter.MenuItem(getString(R.string.home_nav_index),
                ContextCompat.getDrawable(this@RVBottomActivity, R.drawable.home_home_selector)))
            addItem(NavigationAdapter.MenuItem(getString(R.string.home_nav_found),
                ContextCompat.getDrawable(this@RVBottomActivity, R.drawable.home_found_selector)))
            addItem(NavigationAdapter.MenuItem(getString(R.string.home_nav_message),
                ContextCompat.getDrawable(this@RVBottomActivity, R.drawable.home_message_selector)))
            addItem(NavigationAdapter.MenuItem(getString(R.string.home_nav_me),
                ContextCompat.getDrawable(this@RVBottomActivity, R.drawable.home_me_selector)))
            setOnNavigationListener(this@RVBottomActivity)
            rv_home_navigation?.adapter = this
        }

        vp_home_pager



    }

    override fun onNavigationItemSelected(position: Int): Boolean {
        //vp_home_pager.currentItem = position
        //false 不选中
        //true 切换选中
        return true
    }
}