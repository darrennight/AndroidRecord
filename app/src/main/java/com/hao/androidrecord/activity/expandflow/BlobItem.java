/*
 * Copyright (C) 2016 Maxim Alov <alovmax@yandex.ru>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hao.androidrecord.activity.expandflow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hao.androidrecord.R;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

public class BlobItem extends FrameLayout {

  View mRootView;
  ShapeTextView mTextView;
  ShapeLinearLayout layout;
  ImageView logo;
  int position = -1;
  private boolean isClickSelect = false;
  private OnItemCheckListener onItemCheckListener;
  public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener){
    this.onItemCheckListener = onItemCheckListener;
  }
  
  public BlobItem(Context context,int position) {
    this(context, null);
    this.position = position;
  }
  
  public BlobItem(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }
  
  public BlobItem(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    
    mRootView = LayoutInflater.from(context).inflate(R.layout.blob, this, true);
    mTextView = (ShapeTextView) mRootView.findViewById(R.id.text);
    layout = (ShapeLinearLayout) mRootView.findViewById(R.id.stv_layout);
    logo = (ImageView) mRootView.findViewById(R.id.iv_logo);

    layout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isClickSelect){
          unselectItem();
          if (onItemCheckListener!=null){
            onItemCheckListener.onUnchecked(mTextView.getText().toString());
          }
        }else {
          setSelectItem();
          if (onItemCheckListener!=null){
            onItemCheckListener.onChecked(mTextView.getText().toString());
          }
        }
        isClickSelect = !isClickSelect;


      }
    });

  }

  public void setSelectItem(){
    mTextView.setSelected(true);
    layout.setSelected(true);
    logo.setVisibility(View.VISIBLE);
  }

  private void unselectItem(){
    mTextView.setSelected(false);
    layout.setSelected(false);
    logo.setVisibility(View.GONE);
  }
  
  public void setText(String text) {
    mTextView.setText(text);
  }

  interface OnItemCheckListener{
    public void onChecked(String txt);
    public void onUnchecked(String txt);
  }
}