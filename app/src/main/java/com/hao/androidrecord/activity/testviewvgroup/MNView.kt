package com.hao.androidrecord.activity.testviewvgroup

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import com.hao.androidrecord.R


class MNView:View {
    private var text = ""
    private val paint:Paint by lazy {
        val paint = Paint()
        paint.textSize = sp2px(16)
        paint
    }
    private val mTextBounds: Rect by lazy {
        Rect()
    }
    private var meaWidth = 0
    private var meaHeight = 0
    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){

        val ta = context?.obtainStyledAttributes(attrs, R.styleable.MNView)
        text = ta?.getString(R.styleable.MNView_mn_text)?:""
        //资源释放回收到pool资源池里面
        ta?.recycle()

        paint.getTextBounds(text,0,text.length,mTextBounds)


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        var specMode = MeasureSpec.getMode(widthMeasureSpec)

        if (specMode == MeasureSpec.EXACTLY){
            meaWidth = specWidth
        }else if (specMode == MeasureSpec.AT_MOST){
            //bounds 获取文字宽度高度
            meaWidth = mTextBounds.width()+paddingLeft+paddingRight
        }

        val specHeight = MeasureSpec.getSize(heightMeasureSpec)
        specMode = MeasureSpec.getMode(heightMeasureSpec)
        if (specMode == MeasureSpec.EXACTLY){
            meaHeight = specHeight
        }else if (specMode == MeasureSpec.AT_MOST){
            //bounds 获取文字宽度高度
            meaHeight = mTextBounds.height()+paddingTop+paddingBottom
        }

        setMeasuredDimension(meaWidth,meaHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //x y 坐标 绘制起始位置
        canvas?.drawText(text,0f+paddingLeft, mTextBounds.height().toFloat()+paddingTop,paint)

    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("=======v","MNViewonTouchEvent")
        return super.onTouchEvent(event)
    }

    private fun sp2px(sp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            resources.displayMetrics
        )
    }
}