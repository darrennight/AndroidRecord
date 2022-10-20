package com.hao.androidrecord.activity.stickscroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amar.library.ui.StickyScrollView
import com.hao.androidrecord.R

class PageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.activity_main_stick_scroll, container, false)
        view.findViewById<StickyScrollView>(R.id.scrollView).setFooterView(R.id.buttons)
        return view
    }
}
