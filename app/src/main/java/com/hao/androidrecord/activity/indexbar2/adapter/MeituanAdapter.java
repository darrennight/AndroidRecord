package com.hao.androidrecord.activity.indexbar2.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.indexbar2.model.MeiTuanBean;
import com.hao.androidrecord.activity.indexbar2.utils.CommonAdapter;
import com.hao.androidrecord.activity.indexbar2.utils.ViewHolder;

import java.util.List;


/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class MeituanAdapter extends CommonAdapter<MeiTuanBean> {
    public MeituanAdapter(Context context, int layoutId, List<MeiTuanBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, MeiTuanBean meiTuanBean) {
        holder.setText(R.id.tvCity, meiTuanBean.getCity());
    }


}
