package com.hao.androidrecord.activity.pinned3;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PinnedRecyclerView extends RecyclerView {

    public PinnedRecyclerView(Context context) {
        super(context);
        setDefaultLayoutManager();
    }

    public PinnedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setDefaultLayoutManager();
    }

    public PinnedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDefaultLayoutManager();
    }

    private void setDefaultLayoutManager() {
        setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (getLayoutManager() == null) {
            super.setLayoutManager(layout);
        }
    }

    @Override
    public LayoutManager getLayoutManager() {
        return super.getLayoutManager();
    }
}
