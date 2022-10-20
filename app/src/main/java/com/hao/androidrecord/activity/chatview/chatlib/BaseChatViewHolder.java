package com.hao.androidrecord.activity.chatview.chatlib;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author RyanLee
 */
public abstract class BaseChatViewHolder extends RecyclerView.ViewHolder implements IChatHolder {
    @NonNull
    private SparseArray<View> mViews;


    public BaseChatViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    private View findViewById(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    protected View getView(@IdRes int viewId) {
        return findViewById(viewId);
    }
}
