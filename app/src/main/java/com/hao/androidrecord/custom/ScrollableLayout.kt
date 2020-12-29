package com.hao.androidrecord.custom

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.LinearLayout
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager

/**
 * @author zenghao
 * @since 2018/6/13 上午11:13
 */
class ScrollableLayout : LinearLayout {

  private val tag = "cp:scrollableLayout"
  private var mDownX: Float = 0.toFloat()
  private var mDownY: Float = 0.toFloat()
  private var mLastY: Float = 0.toFloat()

  private val minY = 0
  var maxY = 0
    private set
  private var mHeadHeight: Int = 0
  private var mExpandHeight: Int = 0
  private var mTouchSlop: Int = 0
  private var mMinimumVelocity: Int = 0
  private var mMaximumVelocity: Int = 0
  // 方向
  private var mDirection: ScrollableLayout.DIRECTION? = null
  private var mCurY: Int = 0
  private var mLastScrollerY: Int = 0
  private var needCheckUpdown: Boolean = false
  private var updown: Boolean = false
  private var mDisallowIntercept: Boolean = false
  private var isClickHead: Boolean = false
  private var isClickHeadExpand: Boolean = false

  private var mHeadView: View? = null
  private var childViewPager: ViewPager? = null

  private var mScroller: Scroller? = null
  private var mVelocityTracker: VelocityTracker? = null

  private var onScrollListener: ScrollableLayout.OnScrollListener? = null

  var helper: ScrollableHelper? = null
    private set

  val isSticked: Boolean
    get() = mCurY == maxY

  val isHeadTop: Boolean
    get() = mCurY == minY

  /**
   * 滑动方向 *
   */
  internal enum class DIRECTION {
    UP, // 向上划
    DOWN// 向下划
  }

  interface OnScrollListener {

    fun onScroll(currentY: Int, maxY: Int)

  }

  fun setOnScrollListener(onScrollListener: ScrollableLayout.OnScrollListener) {
    this.onScrollListener = onScrollListener
  }

  constructor(context: Context) : super(context) {
    init(context)
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    init(context)
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr) {
    init(context)
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
      context, attrs, defStyleAttr, defStyleRes) {
    init(context)
  }

  private fun init(context: Context) {
    helper = ScrollableHelper()
    mScroller = Scroller(context)
    val configuration = ViewConfiguration.get(context)
    mTouchSlop = configuration.scaledTouchSlop
    mMinimumVelocity = configuration.scaledMinimumFlingVelocity
    mMaximumVelocity = configuration.scaledMaximumFlingVelocity
  }

  /**
   * 扩大头部点击滑动范围
   *
   * @param expandHeight
   */
  fun setClickHeadExpand(expandHeight: Int) {
    mExpandHeight = expandHeight
  }

  fun canPtr(): Boolean {
    return updown && mCurY == minY && helper!!.isTop()
  }

  fun requestScrollableLayoutDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    super.requestDisallowInterceptTouchEvent(disallowIntercept)
    mDisallowIntercept = disallowIntercept
  }

  override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
    val currentX = ev.x
    val currentY = ev.y
    val deltaY: Float
    val shiftX = Math.abs(currentX - mDownX).toInt()
    val shiftY = Math.abs(currentY - mDownY).toInt()
    when (ev.action) {
      MotionEvent.ACTION_DOWN -> {
        mDisallowIntercept = false
        needCheckUpdown = true
        updown = true
        mDownX = currentX
        mDownY = currentY
        mLastY = currentY
        checkIsClickHead(currentY.toInt(), mHeadHeight, scrollY)
        checkIsClickHeadExpand(currentY.toInt(), mHeadHeight, scrollY)
        initOrResetVelocityTracker()
        mVelocityTracker!!.addMovement(ev)
        mScroller!!.forceFinished(true)
      }
      MotionEvent.ACTION_MOVE -> {
        if (mDisallowIntercept) {
          super.dispatchTouchEvent(ev)
          return true
        }
        initVelocityTrackerIfNotExists()
        mVelocityTracker!!.addMovement(ev)
        deltaY = mLastY - currentY
        if (needCheckUpdown) {
          if (shiftY > mTouchSlop && shiftY > shiftX) {
            needCheckUpdown = false
            updown = true
          }
        }

        if (updown && shiftY > mTouchSlop &&  (!isSticked || helper!!.isTop() || isClickHeadExpand)) {

          if (childViewPager != null) {
            childViewPager!!.requestDisallowInterceptTouchEvent(true)
          }
          scrollBy(0, (deltaY + 0.5).toInt())
        }
        mLastY = currentY
      }
      MotionEvent.ACTION_UP -> if (updown && shiftY > shiftX && shiftY > mTouchSlop) {
        mVelocityTracker!!.computeCurrentVelocity(1000, mMaximumVelocity.toFloat())
        val yVelocity = -mVelocityTracker!!.yVelocity
        var dislowChild = false
        if (Math.abs(yVelocity) > mMinimumVelocity) {
          mDirection = if (yVelocity > 0) ScrollableLayout.DIRECTION.UP else ScrollableLayout.DIRECTION.DOWN
          if (mDirection == ScrollableLayout.DIRECTION.UP && isSticked || !isSticked && scrollY == 0 && mDirection == ScrollableLayout.DIRECTION.DOWN) {
            dislowChild = true
          } else {
            mScroller!!.fling(0, scrollY, 0, yVelocity.toInt(), 0, 0, -Integer.MAX_VALUE,
                Integer.MAX_VALUE)
            mScroller!!.computeScrollOffset()
            mLastScrollerY = scrollY
            invalidate()
          }
        }
        if (!dislowChild && (isClickHead || !isSticked)) {
          val action = ev.action
          ev.action = MotionEvent.ACTION_CANCEL
          val dispathResult = super.dispatchTouchEvent(ev)
          ev.action = action
          return dispathResult
        }
      }
      else -> {
      }
    }
    super.dispatchTouchEvent(ev)
    return true
  }

  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
  private fun getScrollerVelocity(distance: Int, duration: Int): Int {
    return if (mScroller == null) {
      0
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      mScroller!!.currVelocity.toInt()
    } else {
      distance / duration
    }
  }

  override fun computeScroll() {
    if (mScroller!!.computeScrollOffset()) {
      val currY = mScroller!!.currY
      if (mDirection == ScrollableLayout.DIRECTION.UP) {
        // 手势向上划
        if (isSticked) {
          val distance = mScroller!!.finalY - currY
          val duration = calcDuration(mScroller!!.duration, mScroller!!.timePassed())
          helper!!.smoothScrollBy(getScrollerVelocity(distance, duration), distance, duration)
          mScroller!!.forceFinished(true)
          return
        } else {
          scrollTo(0, currY)
        }
      } else {
        // 手势向下划
        if (helper!!.isTop() || isClickHeadExpand) {
          val deltaY = currY - mLastScrollerY
          val toY = scrollY + deltaY
          scrollTo(0, toY)
          if (mCurY <= minY) {
            mScroller!!.forceFinished(true)
            return
          }
        }
        invalidate()
      }
      mLastScrollerY = currY
    }
  }

  override fun scrollBy(x: Int, y: Int) {
    var y = y
    val scrollY = scrollY
    var toY = scrollY + y
    if (toY >= maxY) {
      toY = maxY
    } else if (toY <= minY) {
      toY = minY
    }
    y = toY - scrollY
    super.scrollBy(x, y)
  }

  override fun scrollTo(x: Int, y: Int) {
    var y = y
    if (y >= maxY) {
      y = maxY
    } else if (y <= minY) {
      y = minY
    }
    mCurY = y
    if (onScrollListener != null) {
      onScrollListener!!.onScroll(y, maxY)
    }
    super.scrollTo(x, y)
  }

  private fun initOrResetVelocityTracker() {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain()
    } else {
      mVelocityTracker!!.clear()
    }
  }

  private fun initVelocityTrackerIfNotExists() {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain()
    }
  }

  private fun recycleVelocityTracker() {
    if (mVelocityTracker != null) {
      mVelocityTracker!!.recycle()
      mVelocityTracker = null
    }
  }

  private fun checkIsClickHead(downY: Int, headHeight: Int, scrollY: Int) {
    isClickHead = downY + scrollY <= headHeight
  }

  private fun checkIsClickHeadExpand(downY: Int, headHeight: Int, scrollY: Int) {
    if (mExpandHeight <= 0) {
      isClickHeadExpand = false
    }
    isClickHeadExpand = downY + scrollY <= headHeight + mExpandHeight
  }

  private fun calcDuration(duration: Int, timepass: Int): Int {
    return duration - timepass
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    mHeadView = getChildAt(0)
    measureChildWithMargins(mHeadView, widthMeasureSpec, 0, View.MeasureSpec.UNSPECIFIED, 0)
    maxY = mHeadView!!.measuredHeight
    mHeadHeight = mHeadView!!.measuredHeight
    super.onMeasure(widthMeasureSpec,
        View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(heightMeasureSpec) + maxY,
            View.MeasureSpec.EXACTLY))
  }

  override fun onFinishInflate() {
    if (mHeadView != null && !mHeadView!!.isClickable) {
      mHeadView!!.isClickable = true
    }
    val childCount = childCount
    for (i in 0 until childCount) {
      val childAt = getChildAt(i)
      if (childAt != null && childAt is ViewPager) {
        childViewPager = childAt
      }
    }
    super.onFinishInflate()
  }
}

