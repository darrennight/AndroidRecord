package com.hao.androidrecord.activity.autoscroll

import android.graphics.PointF
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.hao.androidrecord.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.activity_auto_scroll_recycle.*
import java.util.concurrent.TimeUnit

class AutoScrollRecycleActivity : AppCompatActivity() {

    var mAutoTask: Disposable? = null
    lateinit var mSmoothScroll: LinearSmoothScroller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_scroll_recycle)

        var data = arrayListOf<String>()
        for (i in 1..15) {
            data.add("测试数据$i")
        }

        mRv.layoutManager = LinearLayoutManager(this)
        mRv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRv.adapter = ItemAutoAdapter(this,data)

        //自定义LinearSmoothScroller，重写方法，滚动item至顶部，控制滚动速度
        mSmoothScroll = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int {
                return LinearSmoothScroller.SNAP_TO_START
            }

            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                // 移动一英寸需要花费3ms
                return 3f / (displayMetrics?.density ?: 1f)
            }

        }

        mRvTwo.layoutManager = LinearLayoutManager(this)
        mRvTwo.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRvTwo.adapter = ItemAutoAdapter(this,data)

    }

    override fun onStart() {
        super.onStart()
        startAuto()
        mRvTwo.start()
    }

    override fun onStop() {
        super.onStop()
        stopAuto()
        mRvTwo.stop()
    }

    fun startAuto() {
        if (mAutoTask != null && !(mAutoTask?.isDisposed ?: true))
            mAutoTask?.dispose()

        mAutoTask = Observable.interval(1, 2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
            //定位到指定项如果该项可以置顶就将其置顶显示
            mSmoothScroll.targetPosition = it.toInt()
            (mRv.layoutManager as LinearLayoutManager).startSmoothScroll(mSmoothScroll)
        }
    }

    fun stopAuto() {
        if (mAutoTask != null && (mAutoTask?.isDisposed ?: true)) {
            mAutoTask?.dispose()
            mAutoTask = null
        }
    }
}
