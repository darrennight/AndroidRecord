package com.hao.androidrecord.activity.table

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.hao.androidrecord.R
import com.hao.androidrecord.adapter.IndexTabPageAdapter
import kotlinx.android.synthetic.main.activity_change_table_color.*

class ChangeTableColorActivity:AppCompatActivity() {
    private val fromColor:Int by lazy {
        ContextCompat.getColor(this,R.color.color_B3FFFFFF)
    }
    private val fromSelectColor:Int by lazy {
        ContextCompat.getColor(this,R.color.color_white)
    }
    private val toColor:Int by lazy {
        ContextCompat.getColor(this,R.color.color_404040)
    }
    private val toSelectColor:Int by lazy {
        ContextCompat.getColor(this,R.color.color_FF3300)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_table_color)

        val list = ArrayList<String>()
        list.add("111111")
        list.add("22222")
        list.add("33333")
        val adapter = IndexTabPageAdapter(supportFragmentManager,list)
        vp_index.adapter = adapter

        stl_top_tab.setViewPager(vp_index)
        initView()
    }
    private var indexpage = 0
    private var lastValue = 0f
    private var isSelected = false
    private val mEvaluator: ArgbEvaluator by lazy {
        ArgbEvaluator()
    }
    private fun initView(){
        vp_index.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                isSelected = false
                if (state == ViewPager.SCROLL_STATE_DRAGGING){
                    indexpage = vp_index.currentItem
                }else if (state == ViewPager.SCROLL_STATE_SETTLING){
                    indexpage = vp_index.currentItem
                }else{
                    lastValue = 0f
                }

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (isSelected){
                    return
                }
                if (positionOffset == 0f){
                    setTabStyle(indexpage)
                    return
                }

//                if(indexpage ==position){
                if(lastValue < positionOffset /*&& indexpage ==position*/){
                    //向左滑动
                    toLeft(position,positionOffset)

                } else if (lastValue > positionOffset) {
                    //向右滑动
                    toRigth(position,positionOffset)

                }
                lastValue = positionOffset

            }

            override fun onPageSelected(position: Int) {
                isSelected = true

                for (index in 0 until stl_top_tab.tabCount){
                    val textView = stl_top_tab.getTitleView(index)
                    if (index == position){
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,17.0f)
                        if (position > 0){
                            textView.setTextColor(toSelectColor)
                        }else{
                            textView.setTextColor(fromSelectColor)
                        }
                    }else{
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0f)
                        if (position == 0){
                            textView.setTextColor(fromColor)
                        }else{
                            textView.setTextColor(toColor)
                        }
                    }
                }
                if (position>0){
                    stl_top_tab.textSelectColor = toSelectColor
                    stl_top_tab.textUnselectColor = toColor
                    stl_top_tab.indicatorColor = toSelectColor

                }else{
                    stl_top_tab.textSelectColor = fromSelectColor
                    stl_top_tab.textUnselectColor = fromColor
                    stl_top_tab.indicatorColor = fromSelectColor
                }
                val textView = stl_top_tab.getTitleView(position)
                textView?.setTextSize(TypedValue.COMPLEX_UNIT_SP,17.0f)

            }
        })
    }


    private fun setTabStyle(indexpage:Int){

        for (index in 0 until stl_top_tab.tabCount){
            val textView = stl_top_tab.getTitleView(index)
            if (indexpage == 0){
                if (index == indexpage){
                    textView.setTextColor(fromSelectColor)
                }else{
                    textView.setTextColor(fromColor)
                }
            }else{
                if (index == indexpage){
                    textView.setTextColor(toSelectColor)
                }else{
                    textView.setTextColor(toColor)
                }
            }
        }
    }

    private fun toLeft(position: Int,positionOffset:Float){
        if (indexpage == 0){

            stl_top_tab.indicatorColor = mEvaluator.evaluate(positionOffset, fromSelectColor, toSelectColor) as Int
        }else{

            if (position>0){
                stl_top_tab.indicatorColor = toSelectColor
            }else{
                stl_top_tab.indicatorColor = mEvaluator.evaluate(positionOffset, fromSelectColor, toSelectColor) as Int
            }

        }

        for (index in 0 until stl_top_tab.tabCount){
            val textView = stl_top_tab.getTitleView(index)

            if (indexpage == 0){
                if (index == indexpage){
                    textView.setTextColor(mEvaluator.evaluate(positionOffset, fromSelectColor, toColor) as Int)
                }else{
                    textView.setTextColor(mEvaluator.evaluate(positionOffset, fromColor, toColor) as Int)
                }

            }else{
                if(indexpage == 1 && position == 0){

                    if (index == indexpage){
                        textView.setTextColor(mEvaluator.evaluate(positionOffset, fromColor, toSelectColor) as Int)
                    }else{
                        textView.setTextColor(mEvaluator.evaluate(positionOffset, fromColor, toColor) as Int)
                    }

                }else{
                    if (index == indexpage){
                        textView.setTextColor(toSelectColor)
                    }else{
                        textView.setTextColor(toColor)
                    }
                }

            }
        }
    }

    private fun toRigth(position:Int,positionOffset:Float){
        if (indexpage == 1 && position == 0){

            stl_top_tab.indicatorColor = mEvaluator.evaluate(1-positionOffset, toSelectColor, fromSelectColor) as Int
        }else{
            if (indexpage == 0 && position == 0){
                stl_top_tab.indicatorColor = mEvaluator.evaluate(1-positionOffset, toSelectColor, fromSelectColor) as Int
            }else{
                stl_top_tab.indicatorColor = toSelectColor
            }

        }
        for (index in 0 until stl_top_tab.tabCount){
            val textView = stl_top_tab.getTitleView(index)
            if (indexpage == 1 && position == 0){
                if (index == indexpage){
                    textView.setTextColor(mEvaluator.evaluate(1-positionOffset, toSelectColor, fromColor) as Int)
                }else{
                    textView.setTextColor(mEvaluator.evaluate(1-positionOffset, toColor, fromColor) as Int)
                }
            }else{
                if(indexpage == 0){

                    if (index == indexpage){
                        textView.setTextColor(mEvaluator.evaluate(1-positionOffset, toColor, fromSelectColor) as Int)
                    }else{
                        textView.setTextColor(mEvaluator.evaluate(1-positionOffset, toColor, fromColor) as Int)
                    }
                }else{
                    if (index == indexpage){
                        textView.setTextColor(toSelectColor)
                    }else{
                        textView.setTextColor(toColor)
                    }
                }

            }
        }
    }
}