package com.hao.androidrecord.activity.bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class BottomSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewArrow.setOnClickListener { _ -> bottomSheetLayout.toggle() }
        bottomSheetLayout.setOnProgressListener { progress -> rotateArrow(progress)}
    }

    private fun rotateArrow(progress: Float) {
        imageViewArrow.rotation = -180 * progress
    }
}
