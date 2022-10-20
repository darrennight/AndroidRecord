package com.hao.androidrecord.activity.chatview.hold;

import android.view.View;
import android.widget.TextView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.chatview.MyChatMsg;
import com.hao.androidrecord.activity.chatview.chatlib.BaseChatViewHolder;

/**
 * 系统消息
 * @author RyanLee
 */
public class SystemNewsHolder extends BaseChatViewHolder {
    public SystemNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object obj, int position) {
        MyChatMsg data = (MyChatMsg) obj;
        TextView tips = (TextView) getView(R.id.tv_system_news_msg);
        tips.setText(data.systemNews);
    }
}
