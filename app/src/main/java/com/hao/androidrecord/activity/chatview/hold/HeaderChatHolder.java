package com.hao.androidrecord.activity.chatview.hold;

import android.view.View;
import android.widget.TextView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.chatview.chatlib.AppUtils;
import com.hao.androidrecord.activity.chatview.chatlib.BaseChatViewHolder;

/**
 * 头部信息
 * @author RyanLee
 */
public class HeaderChatHolder extends BaseChatViewHolder {

    public HeaderChatHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object obj, int position) {
        TextView tips = (TextView) getView(R.id.tv_header_text_msg);
        tips.setText(AppUtils.getContext().getResources().getString(R.string.test_healthy_live));
    }
}
