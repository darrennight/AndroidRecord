package com.hao.androidrecord.activity.scrollMove.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hao.androidrecord.R;
import com.lihang.nbadapter.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * Created by leo
 * on 2019/11/8.
 */
public class MessageAdapter extends BaseAdapter<Integer> {


    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup, int viewType) {

        return new MessageHolder((LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, viewGroup, false)));
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MessageHolder baseViewHolder = (MessageHolder) viewHolder;
        Integer itemBean = dataList.get(i);
        baseViewHolder.imageMessage.setImageResource(itemBean);

    }

    public class MessageHolder extends RecyclerView.ViewHolder{
        public ImageView imageMessage;
        public MessageHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageMessage = itemView.findViewById(R.id.image_message);
        }


    }
}
