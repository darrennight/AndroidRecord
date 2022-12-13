package com.hao.androidrecord.activity.candle

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class CandleView:View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint: Paint by lazy {
        val p = Paint()
        p.color = Color.YELLOW
        p.style = Paint.Style.FILL
        p.isAntiAlias = true
        p.strokeCap = Paint.Cap.ROUND;
        p
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = Path()
//        path.moveTo(150f, 300f)
//        path.quadTo(150f, 200f, 180f, 300f)
//
//        path.moveTo(150f, 300f)
//        path.quadTo(150f, 400f, 180f, 300f)


        //椭圆
//        val rect = RectF(100f, 100f, 200f, 300f)
//        path.addOval(rect, Path.Direction.CW)




        canvas?.drawPath(path, paint)
    }
}