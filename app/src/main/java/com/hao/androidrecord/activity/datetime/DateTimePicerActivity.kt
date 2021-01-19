package com.hao.androidrecord.activity.datetime

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.picker.DatePickDialog
import com.hao.androidrecord.custom.picker.bean.DateType


class DateTimePicerActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time_picker)
        ButterKnife.bind(this);
    }

    private fun showDatePickDialog(type: DateType) {
        val dialog = DatePickDialog(this)
        //设置上下年分限制
        dialog.setYearLimt(5)
        //设置标题
        dialog.setTitle("选择时间")
        //设置类型
        dialog.setType(type)
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm")
        //设置选择回调
        dialog.setOnChangeLisener(null)
        //设置点击确定按钮回调
        dialog.setOnSureLisener(null)
        dialog.show()
    }

    @OnClick(R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5)
    fun onClick(view: View) {
        when (view.getId()) {
            R.id.item1 -> showDatePickDialog(DateType.TYPE_ALL)
            R.id.item2 -> showDatePickDialog(DateType.TYPE_YMDHM)
            R.id.item3 -> showDatePickDialog(DateType.TYPE_YMDH)
            R.id.item4 -> showDatePickDialog(DateType.TYPE_YMD)
            R.id.item5 -> showDatePickDialog(DateType.TYPE_HM)
        }
    }
}