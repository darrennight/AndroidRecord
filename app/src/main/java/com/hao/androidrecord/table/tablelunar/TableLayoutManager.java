package com.hao.androidrecord.table.tablelunar;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created By: sqq
 * Created Time: 16/12/26 下午12:41.
 */
public class TableLayoutManager extends RecyclerView.LayoutManager {

    public TableLayoutManager() {
        //使得RecyclerView 的大小测量方式按照本LayoutManager的方式来计算，一般是为了使得RecyclerView支持WrapContent。
        setAutoMeasureEnabled(true);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {

        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);

        int left, top, right;
        top = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View child = recycler.getViewForPosition(i);
            if (i <= 4) {
                child.getLayoutParams().width = getWidth() / 4;

                addView(child);

                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = i % 4 * width;

                right = left + width;

                top = i / 4 *height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else if (i == 5){

                child.getLayoutParams().width = getWidth() / 2;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = getWidth() / 4;

                right = left + width;

                top = height;

                layoutDecoratedWithMargins(child, left, top, right, top + height*2);

            }else if (i == 6){
                child.getLayoutParams().width = getWidth() / 4;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = width * 3;

                right = left + width;

                top = height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else if (i == 7){
                child.getLayoutParams().width = getWidth() / 4;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = 0;

                right = left + width;

                top = 2*height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else if (i == 8){
                child.getLayoutParams().width = getWidth() / 4;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = width * 3;

                right = left + width;

                top = 2*height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else if (i == 9){
                child.getLayoutParams().width = getWidth() / 4;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = 0;

                right = left + width;
                //三行的高度--底部
                top = 3*height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else if (i==10){
                child.getLayoutParams().width = getWidth() / 2;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = getWidth() / 4;

                right = left + width;

                top = 3*height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else if (i == 11){
                child.getLayoutParams().width = getWidth() / 4;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = 3*width;

                right = left + width;

                top = 3*height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else if (i == 12){
                child.getLayoutParams().width = getWidth() / 4;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = 0;

                right = left + width;

                top = 4*height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }else {
                child.getLayoutParams().width = getWidth() / 16;
                addView(child);
                measureChildWithMargins(child, 0, 0);

                int width = getDecoratedMeasuredWidth(child);
                int height = getDecoratedMeasuredHeight(child);

                left = (getWidth() / 4) + ((i-1) % 12 * width);

                right = left + width;

                top = 4*height;

                layoutDecoratedWithMargins(child, left, top, right, top + height);
            }



//            top += i % 4 *height;
//            top += i % 4 == 0 ? 0 : height;



//            Log.e("===manager","=="+i+"==="+left+"====="+right);
//            Log.e("===manager","=="+i+"==="+(i % 4));
            Log.e("===manager","=="+i+"==="+(i / 4));

        }
    }

    @Override
    public void setAutoMeasureEnabled(boolean enabled) {
        super.setAutoMeasureEnabled(enabled);

    }
}
