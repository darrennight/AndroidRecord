package com.hao.androidrecord.activity.chatview.hold;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
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
 * 送礼的Holder
 *
 * @author RyanLee
 */
public class GiftNewsHolder extends BaseChatViewHolder {

    public GiftNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object obj, int position) {
        MyChatMsg data = (MyChatMsg) obj;
        TextView tips = (TextView) getView(R.id.tv_gift_msg);
        String strTo = AppUtils.getContext().getResources().getString(R.string.str_to);
        String strSendTo = AppUtils.getContext().getResources().getString(R.string.str_send_to);

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

        builder.append(data.sendUserName).append(strTo).append(strSendTo).append(data.giftName);
        int leftLen = builder.length();
        builder.append(" ").append(data.giftNum);

        int imageNewSize = DensityUtils.dp2px( 20);
        CenteredImageSpan imageSpan = new CenteredImageSpan(AppUtils.getContext(), BitmapUtils.decodeResToBitmap(data.giftRes, imageNewSize, imageNewSize));
        builder.setSpan(imageSpan, leftLen, leftLen + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int totalLen = builder.length();
        // 设置送礼信息颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#368CFD"));
        builder.setSpan(colorSpan, data.sendUserName.length() + strTo.length(), totalLen, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //设置用户名颜色
        colorSpan = new ForegroundColorSpan(ContextCompat.getColor(AppUtils.getContext(), R.color.color_chat_username));
        builder.setSpan(colorSpan, 0, data.sendUserName.length() + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tips.setText(builder);
    }

}
