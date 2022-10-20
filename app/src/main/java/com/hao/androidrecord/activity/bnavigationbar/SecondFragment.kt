package com.hao.androidrecord.activity.bnavigationbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hao.androidrecord.R

import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author onestravel
 * @createTime 2019-08-04 11:52
 * @description TODO
 */
class SecondFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false).apply { init() }
    }
    fun init() {
        tvName?.let {it.text = "SecondFragment"}
    }
}
