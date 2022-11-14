package com.hao.androidrecord.calendar.activity.lib01.progress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.hao.androidrecord.R;
import com.hao.androidrecord.calendar.activity.lib01.Article;
import com.hao.androidrecord.calendar.activity.lib01.ArticleAdapter;
import com.hao.androidrecord.calendar.activity.lib01.base.activity.BaseActivity;
import com.hao.androidrecord.calendar.activity.lib01.colorful.ColorfulActivity;
import com.hao.androidrecord.calendar.activity.lib01.group.GroupItemDecoration;
import com.hao.androidrecord.calendar.activity.lib01.group.GroupRecyclerView;
import com.hao.androidrecord.calendar.activity.lib01.index.IndexActivity;
import com.hao.androidrecord.calendar.activity.lib01.simple.SimpleActivity;
import com.hao.androidrecord.calendar.lib01.Calendar;
import com.hao.androidrecord.calendar.lib01.CalendarLayout;
import com.hao.androidrecord.calendar.lib01.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgressActivity extends BaseActivity implements
        CalendarView.OnDateSelectedListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener {

    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    GroupRecyclerView mRecyclerView;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ProgressActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_progress;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mTextMonthDay = (TextView) findViewById(R.id.tv_month_day);
        mTextYear = (TextView) findViewById(R.id.tv_year);
        mTextLunar = (TextView) findViewById(R.id.tv_lunar);
        mRelativeTool = (RelativeLayout) findViewById(R.id.rl_tool);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mTextCurrentDay = (TextView) findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
    }

    @Override
    protected void initData() {
        List<Calendar> schemes = new ArrayList<>();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        schemes.add(getSchemeCalendar(year, month, 3, 0xFF40db25, "20"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "30"));
        schemes.add(getSchemeCalendar(year, month, 9, 0xFFdf1356, "33"));
        schemes.add(getSchemeCalendar(year, month, 12, 0xFFdf1356, "25"));
        schemes.add(getSchemeCalendar(year, month, 13, 0xFFedc56d, "50"));
        schemes.add(getSchemeCalendar(year, month, 14, 0xFFedc56d, "80"));
        schemes.add(getSchemeCalendar(year, month, 15, 0xFFaacc44, "20"));
        schemes.add(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "70"));
        schemes.add(getSchemeCalendar(year, month, 25, 0xFF13acf0, "36"));
        schemes.add(getSchemeCalendar(year, month, 27, 0xFF13acf0, "95"));


         /*
         * 此方法现在弃用，但不影响原来的效果，原因：数据量大时 size()>10000 ，遍历性能太差，超过Android限制的16ms响应，造成卡顿
         * 现在推荐使用 setSchemeDate(Map<String, Calendar> mSchemeDates)，Map查找性能非常好，经测试，50000以上数据，1ms解决
         */
        mCalendarView.setSchemeDate(schemes);


        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "20").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "20"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "33").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "33"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "25").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "25"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "50").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "50"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "80").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "80"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "20").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "20"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "70").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "70"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "36").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "36"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "95").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "95"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);



        mRecyclerView = (GroupRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(this));
        mRecyclerView.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_flyme:
                ProgressActivity.show(this);
                break;
            case R.id.ll_simple:
                SimpleActivity.show(this);
                break;
            case R.id.ll_colorful:
                ColorfulActivity.show(this);
                break;
            case R.id.ll_index:
                IndexActivity.show(this);
                break;
        }
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }


    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }


}
