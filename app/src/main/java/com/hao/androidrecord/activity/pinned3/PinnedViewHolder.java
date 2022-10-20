package com.hao.androidrecord.activity.pinned3;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;

public class PinnedViewHolder extends RecyclerView.ViewHolder {
    protected View viewPin;

    public PinnedViewHolder(View itemView) {
        super(itemView);
        viewPin = itemView.findViewById(R.id.fixed_pin_id);
    }
}
