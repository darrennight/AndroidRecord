package com.hao.androidrecord.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hao.androidrecord.activity.table.EmptyFragment
import com.hao.androidrecord.custom.lazyfragment.FragmentLazyStatePageAdapter

class IndexTabPageAdapter(fm: FragmentManager, val data:ArrayList<String>):
    FragmentLazyStatePageAdapter(fm) {

    override fun getItem(position: Int): Fragment {
       return EmptyFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position]
    }

    override fun getCount(): Int {
        return data.size
    }
}