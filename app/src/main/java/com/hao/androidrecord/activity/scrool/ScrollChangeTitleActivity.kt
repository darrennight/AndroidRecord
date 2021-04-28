package com.hao.androidrecord.activity.scrool

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.nestedscrolling.fragment.DataBean
import com.hao.androidrecord.activity.nestedscrolling.fragment.NestedScrollTestRecyclerViewAdapter
import com.hao.androidrecord.util.ViewUtils
import com.hao.androidrecord.util.barlib.bar.StateAppBar
import com.hao.androidrecord.util.barlib.utils.StatusBarUtils
import kotlinx.android.synthetic.main.activity_scroll_change.*
import java.util.*

class ScrollChangeTitleActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_change)
//        setStatusColor()
//        if (onStatusColor() == ContextCompat.getColor(this, R.color.color_FFFFFF)){
//            setStatusBackgoundLight()
//        }

//        StateAppBar.translucentStatusBar(this, true)
//        StatusBarUtils.StatusBarLightMode(this)

        StateAppBar.translucentStatusBar(this,false)


        nsp_layout_scroll.setHeader(view_header)
        val distance = ViewUtils.dp2px(300f)-ViewUtils.dp2px(81f)
        nsp_layout_scroll.setLayoutScrollListener {
            if (it >= distance){

                layout_title.setBackgroundColor(ContextCompat.getColor(this,R.color.black))


            }else{
                layout_title.setBackgroundColor(ContextCompat.getColor(this,R.color.color_transparent))
            }
        }
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val dataBeans = ArrayList<DataBean>()
        dataBeans.add(
            DataBean(
                1.toString() + "TextView",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2650138538,1827686917&fm=15&gp=0.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                2.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395993&di=16f2a6878f4b5d76e2122b008b80da0e&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn11%2F266%2Fw1600h1066%2F20180318%2F8390-fyshfur1533535.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                3.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395993&di=80f6dd0dbd89d64a282b7e779d188177&imgtype=0&src=http%3A%2F%2Fhimg2.huanqiu.com%2Fattachment2010%2F2013%2F0711%2F20130711013802170.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                4.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395992&di=eeee6904d7a12ea9b0a9a7bd004ef5d7&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1510%2F28%2Fc15%2F14555696_1446001070504_mthumb.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                5.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395992&di=5043a645040c6b3d3cdc9116e50ef5ab&imgtype=0&src=http%3A%2F%2Fi0.sinaimg.cn%2Fty%2F2014%2F1204%2FU11648P6DT20141204190014.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                6.toString() + "TextView",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2650138538,1827686917&fm=15&gp=0.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                7.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395993&di=16f2a6878f4b5d76e2122b008b80da0e&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn11%2F266%2Fw1600h1066%2F20180318%2F8390-fyshfur1533535.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                8.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395993&di=80f6dd0dbd89d64a282b7e779d188177&imgtype=0&src=http%3A%2F%2Fhimg2.huanqiu.com%2Fattachment2010%2F2013%2F0711%2F20130711013802170.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                9.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395992&di=eeee6904d7a12ea9b0a9a7bd004ef5d7&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1510%2F28%2Fc15%2F14555696_1446001070504_mthumb.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                10.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395992&di=5043a645040c6b3d3cdc9116e50ef5ab&imgtype=0&src=http%3A%2F%2Fi0.sinaimg.cn%2Fty%2F2014%2F1204%2FU11648P6DT20141204190014.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                11.toString() + "TextView",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2650138538,1827686917&fm=15&gp=0.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                12.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395993&di=16f2a6878f4b5d76e2122b008b80da0e&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn11%2F266%2Fw1600h1066%2F20180318%2F8390-fyshfur1533535.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                13.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395993&di=80f6dd0dbd89d64a282b7e779d188177&imgtype=0&src=http%3A%2F%2Fhimg2.huanqiu.com%2Fattachment2010%2F2013%2F0711%2F20130711013802170.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                14.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395992&di=eeee6904d7a12ea9b0a9a7bd004ef5d7&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1510%2F28%2Fc15%2F14555696_1446001070504_mthumb.jpg"
            )
        )
        dataBeans.add(
            DataBean(
                15.toString() + "TextView",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582440395992&di=5043a645040c6b3d3cdc9116e50ef5ab&imgtype=0&src=http%3A%2F%2Fi0.sinaimg.cn%2Fty%2F2014%2F1204%2FU11648P6DT20141204190014.jpg"
            )
        )
        rv_item_scroll.setLayoutManager(LinearLayoutManager(this))
        rv_item_scroll.setAdapter(NestedScrollTestRecyclerViewAdapter(this, dataBeans))
    }

    override fun onResume() {
        super.onResume()

    }
    fun setStatusBackgoundLight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun setStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //状态栏背景颜色修改只支持5.0以上
            window.statusBarColor = onStatusColor()
        }

    }
    fun onStatusColor(): Int {
        return  ContextCompat.getColor(this, R.color.color_transparent)
    }
}