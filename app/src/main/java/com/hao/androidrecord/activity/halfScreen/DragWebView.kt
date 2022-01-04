package com.hao.androidrecord.activity.halfScreen

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.webkit.WebView

class DragWebView : WebView {

    private var downY: Float = 0f
    private var hasTouched = false
    private var mHidePageListener: HidePageListener? = null
    private val goUpAnimTime = 200L
    private val goDownAnimTime = 200L
    private val originalPaddingTop = 300

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    fun setHidePageListener(hidePageListener: HidePageListener) {
        mHidePageListener = hidePageListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val parentView = parent as ViewGroup

        var newPadding: Int
        if (event.action == MotionEvent.ACTION_DOWN) {
            downY = event.rawY
            if (hasTouched) {
                return super.onTouchEvent(event)
            }
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            var yOffset = (event.rawY - downY).toInt()
            newPadding = yOffset
            if (!hasTouched) {
                newPadding += originalPaddingTop
            }
            if (newPadding <= 0) {
                newPadding = 0
            }
            if (hasTouched) {
                if (newPadding > 0 && scrollY <= 0) {
                    updateTopPadding(parentView, newPadding)
                }
                return super.onTouchEvent(event)
            } else {
                updateTopPadding(parentView, newPadding)
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            hasTouched = true
            val yOffset = event.rawY - downY
            if (yOffset > parentView.height / 4) {
                hidePageWithAnim(parentView)
            } else {
                movePageToTopWithAnim(parentView)
            }
            return super.onTouchEvent(event)

        } else if (event.action == MotionEvent.ACTION_CANCEL) {
            return super.onTouchEvent(event)
        }
        return true
    }

    private fun movePageToTopWithAnim(parentView: ViewGroup) {
        val anim = ObjectAnimator.ofInt(parentView.paddingTop, 0)
        anim.duration = goUpAnimTime
        anim.addUpdateListener { valueAnimator -> updateTopPadding(parentView, valueAnimator.animatedValue.toString().toInt()) }
        anim.start()
    }

    private fun updateTopPadding(parentView: ViewGroup, paddingValue: Int) {
        parentView.setPadding(0, paddingValue, 0, 0)
    }

    private fun hidePageWithAnim(parentView: ViewGroup) {

        val anim = ObjectAnimator.ofInt(parentView.paddingTop, parentView.height)
        anim.duration = goDownAnimTime
        anim.addUpdateListener { valueAnimator -> updateTopPadding(parentView, valueAnimator.animatedValue.toString().toInt()) }
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(p0: Animator?) {
                mHidePageListener?.onHide()
            }

            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })
        anim.start()
    }
}

interface HidePageListener {
    fun onHide()
}