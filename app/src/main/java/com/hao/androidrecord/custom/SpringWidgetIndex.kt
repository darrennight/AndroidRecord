package com.hao.androidrecord.custom

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.ViewCompat

/**
 * 房态首页下拉刷新加载更多控件
 * @author zenghao
 * @since 2018/6/12 上午11:37
 */
class SpringWidgetIndex : SpringWidget {

  var mContainLayout: ScrollableLayout? = null
  var mScrollContain: ScrollableHelper.ScrollableContainer? = null

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)


  override fun isChildScrollToTop(): Boolean {


    return (mContainLayout?.isHeadTop?:false) && !ViewCompat.canScrollVertically(mScrollContain?.getScrollableView(), -1)
  }

  override fun isChildScrollToBottom(): Boolean {
    return !ViewCompat.canScrollVertically(mScrollContain?.getScrollableView(), 1)
  }
}