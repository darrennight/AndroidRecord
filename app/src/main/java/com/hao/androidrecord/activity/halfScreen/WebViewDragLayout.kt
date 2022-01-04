package com.hao.androidrecord.activity.halfScreen

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout

class WebViewDragLayout : FrameLayout {

    private var downY: Float = 0f
    private var hasTouched = false
    private var mHidePageListener: HidePageListener? = null
    private val goUpAnimTime = 200L
    private val goDownAnimTime = 200L
    private val originalPaddingTop = 300
    private var mWebView: WebView? = null
    private lateinit var mToCloseLayout: View

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    fun setHidePageListener(hidePageListener: HidePageListener) {
        mHidePageListener = hidePageListener
    }

    fun setChildViews(webView: WebView, toCloseLayout: View) {
        mWebView = webView
        mToCloseLayout = toCloseLayout
    }

    private fun shouldIntercept(): Boolean {
        return true
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (!shouldIntercept()) {
            return false
        }
        if (isTouchOnCloseLayout(event)) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downY = event.rawY
                return !hasTouched
            }
            MotionEvent.ACTION_MOVE -> {
                var newPadding = (event.rawY - downY).toInt()
                if (!hasTouched) {
                    newPadding += originalPaddingTop
                }
                if (newPadding <= 0) {
                    newPadding = 0
                }

                return !hasTouched || (isMovingDown(newPadding) && isWebViewReachedTop())
            }
        }
        return false
    }

    /**
     * 判断是否点击在toCloseLayout上
     */
    private fun isTouchOnCloseLayout(event: MotionEvent) =
        event.y >= mToCloseLayout.y && event.y <= mToCloseLayout.y + mToCloseLayout.height

    private fun isMovingDown(newPadding: Int) = newPadding > 0

    private fun isWebViewReachedTop() = mWebView?.scrollY == 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var newPadding: Int
        if (event.action == MotionEvent.ACTION_DOWN) {
            downY = event.rawY
        } else if (event.action == MotionEvent.ACTION_MOVE) {

            var yOffset = (event.rawY - downY).toInt()
            newPadding = yOffset
            if (!hasTouched) {
                newPadding += originalPaddingTop
            }
            if (newPadding <= 0) {
                newPadding = 0
            }
            updateTopPadding(newPadding)
        } else if (event.action == MotionEvent.ACTION_UP) {
            hasTouched = true
            val yOffset = event.rawY - downY
            if (yOffset > height / 4) {
                hidePageWithAnim()
            } else {
                movePageToTopWithAnim()
            }
        }
        return true
    }

    private fun movePageToTopWithAnim() {
        val anim = ObjectAnimator.ofInt(paddingTop, 0)
        anim.duration = goUpAnimTime
        anim.addUpdateListener { valueAnimator ->
            updateTopPadding(
                valueAnimator.animatedValue.toString().toInt()
            )
        }
        anim.start()
    }

    private fun updateTopPadding(paddingValue: Int) {
        setPadding(0, paddingValue, 0, 0)
    }

    fun hidePageWithAnim() {
        val anim = ObjectAnimator.ofInt(paddingTop, height)
        anim.duration = goDownAnimTime
        anim.addUpdateListener { valueAnimator ->
            updateTopPadding(
                valueAnimator.animatedValue.toString().toInt()
            )
        }
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(p0: Animator?) {
                mHidePageListener?.onHide()
                visibility = GONE
            }

            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })
        anim.start()
    }
    interface HidePageListener {
        fun onHide()
    }
}

