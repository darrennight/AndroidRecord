package com.hao.androidrecord.custom.selector.internal.ui.adapter;

import android.database.Cursor;
import android.provider.MediaStore;

import androidx.recyclerview.widget.RecyclerView;


import com.hao.androidrecord.custom.selector.internal.entity.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder> extends
        RecyclerView.Adapter<VH> {

    private Cursor mCursor;
    private int mRowIDColumn;
    private List<Item> list;

    RecyclerViewCursorAdapter(Cursor c) {
        //notifyDataSetChanged时导致图片闪烁 使用下面这句解决 但是需要 重写getItemId 去掉super
        setHasStableIds(true);
        swapCursor(c);
        list = new ArrayList<>();
    }

    protected abstract void onBindViewHolder(VH holder, Cursor cursor);
    protected void onBindViewHolder(VH holder, Cursor cursor,int position){}
    protected void onBindViewHolderItem(VH holder, Item item,int position){}

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (position == 0){
            onBindViewHolderItem(holder, null,position);
        }else {
            onBindViewHolderItem(holder, list.get(position-1),position);
        }

    }

    @Override
    public int getItemCount() {
        if (mCursor == null){
            return list.size()+1;
        }
        if (isDataValid(mCursor)) {
            return mCursor.getCount();
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        //最好使用item的id 否则在使用notifyItemRangeChanged等方法时还会出现复用问题
        return position;
    }

    protected abstract int getItemViewType(int position, Cursor cursor);


    public void swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return;
        }

        if (newCursor != null) {
            mCursor = newCursor;
            mRowIDColumn = mCursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            notifyItemRangeRemoved(0, getItemCount());
            mCursor = null;
            mRowIDColumn = -1;
        }
    }

    public void initData(List<Item> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public Cursor getCursor() {
        return mCursor;
    }

    private boolean isDataValid(Cursor cursor) {
        return cursor != null && !cursor.isClosed();
    }
}