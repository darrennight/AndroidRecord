package com.hao.androidrecord.custom

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.webkit.WebView
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * TODO
 *
 * @author zenghao
 * @since 2018/6/13 上午11:32
 */
class ScrollableHelper {
  private var mCurrentScrollableCainer: ScrollableHelper.ScrollableContainer? = null

  private val scrollableView: View?
    get() = mCurrentScrollableCainer?.getScrollableView()

  /**
   *
   * 判断是否滑动到顶部方法,ScrollAbleLayout根据此方法来做一些逻辑判断
   * 目前只实现了AdapterView,ScrollView,RecyclerView
   * 需要支持其他view可以自行补充实现
   * @return
   */
  fun isTop(): Boolean{

    if (scrollableView == null){
      return true
    }else{
      var scrollableView = scrollableView
      if (scrollableView is AdapterView<*>) {
        return isAdapterViewTop(scrollableView)
      }
      if (scrollableView is ScrollView) {
        return isScrollViewTop(scrollableView)
      }
      if (scrollableView is RecyclerView) {
        return isRecyclerViewTop(scrollableView)
      }
      if (scrollableView is WebView) {
        return isWebViewTop(scrollableView)
      }
    }

    throw IllegalStateException(
        "scrollableView must be a instance of AdapterView|ScrollView|RecyclerView")
  }


  /**
   * a viewgroup whitch contains ScrollView/ListView/RecycelerView..
   */
  interface ScrollableContainer {
    /**
     * @return ScrollView/ListView/RecycelerView..'s instance
     */
   open fun getScrollableView(): View
  }

  fun setCurrentScrollableContainer(scrollableContainer: ScrollableHelper.ScrollableContainer) {
    this.mCurrentScrollableCainer = scrollableContainer
  }

  private fun isRecyclerViewTop(recyclerView: RecyclerView?): Boolean {
    if (recyclerView != null) {
      val layoutManager = recyclerView.layoutManager
      if (layoutManager is LinearLayoutManager) {
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val childAt = recyclerView.getChildAt(0)
        if (childAt == null || firstVisibleItemPosition == 0 && childAt.top == 0) {
          return true
        }
      }
    }
    return false
  }

  private fun isAdapterViewTop(adapterView: AdapterView<*>?): Boolean {
    if (adapterView != null) {
      val firstVisiblePosition = adapterView.firstVisiblePosition
      val childAt = adapterView.getChildAt(0)
      if (childAt == null || firstVisiblePosition == 0 && childAt.top == 0) {
        return true
      }
    }
    return false
  }

  private fun isScrollViewTop(scrollView: ScrollView?): Boolean {
    if (scrollView != null) {
      val scrollViewY = scrollView.scrollY
      return scrollViewY <= 0
    }
    return false
  }

  private fun isWebViewTop(scrollView: WebView?): Boolean {
    if (scrollView != null) {
      val scrollViewY = scrollView.scrollY
      return scrollViewY <= 0
    }
    return false
  }

  @SuppressLint("NewApi")
  fun smoothScrollBy(velocityY: Int, distance: Int, duration: Int) {
    val scrollableView = scrollableView
    if (scrollableView is AbsListView) {
      val absListView = scrollableView as AbsListView?
      if (Build.VERSION.SDK_INT >= 21) {
        absListView!!.fling(velocityY)
      } else {
        absListView!!.smoothScrollBy(distance, duration)
      }
    } else (scrollableView as? ScrollView)?.fling(
        velocityY) ?: ((scrollableView as? RecyclerView)?.fling(0,
        velocityY) ?: (scrollableView as? WebView)?.flingScroll(0, velocityY))
  }

}
