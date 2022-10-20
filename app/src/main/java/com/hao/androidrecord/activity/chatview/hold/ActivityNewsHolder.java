package com.hao.androidrecord.activity.chatview.hold;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.chatview.MyChatMsg;
import com.hao.androidrecord.activity.chatview.chatlib.AppUtils;
import com.hao.androidrecord.activity.chatview.chatlib.BaseChatViewHolder;
import com.hao.androidrecord.activity.chatview.chatlib.DensityUtils;

/**
 * @author RyanLee
 */
public class ActivityNewsHolder extends BaseChatViewHolder {

    public ActivityNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object obj, int position) {
        MyChatMsg data = (MyChatMsg) obj;
        TextView textView = (TextView) getView(R.id.tv_activity_news);
        Drawable drawableLeft = ContextCompat.getDrawable(AppUtils.getContext(), R.drawable.ic_activity);
        Drawable drawableRight = ContextCompat.getDrawable(AppUtils.getContext(), R.drawable.ic_arrow);
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, DensityUtils.dp2px(18), DensityUtils.dp2px(18));
        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, DensityUtils.dp2px(12), DensityUtils.dp2px(12));
        }
        textView.setCompoundDrawables(drawableLeft, null, drawableRight, null);
        textView.setText(data.activityNews);
    }
}
