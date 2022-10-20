package com.hao.androidrecord.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.hao.androidrecord.R
import com.hao.androidrecord.adapter.ScreenSlidePagerAdapter
import com.varunest.sparkbutton.SparkButton


class SparkButtonActivity : AppCompatActivity() {
    private var showcaseViewpager: ViewPager? = null
    private var pagerAdapter: PagerAdapter? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        initWidgets()
        initAnimation()
    }

    private fun initAnimation() {
        showcaseViewpager!!.postDelayed({
            val starLayout = getViewFromPosition(0)
            starLayout?.let { playStarAnimation(it) }
        }, 500)
    }

    private fun initWidgets() {
        showcaseViewpager = findViewById(R.id.showcase_viewpager) as ViewPager
        pagerAdapter = ScreenSlidePagerAdapter(this@SparkButtonActivity)
        showcaseViewpager?.adapter = pagerAdapter
        showcaseViewpager?.addOnPageChangeListener(onPageChangeListener)
        tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout?.setupWithViewPager(showcaseViewpager)
    }

    @get:NonNull
    private val onPageChangeListener: OnPageChangeListener
        private get() = object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val view = getViewFromPosition(position)
                if (view != null) {
                    when (position) {
                        0 -> playStarAnimation(view)
                        1 -> playHeartAnimation(view)
                        2 -> playFacebookAnimation(view)
                        3 -> playTwitterAnimation(view)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        }

    private fun getViewFromPosition(position: Int): View? {
        var view: View? = null
        for (i in 0 until pagerAdapter!!.count) {
            view = showcaseViewpager!!.findViewWithTag(position.toString())
            if (view != null) {
                break
            }
        }
        return view
    }

    private fun playHeartAnimation(heartLayout: View) {
        (heartLayout.findViewById<View>(R.id.heart_button) as SparkButton).isChecked =
            false
        Handler().postDelayed({
            (heartLayout.findViewById<View>(R.id.heart_button) as SparkButton).isChecked =
                true
            (heartLayout.findViewById<View>(R.id.heart_button) as SparkButton).playAnimation()
        }, 300)
    }

    private fun playStarAnimation(starLayout: View) {
        (starLayout.findViewById<View>(R.id.star_button1) as SparkButton).isChecked =
            false
        (starLayout.findViewById<View>(R.id.star_button2) as SparkButton).isChecked =
            true
        (starLayout.findViewById<View>(R.id.star_button2) as SparkButton).playAnimation()
    }

    private fun playFacebookAnimation(facebookLayout: View) {
        (facebookLayout.findViewById<View>(R.id.facebook_button) as SparkButton).playAnimation()
    }

    private fun playTwitterAnimation(twitterLayout: View) {
        (twitterLayout.findViewById<View>(R.id.twitter_button) as SparkButton).playAnimation()
    }
}