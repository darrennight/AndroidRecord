package com.hao.androidrecord.activity.scrollable

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.R
import com.hao.androidrecord.adapter.DemoAdapter
import com.hao.androidrecord.custom.ScrollableHelper
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import kotlinx.android.synthetic.main.activity_scrollable_02.*

class HeaderAndTablayoutRV02:AppCompatActivity() , ScrollableHelper.ScrollableContainer{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrollable_02)


        layout_scrollable.helper?.setCurrentScrollableContainer(this)

        spring_index_load.header = DefaultHeader(this)
        spring_index_load.footer = DefaultFooter(this)



        val managerHouse = LinearLayoutManager(this)
        managerHouse.orientation = LinearLayoutManager.VERTICAL
        rv_order_house_list.layoutManager = managerHouse
        rv_order_house_list.itemAnimator = DefaultItemAnimator()
        rv_order_house_list.itemAnimator?.changeDuration = 0

//        val controllerHouse = AnimationUtils.loadLayoutAnimation(context,
//            R.anim.layout_animation_from_bottom)
//        rv_order_house_list.layoutAnimation = controllerHouse
//        rv_order_house_list.scheduleLayoutAnimation()

        val list = ArrayList<String>()
        for (i in 0..20){
            list.add("$i")
        }
        rv_order_house_list.adapter = DemoAdapter(this,list)

    }

    override fun getScrollableView(): View {
        return rv_order_house_list
    }
}