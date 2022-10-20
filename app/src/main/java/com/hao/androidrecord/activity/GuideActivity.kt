package com.hao.androidrecord.activity

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.pickerads.GuideAdapter
import kotlinx.android.synthetic.main.guide_activity.*

class GuideActivity :AppCompatActivity(){

    private val adapter: GuideAdapter = GuideAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guide_activity)
        adapter.addItem(R.drawable.guide_1_bg)
        adapter.addItem(R.drawable.guide_2_bg)
        adapter.addItem(R.drawable.guide_3_bg)

        vp_guide_pager.adapter = adapter
        vp_guide_pager.registerOnPageChangeCallback(mCallback)
        cv_guide_indicator.setViewPager(vp_guide_pager)







    }

    override fun onDestroy() {
        super.onDestroy()
        vp_guide_pager?.unregisterOnPageChangeCallback(mCallback)
    }

    private val mCallback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (vp_guide_pager?.currentItem != adapter.getCount() - 1 || positionOffsetPixels <= 0) {
                return
            }
            cv_guide_indicator?.visibility = View.VISIBLE
            btn_guide_complete?.visibility = View.INVISIBLE
            btn_guide_complete?.clearAnimation()
        }

        override fun onPageScrollStateChanged(state: Int) {
            if (state != ViewPager2.SCROLL_STATE_IDLE) {
                return
            }
            val lastItem: Boolean = vp_guide_pager?.currentItem == adapter.getCount() - 1
            cv_guide_indicator?.visibility = if (lastItem) View.INVISIBLE else View.VISIBLE
            btn_guide_complete?.visibility = if (lastItem) View.VISIBLE else View.INVISIBLE
            if (lastItem) {
                // 按钮呼吸动效
                val animation = ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
                )
                animation.duration = 350
                animation.repeatMode = Animation.REVERSE
                animation.repeatCount = Animation.INFINITE
                btn_guide_complete?.startAnimation(animation)
            }
        }
    }
}