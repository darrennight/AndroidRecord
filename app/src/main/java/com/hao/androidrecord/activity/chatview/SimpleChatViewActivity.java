package com.hao.androidrecord.activity.chatview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.chatview.chatlib.SimpleChatView;

/**
 * @author RyanLee
 */
public class SimpleChatViewActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleChatView<MyChatMsg, SimpleChatAdapter> mChatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_chat);

        initView();
    }

    private void initView() {
        // 随机发送单条消息按钮
        FloatingActionButton mSingleMsgBtn = findViewById(R.id.fab_single_message);
        mSingleMsgBtn.setOnClickListener(this);

        // 随机发送多条消息按钮
        FloatingActionButton mMultiMsgBtn = findViewById(R.id.fab_multi_message);
        mMultiMsgBtn.setOnClickListener(this);

        mChatView = findViewById(R.id.chat);

        SimpleChatAdapter adapter = new SimpleChatAdapter(null);
        mChatView
                // 设置Adapter
                .setAdapter(adapter)
                // 设置缓冲时间50ms
                .setBufferTime(50)
                // 最后调用setUp
                .setUp();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_single_message:
                mChatView.sendSingleMsg(TestUtils.getRandomMsg());
                break;
            case R.id.fab_multi_message:
                mChatView.sendMultiMsg(TestUtils.getRandomMsgList(10));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mChatView != null) {
            mChatView.release();
        }
    }
}
