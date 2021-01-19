package com.hao.androidrecord.custom.picker.listener;


import com.hao.androidrecord.custom.picker.bean.DateBean;
import com.hao.androidrecord.custom.picker.bean.DateType;

/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
