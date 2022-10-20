package com.hao.androidrecord.activity.chatview.chatlib;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author RyanLee
 */
public abstract class BaseChatAdapter<D extends BaseChatMsg> extends RecyclerView.Adapter<BaseChatViewHolder> {

    public abstract void addItem(D chatMsg);

    public abstract void addItemList(List<D> list);

    public abstract void removeItems(int startPos, int endPos);
}
