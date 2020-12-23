package com.hao.androidrecord.custom;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;

import com.hao.androidrecord.R;


public class VideoDesDialog extends AppCompatDialog {
    private Context mContext;
    private InputMethodManager imm;
    private EditText messageTextView;
    private RelativeLayout rlDlg;
    private int mLastDiff = 0;
    private TextView tvNumber;
    private int maxNumber = 200;

    public interface OnTextSendListener {

        void onTextSend(String msg);

        void dismiss(String msg);
    }

    private OnTextSendListener mOnTextSendListener;

    public VideoDesDialog(@NonNull Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        this.getWindow().setWindowAnimations(R.style.main_menu_animstyle);
        init();
        setLayout();
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }



    /**
     * 设置输入提示文字
     */
    public void setHint(String text) {
        messageTextView.setHint(text);
    }
    public void setMsgText(String text){
        messageTextView.setText(text);
    }

    private void init() {
        setContentView(R.layout.dialog_video_des);
        messageTextView = findViewById(R.id.et_input_message);
        tvNumber = findViewById(R.id.tv_edit_count);

        final LinearLayout rldlgview =  findViewById(R.id.rl_inputdlg_view);
        messageTextView.setFilters(new InputFilter[]{new RemarkFilter(60)});
        messageTextView.requestFocus();
        messageTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int length = editable.toString().length();
                SpannableString spannableString = new SpannableString(length+"/60");
                ForegroundColorSpan countColorSpan = new ForegroundColorSpan(ContextCompat.getColor(getContext(),R.color.color_white));
                spannableString.setSpan(countColorSpan,0,spannableString.length()-3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                tvNumber.setText(spannableString);
            }
        });

        imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);


        /*messageTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        Toast.makeText(mContext, "请输入文字", Toast.LENGTH_LONG).show();
                        VideoDesDialog.this.dismiss();
                        *//*if (messageTextView.getText().length() > maxNumber) {
                            Toast.makeText(mContext, "超过最大字数限制", Toast.LENGTH_LONG).show();
                            return true;
                        }
                        if (messageTextView.getText().length() > 0) {
                            imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                            VideoDesDialog.this.dismiss();
                        } else {
                            Toast.makeText(mContext, "请输入文字", Toast.LENGTH_LONG).show();
                        }*//*
                        return true;
                    case KeyEvent.KEYCODE_BACK:
                        VideoDesDialog.this.dismiss();
                        return false;
                    default:
                        return false;
                }
            }
        });*/

        messageTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int id, KeyEvent keyEvent) {
                if (id == KeyEvent.KEYCODE_ENTER || id == KeyEvent.KEYCODE_BACK){
                    VideoDesDialog.this.dismiss();
                    return true;
                }

                return false;
            }
        });

        rlDlg = findViewById(R.id.rl_outside_view);
        /*rlDlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() != R.id.rl_inputdlg_view)
                    InputTextMsgDialog.this.dismiss();
            }
        });*/


        rldlgview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                Rect r = new Rect();
                //获取当前界面可视部分
                VideoDesDialog.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = VideoDesDialog.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;

                if (heightDifference <= 0 && mLastDiff > 0) {
                    VideoDesDialog.this.dismiss();
                }
                mLastDiff = heightDifference;
            }
        });
        /*rldlgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                InputTextMsgDialog.this.dismiss();
            }
        });*/

        /*setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    InputTextMsgDialog.this.dismiss();
                return false;
            }
        });*/
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    public void setmOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.mOnTextSendListener = onTextSendListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //dismiss之前重置mLastDiff值避免下次无法打开
        mLastDiff = 0;
        if (mOnTextSendListener!=null) {
            String msg = messageTextView.getText().toString().trim();
            mOnTextSendListener.dismiss(msg);
        }

    }

    @Override
    public void show() {
        super.show();
    }


}
