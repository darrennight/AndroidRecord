package com.hao.androidrecord.indexable.adapter;


import com.hao.androidrecord.indexable.model.City;

public interface InnerListener {
    void dismiss(int position, City data);
    void locate();
}
