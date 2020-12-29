package com.hao.androidrecord.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ListView
import android.widget.OverScroller
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewCompat
import com.liaoinstan.springview.widget.SpringView

/**
 * @author zenghao
 * @since 2018/6/12 下午1:59
 */
open class SpringWidget : ViewGroup{

  private var inflater: LayoutInflater
  private val mScroller: OverScroller
  private val mHandler = Handler()
  private var listener: SpringView.OnFreshListener? = null         //监听回调
  private var isCallDown = false     //用于判断是否在下拉时到达临界点
  private var isCallUp = false       //用于判断是否在上拉时到达临界点
  private var isFirst = true         //用于判断是否是拖动动作的第一次move
  private var needChange = false     //是否需要改变样式
  private var needResetAnim = false  //是否需要弹回的动画
  //private val isFullEnable = false   //是否超过一屏时才允许上拉，为false则不满一屏也可以上拉，注意样式为isOverlap时，无论如何也不允许在不满一屏时上拉
  private var isMoveNow = false       //当前是否正在拖动
  private var lastMoveTime: Long = 0
  var isEnableHeader = true    //是否禁止header下拉（默认可用）
  var isEnableFooter = true    //是否禁止footer上拉（默认可用）

  private var MOVE_TIME = 400
  private var MOVE_TIME_OVER = 200

  private var give: SpringView.Give = SpringView.Give.BOTH
  private var type: SpringView.Type? = SpringView.Type.FOLLOW
  private var _type: SpringView.Type? = null

  //移动参数：计算手指移动量的时候会用到这个值，值越大，移动量越小，若值为1则手指移动多少就滑动多少px
  private val MOVE_PARA = 2.0
  //最大拉动距离(px)，拉动距离越靠近这个值拉动就越缓慢
  private var MAX_HEADER_PULL_HEIGHT = 600
  private var MAX_FOOTER_PULL_HEIGHT = 600
  //拉动多少距离被认定为刷新(加载)动作
  private var HEADER_LIMIT_HEIGHT: Int = 0
  private var FOOTER_LIMIT_HEIGHT: Int = 0
  private var HEADER_SPRING_HEIGHT: Int = 0
  private var FOOTER_SPRING_HEIGHT: Int = 0
  //储存上次的Y坐标
  private var mLastY: Float = 0.toFloat()
  private var mLastX: Float = 0.toFloat()
  //储存第一次的Y坐标
  private var mfirstY: Float = 0.toFloat()
  //储存手指拉动的总距离
  private var dsY: Float = 0.toFloat()
  //滑动事件目前是否在本控件的控制中（用于过渡滑动事件：比如正在滚动recyclerView到顶部后自动切换到SpringView处理后续事件进行下拉）
  private var isInControl = false
  //存储拉动前的位置
  private val mRect = Rect()

  //头尾视图
  var headerView: View? = null
    private set
  var footerView: View? = null
    private set
  //目标View，即被SpringView包裹的View
  private var contentView: View? = null
  //头尾布局资源id
  private var headerResoureId: Int = 0
  private var footerResoureId: Int = 0

  //记录单次滚动x,y轴偏移量
  private var dy: Float = 0.toFloat()
  private var dx: Float = 0.toFloat()
  //记录当前滚动事件是否需要SpringView进行处理，如果需要则SpringView拦截事件（比如已经滚动到顶部了还继续下拉）
  private var isNeedMyMove: Boolean = false

  /**
   * 处理多点触控的情况，准确地计算Y坐标和移动距离dy
   * 同时兼容单点触控的情况
   */
  private var mActivePointerId = MotionEvent.INVALID_POINTER_ID

  private var _firstDrag = true

  private var callFreshORload = 0
  private var isFullAnim: Boolean = false
  private var hasCallFull = false
  private var hasCallRefresh = false



  /**
   * 判断目标View是否滑动到顶部 还能否继续滑动
   */
  open fun isChildScrollToTop(): Boolean{
    return !ViewCompat.canScrollVertically(contentView, -1)
  }

  /**
   * 是否滑动到底部
   */
  open fun isChildScrollToBottom(): Boolean{
    return !ViewCompat.canScrollVertically(contentView, 1)
  }

  /**
   * 判断顶部拉动是否超过临界值
   */
  private val isTopOverFarm: Boolean
    get() = if (type == SpringView.Type.OVERLAP) {
      contentView!!.top > HEADER_LIMIT_HEIGHT
    } else if (type == SpringView.Type.FOLLOW) {
      -scrollY > HEADER_LIMIT_HEIGHT
    } else
      false

  /**
   * 判断底部拉动是否超过临界值
   */
  private val isBottomOverFarm: Boolean
    get() = if (type == SpringView.Type.OVERLAP) {
      height - contentView!!.bottom > FOOTER_LIMIT_HEIGHT
    } else if (type == SpringView.Type.FOLLOW) {
      scrollY > FOOTER_LIMIT_HEIGHT
    } else
      false

  /**
   * 判断当前状态是否拉动到顶部
   */
  private val isTop: Boolean
    get() = if (type == SpringView.Type.OVERLAP) {
      contentView!!.top > 0
    } else if (type == SpringView.Type.FOLLOW) {
      scrollY < 0
    } else
      false

  /**
   * 判断当前状态是否拉动到底部
   */
  private val isBottom: Boolean
    get() = if (type == SpringView.Type.OVERLAP) {
      contentView!!.top < 0
    } else if (type == SpringView.Type.FOLLOW) {
      scrollY > 0
    } else
      false

  /**
   * 判断当前滚动位置是否已经进入可折叠范围了
   */
  private val isFlow: Boolean
    get() = if (type == SpringView.Type.OVERLAP) {
      contentView!!.top < 30 && contentView!!.top > -30
    } else if (type == SpringView.Type.FOLLOW) {
      scrollY > -30 && scrollY < 30
    } else
      false

  /**
   * 是否禁用SpringView
   */
  var isEnable: Boolean
    get() = isEnableHeader && isEnableFooter
    set(enable) {
      this.isEnableHeader = enable
      this.isEnableFooter = enable
    }

  private var needChangeHeader = false
  private var needChangeFooter = false
  private var _headerHander: SpringView.DragHander? = null
  private var _footerHander: SpringView.DragHander? = null
  private var headerHander: SpringView.DragHander? = null
  private var footerHander: SpringView.DragHander? = null

  var header: SpringView.DragHander?
    get() = headerHander
    set(headerHander) = if (this.headerHander != null && isTop) {
      needChangeHeader = true
      _headerHander = headerHander
      resetPosition()
    } else {
      setHeaderIn(headerHander)
    }

  var footer: SpringView.DragHander?
    get() = footerHander
    set(footerHander) = if (this.footerHander != null && isBottom) {
      needChangeFooter = true
      _footerHander = footerHander
      resetPosition()
    } else {
      setFooterIn(footerHander)
    }

  //是否需要回调接口：TOP 只回调刷新、BOTTOM 只回调加载更多、BOTH 都需要、NONE 都不
  enum class Give {
    BOTH, TOP, BOTTOM, NONE
  }

  enum class Type {
    OVERLAP, FOLLOW
  }

  constructor(context: Context?) : this(context,null)
  constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr){

    inflater = LayoutInflater.from(context)

    mScroller = OverScroller(context)

    context?.let {
      //获取自定义属性
      val ta = context.obtainStyledAttributes(attrs, com.liaoinstan.springview.R.styleable.SpringView)
      if (ta.hasValue(com.liaoinstan.springview.R.styleable.SpringView_type)) {
        val type_int = ta.getInt(com.liaoinstan.springview.R.styleable.SpringView_type, 0)
        type = SpringView.Type.values()[type_int]
      }
      if (ta.hasValue(com.liaoinstan.springview.R.styleable.SpringView_give)) {
        val give_int = ta.getInt(com.liaoinstan.springview.R.styleable.SpringView_give, 0)
        give = SpringView.Give.values()[give_int]
      }
      if (ta.hasValue(com.liaoinstan.springview.R.styleable.SpringView_header)) {
        headerResoureId = ta.getResourceId(com.liaoinstan.springview.R.styleable.SpringView_header, 0)
      }
      if (ta.hasValue(com.liaoinstan.springview.R.styleable.SpringView_footer)) {
        footerResoureId = ta.getResourceId(com.liaoinstan.springview.R.styleable.SpringView_footer, 0)
      }
      ta.recycle()
    }

  }



  override fun onFinishInflate() {
    contentView = getChildAt(0)
    if (contentView == null) {
      return
    }
    setPadding(0, 0, 0, 0)
    //contentView.setPadding(0, contentView.getPaddingTop(), 0, contentView.getPaddingBottom());
    if (headerResoureId != 0) {
      inflater.inflate(headerResoureId, this, true)
      headerView = getChildAt(childCount - 1)
    }
    if (footerResoureId != 0) {
      inflater.inflate(footerResoureId, this, true)
      footerView = getChildAt(childCount - 1)
      footerView!!.visibility = View.INVISIBLE
    }

    contentView!!.bringToFront() //把内容放在最前端

    super.onFinishInflate()
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    if (childCount > 0) {
      for (i in 0 until childCount) {
        val child = getChildAt(i)
        measureChild(child, widthMeasureSpec, heightMeasureSpec)
      }
    }
    //如果是动态设置的头部，则使用动态设置的参数
    if (headerHander != null) {
      //设置下拉最大高度，只有在>0时才生效，否则使用默认值
      val xh = headerHander!!.getDragMaxHeight(headerView)
      if (xh > 0) MAX_HEADER_PULL_HEIGHT = xh
      //设置下拉临界高度，只有在>0时才生效，否则默认为header的高度
      val h = headerHander!!.getDragLimitHeight(headerView)
      HEADER_LIMIT_HEIGHT = if (h > 0) h else headerView!!.measuredHeight
      //设置下拉弹动高度，只有在>0时才生效，否则默认和临界高度一致
      val sh = headerHander!!.getDragSpringHeight(headerView)
      HEADER_SPRING_HEIGHT = if (sh > 0) sh else HEADER_LIMIT_HEIGHT
    } else {
      //不是动态设置的头部，设置默认值
      if (headerView != null) HEADER_LIMIT_HEIGHT = headerView!!.measuredHeight
      HEADER_SPRING_HEIGHT = HEADER_LIMIT_HEIGHT
    }
    //设置尾部参数，和上面一样
    if (footerHander != null) {
      val xh = footerHander!!.getDragMaxHeight(footerView)
      if (xh > 0) MAX_FOOTER_PULL_HEIGHT = xh
      val h = footerHander!!.getDragLimitHeight(footerView)
      FOOTER_LIMIT_HEIGHT = if (h > 0) h else footerView!!.measuredHeight
      val sh = footerHander!!.getDragSpringHeight(footerView)
      FOOTER_SPRING_HEIGHT = if (sh > 0) sh else FOOTER_LIMIT_HEIGHT
    } else {
      if (footerView != null) FOOTER_LIMIT_HEIGHT = footerView!!.measuredHeight
      FOOTER_SPRING_HEIGHT = FOOTER_LIMIT_HEIGHT
    }
    setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    if (contentView != null) {
      if (type == SpringView.Type.OVERLAP) {
        if (headerView != null) {
          headerView!!.layout(0, 0, width, headerView!!.measuredHeight)
        }
        if (footerView != null) {
          footerView!!.layout(0, height - footerView!!.measuredHeight, width, height)
        }
      } else if (type == SpringView.Type.FOLLOW) {
        if (headerView != null) {
          headerView!!.layout(0, -headerView!!.measuredHeight, width, 0)
        }
        if (footerView != null) {
          footerView!!.layout(0, height, width, height + footerView!!.measuredHeight)
        }
      }
      contentView!!.layout(0, 0, contentView!!.measuredWidth, contentView!!.measuredHeight)
    }
  }

  override fun dispatchTouchEvent(event: MotionEvent): Boolean {
    dealMulTouchEvent(event)
    val action = event.action
    when (action) {
      MotionEvent.ACTION_DOWN -> {
        hasCallFull = false
        hasCallRefresh = false
        mfirstY = event.y
        //                boolean isTop = isChildScrollToTop();
        //                boolean isBottom = isChildScrollToBottom();
        //                if (isTop || isBottom) isNeedMyMove = false;
        isNeedMyMove = false
      }
      MotionEvent.ACTION_MOVE -> {
        dsY += dy
        isMoveNow = true
        isNeedMyMove = isNeedMyMove()
        if (isNeedMyMove && !isInControl) {
          //把内部控件的事件转发给本控件处理
          isInControl = true
          event.action = MotionEvent.ACTION_CANCEL
          val ev2 = MotionEvent.obtain(event)
          dispatchTouchEvent(event)
          ev2.action = MotionEvent.ACTION_DOWN
          return dispatchTouchEvent(ev2)
        }
      }
      MotionEvent.ACTION_UP -> {
        isMoveNow = false
        lastMoveTime = System.currentTimeMillis()
      }
      MotionEvent.ACTION_CANCEL -> isMoveNow = false
    }
    return super.dispatchTouchEvent(event)
  }

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    return isNeedMyMove
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    if (contentView == null) {
      return false
    }
    val action = event.action
    when (action) {
      MotionEvent.ACTION_DOWN -> isFirst = true
      MotionEvent.ACTION_MOVE ->
        //getParent().requestDisallowInterceptTouchEvent(true);
        if (isNeedMyMove) {
          needResetAnim = false      //按下的时候关闭回弹
          //执行位移操作
          doMove()
          //下拉的时候显示header并隐藏footer，上拉的时候相反
          if (isTop) {
            if (headerView != null && headerView!!.visibility != View.VISIBLE)
              headerView!!.visibility = View.VISIBLE
            if (footerView != null && footerView!!.visibility != View.INVISIBLE)
              footerView!!.visibility = View.INVISIBLE
          } else if (isBottom) {
            if (headerView != null && headerView!!.visibility != View.INVISIBLE)
              headerView!!.visibility = View.INVISIBLE
            if (footerView != null && footerView!!.visibility != View.VISIBLE)
              footerView!!.visibility = View.VISIBLE
          }
          //回调onDropAnim接口
          callOnDropAnim()
          //回调callOnPreDrag接口
          callOnPreDrag()
          //回调onLimitDes接口
          callOnLimitDes()
          isFirst = false
        } else {
          //手指在产生移动的时候（dy!=0）才重置位置
          if (dy != 0f && isFlow) {
            resetPosition()
            //把滚动事件交给内部控件处理
            event.action = MotionEvent.ACTION_DOWN
            dispatchTouchEvent(event)
            isInControl = false
          }
        }
      MotionEvent.ACTION_UP -> {
        needResetAnim = true      //松开的时候打开回弹
        isFirst = true
        _firstDrag = true
        restSmartPosition()
        dsY = 0f
        dy = 0f
      }
      MotionEvent.ACTION_CANCEL -> {
      }
    }//if (!mScroller.isFinished()) mScroller.abortAnimation();//不需要处理
    return false
  }

  fun dealMulTouchEvent(ev: MotionEvent) {
    val action = MotionEventCompat.getActionMasked(ev)
    when (action) {
      MotionEvent.ACTION_DOWN -> {
        val pointerIndex = MotionEventCompat.getActionIndex(ev)
        val x = MotionEventCompat.getX(ev, pointerIndex)
        val y = MotionEventCompat.getY(ev, pointerIndex)
        mLastX = x
        mLastY = y
        mActivePointerId = MotionEventCompat.getPointerId(ev, 0)
      }
      MotionEvent.ACTION_MOVE -> {
        val pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId)
        val x = MotionEventCompat.getX(ev, pointerIndex)
        val y = MotionEventCompat.getY(ev, pointerIndex)
        dx = x - mLastX
        dy = y - mLastY
        mLastY = y
        mLastX = x
      }
      MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> mActivePointerId = MotionEvent.INVALID_POINTER_ID
      MotionEvent.ACTION_POINTER_DOWN -> {
        val pointerIndex = MotionEventCompat.getActionIndex(ev)
        val pointerId = MotionEventCompat.getPointerId(ev, pointerIndex)
        if (pointerId != mActivePointerId) {
          mLastX = MotionEventCompat.getX(ev, pointerIndex)
          mLastY = MotionEventCompat.getY(ev, pointerIndex)
          mActivePointerId = MotionEventCompat.getPointerId(ev, pointerIndex)
        }
      }
      MotionEvent.ACTION_POINTER_UP -> {
        val pointerIndex = MotionEventCompat.getActionIndex(ev)
        val pointerId = MotionEventCompat.getPointerId(ev, pointerIndex)
        if (pointerId == mActivePointerId) {
          val newPointerIndex = if (pointerIndex == 0) 1 else 0
          mLastX = MotionEventCompat.getX(ev, newPointerIndex)
          mLastY = MotionEventCompat.getY(ev, newPointerIndex)
          mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex)
        }
      }
    }
  }

  //执行位移操作
  private fun doMove() {
    if (type == SpringView.Type.OVERLAP) {
      //记录移动前的位置
      if (mRect.isEmpty) {
        mRect.set(contentView!!.left, contentView!!.top, contentView!!.right, contentView!!.bottom)
      }
      //根据下拉高度计算位移距离，（越拉越慢）
      val movedy: Int
      if (dy > 0) {
        movedy = (((MAX_HEADER_PULL_HEIGHT - contentView!!.top) / MAX_HEADER_PULL_HEIGHT.toFloat()).toFloat() * dy / MOVE_PARA).toInt()
      } else {
        movedy = (((MAX_FOOTER_PULL_HEIGHT - (height - contentView!!.bottom)) / MAX_FOOTER_PULL_HEIGHT.toFloat()).toFloat() * dy / MOVE_PARA).toInt()
      }
      val top = contentView!!.top + movedy
      contentView!!.layout(contentView!!.left, top, contentView!!.right,
          top + contentView!!.measuredHeight)
    } else if (type == SpringView.Type.FOLLOW) {
      //根据下拉高度计算位移距离，（越拉越慢）
      val movedx: Int
      if (dy > 0) {
        movedx = (((MAX_HEADER_PULL_HEIGHT + scrollY) / MAX_HEADER_PULL_HEIGHT.toFloat()).toFloat() * dy / MOVE_PARA).toInt()
      } else {
        movedx = (((MAX_FOOTER_PULL_HEIGHT - scrollY) / MAX_FOOTER_PULL_HEIGHT.toFloat()).toFloat() * dy / MOVE_PARA).toInt()
      }
      scrollBy(0, (-movedx).toInt())
    }
  }

  //回调自定义header/footer OnDropAnim接口
  private fun callOnDropAnim() {
    if (type == SpringView.Type.OVERLAP) {
      if (contentView!!.top > 0)
        if (headerHander != null) headerHander!!.onDropAnim(headerView, contentView!!.top)
      if (contentView!!.top < 0)
        if (footerHander != null) footerHander!!.onDropAnim(footerView, contentView!!.top)
    } else if (type == SpringView.Type.FOLLOW) {
      if (scrollY < 0)
        if (headerHander != null) headerHander!!.onDropAnim(headerView, -scrollY)
      if (scrollY > 0)
        if (footerHander != null) footerHander!!.onDropAnim(footerView, -scrollY)
    }
  }

  //回调自定义header/footer OnPreDrag接口
  private fun callOnPreDrag() {
    if (_firstDrag) {
      if (isTop) {
        if (headerHander != null) headerHander!!.onPreDrag(headerView)
        _firstDrag = false
      } else if (isBottom) {
        if (footerHander != null) footerHander!!.onPreDrag(footerView)
        _firstDrag = false
      }
    }
  }

  //回调自定义header/footer OnLimitDes接口
  private fun callOnLimitDes() {
    var topORbottom = false
    if (type == SpringView.Type.OVERLAP) {
      topORbottom = contentView!!.top >= 0 && isChildScrollToTop()
    } else if (type == SpringView.Type.FOLLOW) {
      topORbottom = scrollY <= 0 && isChildScrollToTop()
    }
    if (isFirst) {
      if (topORbottom) {
        isCallUp = true
        isCallDown = false
      } else {
        isCallUp = false
        isCallDown = true
      }
    }
    if (dy == 0f) return
    val upORdown = dy < 0
    if (topORbottom) {
      if (!upORdown) {
        if (isTopOverFarm && !isCallDown) {
          isCallDown = true
          if (headerHander != null) headerHander!!.onLimitDes(headerView, upORdown)
          isCallUp = false
        }
      } else {
        if (!isTopOverFarm && !isCallUp) {
          isCallUp = true
          if (headerHander != null) headerHander!!.onLimitDes(headerView, upORdown)
          isCallDown = false
        }
      }
    } else {
      if (upORdown) {
        if (isBottomOverFarm && !isCallUp) {
          isCallUp = true
          if (footerHander != null) footerHander!!.onLimitDes(footerView, upORdown)
          isCallDown = false
        }
      } else {
        if (!isBottomOverFarm && !isCallDown) {
          isCallDown = true
          if (footerHander != null) footerHander!!.onLimitDes(footerView, upORdown)
          isCallUp = false
        }
      }
    }
  }

  override fun computeScroll() {
    if (mScroller.computeScrollOffset()) {
      scrollTo(0, mScroller.currY)
      invalidate()
    }
    //在滚动动画完全结束后回调接口
    //滚动回调过程中mScroller.isFinished会多次返回true，导致判断条件被多次进入，设置标志位保证只调用一次
    if (!isMoveNow && type == SpringView.Type.FOLLOW && mScroller.isFinished) {
      if (isFullAnim) {
        if (!hasCallFull) {
          hasCallFull = true
          callOnAfterFullAnim()
        }
      } else {
        if (!hasCallRefresh) {
          hasCallRefresh = true
          callOnAfterRefreshAnim()
        }
      }
    }
  }

  /**
   * 判断是否需要由该控件来控制滑动事件
   */
  private fun isNeedMyMove(): Boolean {
    if (contentView == null) {
      return false
    }
    if (Math.abs(dy) < Math.abs(dx)) {
      return false
    }
    val isTop = isChildScrollToTop()
    val isBottom = isChildScrollToBottom()
    //用户禁止了下拉操作，则不控制
    if (!isEnableHeader && isTop && dy > 0) {
      return false
    }
    //用户禁止了上拉操作，则不控制
    if (!isEnableFooter && isBottom && dy < 0) {
      return false
    }
    if (type == SpringView.Type.OVERLAP) {
      if (headerView != null) {
        if (isTop && dy > 0 || contentView!!.top > 0 + 20) {
          return true
        }
      }
      if (footerView != null) {
        if (isBottom && dy < 0 || contentView!!.bottom < mRect.bottom - 20) {
          return true
        }
      }
    } else if (type == SpringView.Type.FOLLOW) {
      if (headerView != null) {
        //其中的20是一个防止触摸误差的偏移量
        if (isTop && dy > 0 || scrollY < 0 - 20) {
          return true
        }
      }
      if (footerView != null) {
        if (isBottom && dy < 0 || scrollY > 0 + 20) {
          return true
        }
      }
    }
    return false
  }

  private fun callOnAfterFullAnim() {
    if (callFreshORload != 0) {
      callOnFinishAnim()
    }
    if (needChangeHeader) {
      needChangeHeader = false
      setHeaderIn(_headerHander)
    }
    if (needChangeFooter) {
      needChangeFooter = false
      setFooterIn(_footerHander)
    }
    //动画完成后检查是否需要切换type，是则切换
    if (needChange) {
      changeType(_type)
    }
  }

  private fun callOnAfterRefreshAnim() {
    if (type == SpringView.Type.FOLLOW) {
      if (isTop) {
        listener!!.onRefresh()
      } else if (isBottom) {
        listener!!.onLoadmore()
      }
    } else if (type == SpringView.Type.OVERLAP) {
      if (!isMoveNow) {
        val nowtime = System.currentTimeMillis()
        if (nowtime - lastMoveTime >= MOVE_TIME_OVER) {
          if (callFreshORload == 1)
            listener!!.onRefresh()
          if (callFreshORload == 2)
            listener!!.onLoadmore()
        }
      }
    }
  }

  /**
   * 重置控件位置到初始状态
   */
  private fun resetPosition() {
    isFullAnim = true
    isInControl = false    //重置位置的时候，滑动事件已经不在控件的控制中了
    if (type == SpringView.Type.OVERLAP) {
      if (mRect.bottom == 0 || mRect.right == 0) return
      //根据下拉高度计算弹回时间，时间最小100，最大400
      var time = 0
      if (contentView!!.height > 0) {
        time = Math.abs(400 * contentView!!.top / contentView!!.height)
      }
      if (time < 100) time = 100

      val animation = TranslateAnimation(0f, 0f, contentView!!.top.toFloat(), mRect.top.toFloat())
      animation.duration = time.toLong()
      animation.fillAfter = true
      animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}

        override fun onAnimationEnd(animation: Animation) {
          callOnAfterFullAnim()
        }

        override fun onAnimationRepeat(animation: Animation) {}
      })
      contentView!!.startAnimation(animation)
      contentView!!.layout(mRect.left, mRect.top, mRect.right, mRect.bottom)
    } else if (type == SpringView.Type.FOLLOW) {
      mScroller.startScroll(0, scrollY, 0, -scrollY, MOVE_TIME)
      invalidate()
    }
    //mRect.setEmpty();
  }

  private fun callOnFinishAnim() {
    if (callFreshORload != 0) {
      if (callFreshORload == 1) {
        if (headerHander != null) headerHander!!.onFinishAnim()
        if (give == SpringView.Give.BOTTOM || give == SpringView.Give.NONE) {
          listener!!.onRefresh()
        }
      } else if (callFreshORload == 2) {
        if (footerHander != null) footerHander!!.onFinishAnim()
        if (give == SpringView.Give.TOP || give == SpringView.Give.NONE) {
          listener!!.onLoadmore()
        }
      }
      callFreshORload = 0
    }
  }

  /**
   * 重置控件位置到刷新状态（或加载状态）
   */
  private fun resetRefreshPosition() {
    isFullAnim = false
    isInControl = false    //重置位置的时候，滑动事件已经不在控件的控制中了
    if (type == SpringView.Type.OVERLAP) {
      if (mRect.bottom == 0 || mRect.right == 0) return
      if (contentView!!.top > mRect.top) {    //下拉
        val animation = TranslateAnimation(0f, 0f,
            (contentView!!.top - HEADER_SPRING_HEIGHT).toFloat(), mRect.top.toFloat())
        animation.duration = MOVE_TIME_OVER.toLong()
        animation.fillAfter = true
        animation.setAnimationListener(object : Animation.AnimationListener {
          override fun onAnimationStart(animation: Animation) {}

          override fun onAnimationEnd(animation: Animation) {
            callOnAfterRefreshAnim()
          }

          override fun onAnimationRepeat(animation: Animation) {}
        })
        contentView!!.startAnimation(animation)
        contentView!!.layout(mRect.left, mRect.top + HEADER_SPRING_HEIGHT, mRect.right,
            mRect.bottom + HEADER_SPRING_HEIGHT)
      } else {     //上拉
        val animation = TranslateAnimation(0f, 0f,
            (contentView!!.top + FOOTER_SPRING_HEIGHT).toFloat(), mRect.top.toFloat())
        animation.duration = MOVE_TIME_OVER.toLong()
        animation.fillAfter = true
        animation.setAnimationListener(object : Animation.AnimationListener {
          override fun onAnimationStart(animation: Animation) {}

          override fun onAnimationEnd(animation: Animation) {
            callOnAfterRefreshAnim()
          }

          override fun onAnimationRepeat(animation: Animation) {}
        })
        contentView!!.startAnimation(animation)
        contentView!!.layout(mRect.left, mRect.top - FOOTER_SPRING_HEIGHT, mRect.right,
            mRect.bottom - FOOTER_SPRING_HEIGHT)
      }
    } else if (type == SpringView.Type.FOLLOW) {
      if (scrollY < 0) {     //下拉
        mScroller.startScroll(0, scrollY, 0, -scrollY - HEADER_SPRING_HEIGHT, MOVE_TIME)
        invalidate()
      } else {       //上拉
        mScroller.startScroll(0, scrollY, 0, -scrollY + FOOTER_SPRING_HEIGHT, MOVE_TIME)
        invalidate()
      }
    }
  }

  /**
   * [.callFresh]的执行方法，不要暴露在外部
   */
  private fun _callFresh() {
    headerView!!.visibility = View.VISIBLE
    if (type == SpringView.Type.OVERLAP) {
      if (mRect.isEmpty) {
        mRect.set(contentView!!.left, contentView!!.top, contentView!!.right, contentView!!.bottom)
      }
      val animation = TranslateAnimation(0f, 0f,
          (contentView!!.top - HEADER_SPRING_HEIGHT).toFloat(), mRect.top.toFloat())
      animation.duration = MOVE_TIME_OVER.toLong()
      animation.fillAfter = true
      animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {
          if (headerHander != null) headerHander!!.onStartAnim()
        }

        override fun onAnimationEnd(animation: Animation) {
          callFreshORload = 1
          needResetAnim = true
          listener!!.onRefresh()
        }

        override fun onAnimationRepeat(animation: Animation) {}
      })
      contentView!!.startAnimation(animation)
      contentView!!.layout(mRect.left, mRect.top + HEADER_SPRING_HEIGHT, mRect.right,
          mRect.bottom + HEADER_SPRING_HEIGHT)
    } else if (type == SpringView.Type.FOLLOW) {
      isFullAnim = false
      hasCallRefresh = false
      callFreshORload = 1
      needResetAnim = true
      if (headerHander != null) headerHander!!.onStartAnim()
      mScroller.startScroll(0, scrollY, 0, -scrollY - HEADER_SPRING_HEIGHT, MOVE_TIME)
      invalidate()
    }
  }

  /**
   * 智能判断是重置控件位置到初始状态还是到刷新/加载状态
   */
  private fun restSmartPosition() {
    if (listener == null) {
      resetPosition()
    } else {
      if (isTopOverFarm) {
        callFreshORload()
        if (give == SpringView.Give.BOTH || give == SpringView.Give.TOP)
          resetRefreshPosition()
        else
          resetPosition()
      } else if (isBottomOverFarm) {
        callFreshORload()
        if (give == SpringView.Give.BOTH || give == SpringView.Give.BOTTOM)
          resetRefreshPosition()
        else
          resetPosition()
      } else {
        resetPosition()
      }
    }
  }

  private fun callFreshORload() {
    if (isTop) {  //下拉
      callFreshORload = 1
      if (type == SpringView.Type.OVERLAP) {
        if (dsY > 200 || HEADER_LIMIT_HEIGHT >= HEADER_SPRING_HEIGHT) {
          if (headerHander != null) headerHander!!.onStartAnim()
        }
      } else if (type == SpringView.Type.FOLLOW) {
        if (headerHander != null) headerHander!!.onStartAnim()
      }
    } else if (isBottom) {
      callFreshORload = 2
      if (type == SpringView.Type.OVERLAP) {
        if (dsY < -200 || FOOTER_LIMIT_HEIGHT >= FOOTER_SPRING_HEIGHT) {
          if (footerHander != null) footerHander!!.onStartAnim()
        }
      } else if (type == SpringView.Type.FOLLOW) {
        if (footerHander != null) footerHander!!.onStartAnim()
      }
    }
  }

  /**
   * 切换Type的执行方法，之所以不暴露在外部，是防止用户在拖动过程中调用造成布局错乱，虽然并不会有人这样做
   * 所以在外部方法[.setType]中设置标志，然后在拖动完毕后判断是否需要调用，是则调用本方法执行切换
   */
  private fun changeType(type: SpringView.Type?) {
    this.type = type
    if (headerView != null && headerView!!.visibility != View.INVISIBLE) headerView!!.visibility = View.INVISIBLE
    if (footerView != null && footerView!!.visibility != View.INVISIBLE) footerView!!.visibility = View.INVISIBLE
    requestLayout()
    needChange = false
  }

  //#############################################
  //##            对外暴露的方法               ##
  //#############################################

  /**
   * 重置控件位置，暴露给外部的方法，用于在刷新或者加载完成后调用
   */
  fun onFinishFreshAndLoad() {
    if (!isMoveNow && needResetAnim) {
      val needTop = isTop && (give == SpringView.Give.TOP || give == SpringView.Give.BOTH)
      val needBottom = isBottom && (give == SpringView.Give.BOTTOM || give == SpringView.Give.BOTH)
      if (needTop || needBottom) {
        if (contentView is ListView) {
          //((ListView) contentView).smoothScrollByOffset(1);
          //刷新后调用，才能正确显示刷新的item，如果调用上面的方法，listview会被固定在底部
          //在版本更新中，这个问题已经被修复，保留注释
          //((ListView) contentView).smoothScrollBy(-1,0);
        }
        resetPosition()
      }
    }
  }

  @JvmOverloads
  fun onFinishFreshAndLoadDelay(delay: Int = 100) {
    mHandler.postDelayed({ onFinishFreshAndLoad() }, delay.toLong())
  }

  /**
   * 手动调用该方法使SpringView进入拉动更新的状态
   */
  fun callFresh() {
    _callFresh()
  }

  @JvmOverloads
  fun callFreshDelay(delay: Int = 100) {
    mHandler.postDelayed({ callFresh() }, delay.toLong())
  }

  fun setMoveTime(time: Int) {
    this.MOVE_TIME = time
  }

  fun setMoveTimeOver(time: Int) {
    this.MOVE_TIME_OVER = time
  }

  /**
   * 设置监听
   */
  fun setListener(listener: SpringView.OnFreshListener) {
    this.listener = listener
  }

  /**
   * 动态设置弹性模式
   */
  fun setGive(give: SpringView.Give) {
    this.give = give
  }

  /**
   * 改变样式的对外接口
   */
  fun setType(type: SpringView.Type) {
    if (isTop || isBottom) {
      //如果当前用户正在拖动，直接调用changeType()会造成布局错乱
      //设置needChange标志，在执行完拖动后再调用changeType()
      needChange = true
      //把参数保持起来
      _type = type
    } else {
      changeType(type)
    }
  }

  /**
   * 获取当前样式
   */
  fun getType(): SpringView.Type? {
    return type
  }

  /**
   * 回调接口
   *//*
  interface OnFreshListener {
    *//**
     * 下拉刷新，回调接口
     *//*
    fun onRefresh()

    *//**
     * 上拉加载，回调接口
     *//*
    fun onLoadmore()
  }*/

  private fun setHeaderIn(headerHander: SpringView.DragHander?) {
    this.headerHander = headerHander
    if (headerView != null) {
      removeView(this.headerView)
    }
    headerHander!!.getView(inflater, this)
    this.headerView = getChildAt(childCount - 1)
    contentView!!.bringToFront() //把内容放在最前端
    requestLayout()
  }

  private fun setFooterIn(footerHander: SpringView.DragHander?) {
    this.footerHander = footerHander
    if (footerView != null) {
      removeView(footerView)
    }
    footerHander!!.getView(inflater, this)
    this.footerView = getChildAt(childCount - 1)
    contentView!!.bringToFront() //把内容放在最前端
    requestLayout()
  }

}
