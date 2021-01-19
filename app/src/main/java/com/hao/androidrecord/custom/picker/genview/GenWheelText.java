/* * Copyright (C) 2010 The Android Open Source Project * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *      http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */
package com.hao.androidrecord.custom.picker.genview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hao.androidrecord.R;


public class GenWheelText extends GenWheelView {
    private int line = 1;
    private int textSize = 24;
    private int textColor;

    public GenWheelText() {
        this(1, 24, 0x444444);
    }

    public GenWheelText(int textColor) {
        this(1, 24, textColor);
    }

    public GenWheelText(int line, int textSize, int textColor) {
        this.line = line;
        this.textSize = textSize;
        this.textColor = textColor;
    }

    @Override
    protected View genBody(Context context, View convertView, Object element, int position) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.cbk_wheel_default_inner_text, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);// 投資代碼_投資標的名稱
            convertView.setTag(holder);
        }
        holder.text.setTextSize(textSize);
        holder.text.setMaxLines(line);
        holder.text.setText(element.toString());
        holder.text.setTextColor(textColor);
        return convertView;
    }

    private class ViewHolder {
        public TextView text;
    }

}
