package com.hao.androidrecord.activity.marqueeView.lib1.util;

import android.view.View;

/**
 * Created by GongWen on 18/1/8.
 */

public interface OnItemClickListener<V extends View, E> {
    void onItemClickListener(V mView, E mData, int mPosition);
}
