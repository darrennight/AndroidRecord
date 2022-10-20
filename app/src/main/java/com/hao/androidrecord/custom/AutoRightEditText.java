package com.hao.androidrecord.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Rex on 2019/1/3.
 */
 
public class AutoRightEditText extends androidx.appcompat.widget.AppCompatEditText {
 
    public boolean isFirstOnClick = true;
    private boolean isShowEnd;
 
    public AutoRightEditText(Context context) {
        super(context);
        initView();
    }
 
 
    public AutoRightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
 
    public AutoRightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
 
    private void initView() {
        setClickable(true);
        /**
         * 如果你本身使用的该方法记得 !hasFocus--->isFirstOnClick = true
         */
 
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isFirstOnClick = true;
                }
            }
        });
    }
 
    @Override
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);
 
        if (isFirstOnClick && isPressed()) {
            isShowEnd = true;
            int len = getText().length();
            setSelection(len);
        } else {
            isShowEnd = false;
            isFirstOnClick = false;
        }
 
    }
 
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (isShowEnd && selStart == selEnd) {
            setSelection(getText().length());
        }
        
 
    }
 
 
}