package com.hao.androidrecord.activity.chatview.hold;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.chatview.MyChatMsg;
import com.hao.androidrecord.activity.chatview.chatlib.AppUtils;
import com.hao.androidrecord.activity.chatview.chatlib.BaseChatViewHolder;
import com.hao.androidrecord.activity.chatview.chatlib.BitmapUtils;
import com.hao.androidrecord.activity.chatview.chatlib.CenteredImageSpan;
import com.hao.androidrecord.activity.chatview.chatlib.DensityUtils;

/**
 * 普通消息
 * @author RyanLee
 */
public class NormalChatHolder extends BaseChatViewHolder {

    public NormalChatHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object obj, int position) {
        MyChatMsg data = (MyChatMsg) obj;
        TextView text = (TextView) getView(R.id.tv_normal_text_msg);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (data.headLight > 0) {
            // 设置头灯
            int resId;
            switch (data.headLight) {
                case MyChatMsg.HEAD_LIGHT_VIP:
                    resId = R.drawable.ic_vip;
                    break;
                case MyChatMsg.HEAD_LIGHT_DIAMOND:
                    resId = R.drawable.ic_diamond;
                    break;
                default:
                    return;
            }
            builder.append(" ");
            int imageNewSize = DensityUtils.dp2px(24);
            CenteredImageSpan vipSpan = new CenteredImageSpan(AppUtils.getContext(), BitmapUtils.decodeResToBitmap(resId, imageNewSize, imageNewSize));
            builder.setSpan(vipSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        builder.append(data.sendUserName).append(AppUtils.getContext().getResources().getString(R.string.str_to));

        // 用户名的长度
        int leftLen = builder.length();
        if (!TextUtils.isEmpty(data.atUserName)) {
            builder.append(AppUtils.getContext().getResources().getString(R.string.str_at)).append(data.atUserName).append(" ");
        }
        // 用户名到@的长度
        int nLeftLen = builder.length();
        builder.append(data.content);
        //设置@用户名的颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(AppUtils.getContext(), R.color.color_chat_at_username));
        builder.setSpan(colorSpan, leftLen, nLeftLen, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置用户名颜色
        colorSpan = new ForegroundColorSpan(ContextCompat.getColor(AppUtils.getContext(), R.color.color_chat_username));
        builder.setSpan(colorSpan, 1, data.sendUserName.length() + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);


        text.setText(builder);
    }
}
