package com.hao.androidrecord.activity.bottomsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        imageViewArrow.setOnClickListener { _ -> bottomSheetLayout.toggle() }
        bottomSheetLayout.setOnProgressListener { progress -> rotateArrow(progress)}
    }

    private fun rotateArrow(progress: Float) {
        imageViewArrow.rotation = -180 * progress
    }
}
