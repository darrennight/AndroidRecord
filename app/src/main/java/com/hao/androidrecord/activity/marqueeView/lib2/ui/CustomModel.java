package com.hao.androidrecord.activity.marqueeView.lib2.ui;

import com.hao.androidrecord.activity.marqueeView.lib2.IMarqueeItem;

/**
 * @author by sunfusheng on 2019-04-25
 */
public class CustomModel implements IMarqueeItem {

    public int id;
    public String title;
    public String content;

    public CustomModel(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public CharSequence marqueeMessage() {
        return title + "\n" + content;
    }
}
