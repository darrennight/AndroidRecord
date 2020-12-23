package com.hao.androidrecord.custom

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.hao.androidrecord.R


/**
 * Created by zenghao on 2017/9/8.
 */
class RoundBackGround : RelativeLayout{

    /**文字*/
    var text:String? = null
    var textColor:Int = 0
    var textSize:Int = 0

    /**纯色正常态颜色*/
    var pureNormalColor:Int = 0
    /**纯色点击态颜色*/
    var purePressColor:Int = 0

    /**渐变正常态*/
    var gradientNormalStartColor:Int = 0
    var gradientNormalEndColor:Int = 0

    /**渐变色点击态*/
    var gradientPressStartColor:Int = 0
    var gradientPressEndColor:Int = 0


    /**边框颜色*/
    var strokeColor:Int = 0
    /**边框宽度*/
    var strokeSize:Int = 0

    /**边框渐变色*/
    var strokeGradientStartColor: Int = 0
    var strokeGradientEndColor: Int = 0


    /**按钮圆角角度 */
    var cornerRadius: Int = 0

    /**是否按钮颜色渐变 */
    var hasGradient: Boolean = false

    /**边框是否渐变 */
    var hasStrokeGradient: Boolean = false
    /**是否需要边框 */
    var hasStroke: Boolean = false
    /**渐变方向 */
    var GraditenOrientaton: Int = 0

    var mLeftPadding: Int = 0
    var mRightPadding: Int = 0

    val textView:TextView by lazy {
        TextView(context)
    }

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initTypedArray(attrs)
    }

    private fun initTypedArray(attrs: AttributeSet?){
        val type = context.theme.obtainStyledAttributes(attrs, R.styleable.BNBRoundButton, 0, 0)

        text = type.getString(R.styleable.BNBRoundButton_bnb_bt_text)
        textColor = type.getColor(R.styleable.BNBRoundButton_bnb_bt_text_color, ContextCompat.getColor(context, android.R.color.white))
        textSize = type.getDimensionPixelSize(R.styleable.BNBRoundButton_bnb_bt_text_size,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics).toInt())


        pureNormalColor = type.getColor(R.styleable.BNBRoundButton_pure_normal_color
                ,ContextCompat.getColor(context, android.R.color.white))

        purePressColor = type.getColor(R.styleable.BNBRoundButton_pure_press_color
                ,ContextCompat.getColor(context,  android.R.color.white))

        gradientNormalStartColor = type.getColor(R.styleable.BNBRoundButton_gradient_normal_start_color
                ,ContextCompat.getColor(context,  android.R.color.white))
        gradientNormalEndColor = type.getColor(R.styleable.BNBRoundButton_gradient_normal_end_color
                ,ContextCompat.getColor(context,  android.R.color.white))

        gradientPressStartColor = type.getColor(R.styleable.BNBRoundButton_gradient_press_start_color
                ,ContextCompat.getColor(context,  android.R.color.white))
        gradientPressEndColor = type.getColor(R.styleable.BNBRoundButton_gradient_press_end_color
                ,ContextCompat.getColor(context,  android.R.color.white))


        strokeColor = type.getColor(R.styleable.BNBRoundButton_bnb_stroke_color
                ,ContextCompat.getColor(context,  android.R.color.white))
        strokeSize = type.getDimensionPixelSize(R.styleable.BNBRoundButton_bnb_stroke_size,0)

        strokeGradientStartColor = type.getColor(R.styleable.BNBRoundButton_stroke_gradient_start_color
                ,ContextCompat.getColor(context,  android.R.color.white))
        strokeGradientEndColor = type.getColor(R.styleable.BNBRoundButton_stroke_gradient_end_color
                ,ContextCompat.getColor(context,  android.R.color.white))

        cornerRadius = type.getDimensionPixelSize(R.styleable.BNBRoundButton_bnb_corner_radius,0)

        hasGradient = type.getBoolean(R.styleable.BNBRoundButton_bnb_has_gradient,false)
        hasStrokeGradient = type.getBoolean(R.styleable.BNBRoundButton_bnb_has_stroke_gradient,false)
        hasStroke = type.getBoolean(R.styleable.BNBRoundButton_bnb_has_stroke,false)

        GraditenOrientaton = type.getInteger(R.styleable.BNBRoundButton_bnb_gradient_orientation, 0)
        mLeftPadding = type.getDimensionPixelSize(R.styleable.BNBRoundButton_bnb_text_padding_left,0)
        mRightPadding = type.getDimensionPixelSize(R.styleable.BNBRoundButton_bnb_text_padding_right,0)
        type.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val specSize = MeasureSpec.getSize(widthMeasureSpec)

        if(specMode == MeasureSpec.AT_MOST){
            val view = getChildAt(0)
            view.setPadding(mLeftPadding,0,mRightPadding,0)

        }else if (specMode == MeasureSpec.EXACTLY){
            val view = getChildAt(0)
            val params = view.layoutParams
            params.width = specSize
            view.layoutParams = params

        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initTextView()
    }

     fun initTextView(){

//        textView = TextView(context)
        val textParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        textView.layoutParams = textParams
        textView.gravity = Gravity.CENTER

        textView.text = text
        textView.setTextColor(textColor)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())

        if (hasGradient) {
            GradientStyle()
        } else {
            PureStyle()
        }

        if (hasStrokeGradient) {
            GradientStrok()
            PureContent(textView)
            setPadding(strokeSize, strokeSize, strokeSize, strokeSize)
        }
        removeAllViews()
        addView(textView)

        isClickable = true
    }




    /***
     * 按钮纯颜色+纯色边框背景样式
     */
    private fun PureStyle(){
        val normal = GradientDrawable()
        val press = GradientDrawable()

        normal.setColor(pureNormalColor)
        normal.cornerRadius = cornerRadius.toFloat()
        normal.setStroke(strokeSize,strokeColor)

        press.setColor(purePressColor)
        press.cornerRadius = cornerRadius.toFloat()
        press.setStroke(strokeSize,strokeColor)

        val drawable = StateListDrawable()
        drawable.addState(intArrayOf(android.R.attr.state_pressed),press)
        drawable.addState(intArrayOf(0),normal)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = (drawable)
        } else {
            setBackgroundDrawable(drawable)
        }
    }

    /**
     *按钮渐变+纯色边框样式
     */

    private fun GradientStyle(){
        val normal = GradientDrawable(getOrientation(GraditenOrientaton),intArrayOf(gradientNormalStartColor, gradientNormalEndColor))
        val press = GradientDrawable(getOrientation(GraditenOrientaton),intArrayOf(gradientPressStartColor, gradientPressEndColor))

        normal.cornerRadius = cornerRadius.toFloat()
        normal.setStroke(strokeSize,strokeColor)

        press.cornerRadius = cornerRadius.toFloat()
        press.setStroke(strokeSize,strokeColor)

        val drawable = StateListDrawable()
        drawable.addState(intArrayOf(android.R.attr.state_pressed),press)
        drawable.addState(intArrayOf(0),normal)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = (drawable)
        } else {
            setBackgroundDrawable(drawable)
        }

    }

    /**
     * 内容填充色
     */
    private fun PureContent(view:TextView){
        val normal = GradientDrawable()
        val press = GradientDrawable()

        normal.setColor(pureNormalColor)
        normal.cornerRadius = cornerRadius.toFloat()

        press.setColor(purePressColor)
        press.cornerRadius = cornerRadius.toFloat()

        val drawable = StateListDrawable()
        drawable.addState(intArrayOf(android.R.attr.state_pressed), press)
        drawable.addState(intArrayOf(0), normal)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.background = (drawable)
        } else {
            view.setBackgroundDrawable(drawable)
        }
    }

    /**渐变边框*/
    private fun GradientStrok(){

        val normal = GradientDrawable(getOrientation(GraditenOrientaton), intArrayOf(strokeGradientStartColor, strokeGradientEndColor))
        normal.cornerRadius = cornerRadius.toFloat()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = normal
        } else {
            setBackgroundDrawable(normal)
        }

    }

    private fun getOrientation(orientation:Int):GradientDrawable.Orientation{

        when(orientation){

            0->{
                return GradientDrawable.Orientation.TOP_BOTTOM
            }
            1->{
                return GradientDrawable.Orientation.TR_BL
            }
            2->{
                return GradientDrawable.Orientation.RIGHT_LEFT
            }
            3->{
                return GradientDrawable.Orientation.BR_TL
            }
            4->{
                return GradientDrawable.Orientation.BOTTOM_TOP;
            }
            5->{
                return GradientDrawable.Orientation.BL_TR;
            }
            6->{
                return GradientDrawable.Orientation.LEFT_RIGHT
            }
            7->{
                return GradientDrawable.Orientation.TL_BR
            }

            else->{
                return GradientDrawable.Orientation.TOP_BOTTOM
            }
        }
    }
}