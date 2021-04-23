package com.hao.androidrecord.indexable.adapter;


import com.hao.androidrecord.indexable.model.City;

public interface OnPickListener {
    void onPick(int position, City data);
    void onLocate();
    void onCancel();
}
