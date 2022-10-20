package com.hao.androidrecord.activity.expandablecardview

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_expand_card.*


//https://github.com/AleSpero/ExpandableCardView
class ExpandCardMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_card)





        main_profile_card.setOnExpandedListener { _, isExpanded ->
            if(isExpanded) Toast.makeText(applicationContext, "Expanded!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext, "Collapsed!", Toast.LENGTH_SHORT).show()
        }
    }




}
