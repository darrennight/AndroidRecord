package com.hao.androidrecord.activity.tiktokComments.sheet

import android.os.Bundle
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hao.androidrecord.R

open class BaseBottomSheetDialog :BottomSheetDialogFragment() {

    private var bottomSheet:FrameLayout? = null
    private var behavior: BottomSheetBehavior<FrameLayout>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog)
        isCancelable = true
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog?
        bottomSheet = dialog?.delegate
            ?.findViewById(R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            val layoutParams =
                bottomSheet?.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.height = getHeight()
            bottomSheet?.layoutParams = layoutParams
            bottomSheet?.let {
                behavior = BottomSheetBehavior.from(it)
            }

            behavior?.peekHeight = getHeight()
            // 初始为展开状态
            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

     open fun getHeight(): Int {
        return resources.displayMetrics.heightPixels
    }

}