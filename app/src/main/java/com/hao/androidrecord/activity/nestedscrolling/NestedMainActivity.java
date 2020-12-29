package com.hao.androidrecord.activity.nestedscrolling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.hao.androidrecord.R;


/**
 * 事件分发、滑动冲突入口页面
 *
 * @author hufeiyang
 */
public class NestedMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGoToViewTestActivity;
    Button btnTestTraditionalNestedScroll;
    Button btnTestTraditionalScrollViewRecyclerView;
    Button btnTestTraditionalNestedScrollViewRecyclerView;

    public static void launch(FragmentActivity activity) {
        Intent intent = new Intent(activity, NestedMainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_test_enter);
        btnGoToViewTestActivity = findViewById(R.id.btn_test_nested_scrolling);
        btnTestTraditionalNestedScroll = findViewById(R.id.btn_test_traditional_nested_scroll);
        btnTestTraditionalScrollViewRecyclerView = findViewById(R.id.btn_test_traditional_scroll_view_recycler_view);
        btnTestTraditionalNestedScrollViewRecyclerView = findViewById(R.id.btn_test_traditional_nested_scroll_view_recycler_view);


        findViewById(R.id.btn_test_nested_scrolling).setOnClickListener(this);
        findViewById(R.id.btn_test_traditional_nested_scroll).setOnClickListener(this);
        findViewById(R.id.btn_test_traditional_scroll_view_recycler_view).setOnClickListener(this);
        findViewById(R.id.btn_test_traditional_nested_scroll_view_recycler_view).setOnClickListener(this);
        findViewById(R.id.btn_test_nested_scroll_parent2_view_recycler_view).setOnClickListener(this);
        findViewById(R.id.btn_test_nested_scroll_recycler_view_and_recycler_view).setOnClickListener(this);
        findViewById(R.id.btn_test_recycler_view_and_recycler_view).setOnClickListener(this);
        findViewById(R.id.btn_add_refresh).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_traditional_nested_scroll:
                NestedScrollTestActivity.launch(this, false);
                break;
            case R.id.btn_test_nested_scrolling:
                NestedScrollTestActivity.launch(this, true);
                break;
            case R.id.btn_test_traditional_scroll_view_recycler_view:
                ScrollViewAndRecyclerViewActivity.launch(this, 1);
                break;
            case R.id.btn_test_traditional_nested_scroll_view_recycler_view:
                ScrollViewAndRecyclerViewActivity.launch(this, 2);
                break;
            case R.id.btn_test_nested_scroll_parent2_view_recycler_view:
                ScrollViewAndRecyclerViewActivity.launch(this, 3);
                break;

            case R.id.btn_test_recycler_view_and_recycler_view:
                RecyclerViewAndRecyclerViewActivity.launch(this, false);
                break;
            case R.id.btn_test_nested_scroll_recycler_view_and_recycler_view:
                RecyclerViewAndRecyclerViewActivity.launch(this, true);
                break;
            case R.id.btn_add_refresh:
                NestedScrollTestRefreshActivity.launch(this, true);
            default:
                break;
        }
    }


}
