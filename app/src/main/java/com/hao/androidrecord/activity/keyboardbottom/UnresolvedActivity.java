package com.hao.androidrecord.activity.keyboardbottom;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.keyboardbottom.common.Message;
import com.hao.androidrecord.activity.keyboardbottom.common.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class UnresolvedActivity extends AppCompatActivity {

    private EditText et_inputMessage;

    private LinearLayout ll_rootEmojiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unresolved);
        initView();
    }

    private void initView() {
        RecyclerView rv_messageList = (RecyclerView) findViewById(R.id.rv_messageList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_messageList.setLayoutManager(linearLayoutManager);
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("1"));
        messageList.add(new Message("2"));
        messageList.add(new Message("3"));
        messageList.add(new Message("4"));
        messageList.add(new Message("5"));
        messageList.add(new Message("6"));
        messageList.add(new Message("7"));
        messageList.add(new Message("8"));
        messageList.add(new Message("9"));
        messageList.add(new Message("10"));
        messageList.add(new Message("11"));
        messageList.add(new Message("12"));
        messageList.add(new Message("13"));
        messageList.add(new Message("14"));
        messageList.add(new Message("15"));
        messageList.add(new Message("16"));
        messageList.add(new Message("17"));
        messageList.add(new Message("18"));
        messageList.add(new Message("19"));
        messageList.add(new Message("20"));
        MessageAdapter messageAdapter = new MessageAdapter(this, messageList, R.layout.item_message_key);
        rv_messageList.setAdapter(messageAdapter);

        et_inputMessage = (EditText) findViewById(R.id.et_inputMessage);
        ImageView iv_more = (ImageView) findViewById(R.id.iv_more);
        ll_rootEmojiPanel = (LinearLayout) findViewById(R.id.ll_rootEmojiPanel);
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_rootEmojiPanel.getVisibility() == View.VISIBLE) {
                    showKeyboard();
                } else {
                    hideKeyboard();
                    ll_rootEmojiPanel.setVisibility(View.VISIBLE);
                }
            }
        });
        et_inputMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    ll_rootEmojiPanel.setVisibility(View.GONE);
                }
            }
        });
        rv_messageList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    hideKeyboard();
                    ll_rootEmojiPanel.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    private void showKeyboard() {
        et_inputMessage.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) et_inputMessage.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et_inputMessage, 0);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        et_inputMessage.clearFocus();
        inputMethodManager.hideSoftInputFromWindow(et_inputMessage.getWindowToken(), 0);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            if (ll_rootEmojiPanel.getVisibility() == View.VISIBLE) {
//                ll_rootEmojiPanel.setVisibility(View.GONE);
//                return true;
//            }
//        }
        return super.dispatchKeyEvent(event);
    }

}
