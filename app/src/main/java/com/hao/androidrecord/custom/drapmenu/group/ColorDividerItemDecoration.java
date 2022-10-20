package com.hao.androidrecord.custom.drapmenu.group;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.util.ViewUtils;

/**
 * Created by frank on 2017/4/12.
 */

public class ColorDividerItemDecoration extends RecyclerView.ItemDecoration {

    private float mDividerHeight;

    private Paint mPaint;

    public ColorDividerItemDecoration() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int po = parent.getChildAdapterPosition(view);
        if(po % 2 == 0){
            int Offsets = Offsets = ViewUtils.INSTANCE.dp2px(40);;

            outRect.set(0, Offsets, 0, 0);
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawLetterToItemLeft(c,parent);

    }


    private void drawLetterToItemLeft(Canvas c, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            int position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() + i;
            View child = parent.getChildAt(i);
            int left = 0;
            int top = child.getTop() - ViewUtils.INSTANCE.dp2px(40);
            int right = child.getRight();
            int bottom = child.getBottom() - child.getMeasuredHeight();
            Rect targetRect = new Rect(left, top, right, bottom);
            mPaint.setColor(Color.RED);

            if (position == 0) {
                mPaint.setColor(Color.parseColor("#eaeaea"));
                c.drawRect(targetRect, mPaint);

            } else {
                mPaint.setColor(Color.parseColor("#eaeaea"));
                c.drawRect(targetRect, mPaint);
            }

        }
    }
}
