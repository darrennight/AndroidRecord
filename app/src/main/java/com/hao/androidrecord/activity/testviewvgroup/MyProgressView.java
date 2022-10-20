package com.hao.androidrecord.activity.testviewvgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyProgressView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int textPadding = 5;
    private int progress = 0;

    public MyProgressView(Context context) {
        super(context);
        initPaint();
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(14);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    Rect rect = new Rect();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = progress + "%";
        float textWidth = mPaint.measureText(text) + textPadding;
//        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        mPaint.setColor(Color.BLUE);

        canvas.drawLine(0, mHeight / 2, progress * ((mWidth - textWidth) / 100), mHeight / 2, mPaint);
        canvas.drawText(text, progress * ((mWidth - textWidth) / 100) + textPadding, (mHeight - rect.height()) / 2 + 2 * textPadding, mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(progress * ((mWidth - textWidth) / 100) + textWidth + textPadding, mHeight / 2, mWidth, mHeight / 2, mPaint);
    }

    public void setProgress(int progress) {
        if (progress > 100) {
            progress = 100;
        } else if (progress < 0) {
            progress = 0;
        }
        this.progress = progress;
        postInvalidate();
    }
}