package com.hao.androidrecord.activity.scrollMove.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.lihang.nbadapter.BaseAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * Created by leo
 * on 2019/12/5.
 */
public class CommentsAdapter extends BaseAdapter<String> {
    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup, int viewType) {
        return new CommentsHolder((LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comments, viewGroup, false)));
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }


    public class CommentsHolder extends RecyclerView.ViewHolder{

        public CommentsHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }


    }

}
