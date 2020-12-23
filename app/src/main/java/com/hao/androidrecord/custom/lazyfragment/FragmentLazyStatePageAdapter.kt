package com.hao.androidrecord.custom.lazyfragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Description:支持懒加载的FragmentLazyStatePageAdapter,只有主Fragment才会调用resume方法,需要配合[com.jennifer.andy.androidxlazyload.LazyFragment]使用
 */

abstract class FragmentLazyStatePageAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)