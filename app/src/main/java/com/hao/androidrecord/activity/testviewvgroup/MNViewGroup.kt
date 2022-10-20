package com.hao.androidrecord.activity.testviewvgroup

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup

class MNViewGroup:ViewGroup {

    private var measWidth = 0
    private var measHeight = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (childCount == 0){
            measWidth = measureWidthAndHeight(widthMeasureSpec)
            measHeight = measureWidthAndHeight(heightMeasureSpec)
            setMeasuredDimension(measWidth,measHeight)
        }else{

            var childViewWidth = 0
            var childViewHeight = 0
            var childViewMarginTop = 0
            var childViewMarginBottom = 0


            for (i in 0 until childCount){
                val childView = getChildAt(i)
                //测量子view
//                measureChildren(widthMeasureSpec, heightMeasureSpec);方式一
                measureChild(childView,widthMeasureSpec,heightMeasureSpec) //方式二

                val lp = childView.layoutParams as MarginLayoutParams
                childViewWidth = Math.max(childViewWidth,childView.measuredWidth+lp.leftMargin+lp.rightMargin)
                childViewHeight += childView.measuredHeight

                childViewMarginTop += lp.topMargin
                childViewMarginBottom += lp.bottomMargin


            }

            measWidth = childViewWidth + paddingLeft + paddingRight
            measHeight = childViewHeight + childViewMarginTop + childViewMarginBottom + paddingTop + paddingBottom

            setMeasuredDimension(measureWidthAndHeight(widthMeasureSpec,measWidth),measureWidthAndHeight(heightMeasureSpec,measHeight))
        }

    }



    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var cLeft = 0
        var cTop = 0

        var cRight = 0
        var cBottom = 0
        var countTop = 0
        for (i in 0 until childCount){
            val childView = getChildAt(i)
            val params = childView.layoutParams as MarginLayoutParams

            cLeft = paddingLeft + params.leftMargin
            cTop = countTop + paddingTop + params.topMargin

            cRight = cLeft + childView.measuredWidth

            cBottom = cTop + childView.measuredHeight
            childView.layout(cLeft,cTop,cRight,cBottom)

            countTop += childView.measuredHeight + params.topMargin + params.bottomMargin

        }

    }

    private fun measureWidthAndHeight(measureSpec: Int,size:Int):Int{
        var result = 0
        var specMode = MeasureSpec.getMode(measureSpec)
        var specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize
        }else if (specMode == MeasureSpec.AT_MOST){
            result = size
        }
        return result
    }


    private fun measureWidthAndHeight(measureSpec: Int):Int{
        var result = 0
        var specMode = MeasureSpec.getMode(measureSpec)
        var specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize
        }else if (specMode == MeasureSpec.AT_MOST){
            result = 0
        }
        return result
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context,attrs)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Log.e("=======v","GrouponTouchEvent")
        return super.onTouchEvent(event)
    }
}