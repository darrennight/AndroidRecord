package com.hao.androidrecord.calendar.activity.lib01;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.hao.androidrecord.R;
import com.hao.androidrecord.calendar.activity.lib01.base.activity.BaseActivity;
import com.hao.androidrecord.calendar.activity.lib01.colorful.ColorfulActivity;
import com.hao.androidrecord.calendar.activity.lib01.custom.CustomActivity;
import com.hao.androidrecord.calendar.activity.lib01.custom.CustomMonthView;
import com.hao.androidrecord.calendar.activity.lib01.custom.CustomWeekBar;
import com.hao.androidrecord.calendar.activity.lib01.custom.CustomWeekView;
import com.hao.androidrecord.calendar.activity.lib01.index.IndexActivity;
import com.hao.androidrecord.calendar.activity.lib01.meizu.MeiZuActivity;
import com.hao.androidrecord.calendar.activity.lib01.pager.CalendarViewPagerActivity;
import com.hao.androidrecord.calendar.activity.lib01.progress.ProgressActivity;
import com.hao.androidrecord.calendar.activity.lib01.simple.SimpleActivity;
import com.hao.androidrecord.calendar.activity.lib01.single.SingleActivity;
import com.hao.androidrecord.calendar.activity.lib01.solay.SolarActivity;
import com.hao.androidrecord.calendar.lib01.Calendar;
import com.hao.androidrecord.calendar.lib01.CalendarLayout;
import com.hao.androidrecord.calendar.lib01.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://github.com/ashLikun/CalendarView
public class CalendarLib01Activity extends BaseActivity implements
        CalendarView.OnDateSelectedListener,
        CalendarView.OnMonthChangeListener,
        CalendarView.OnYearChangeListener,
        CalendarView.OnDateLongClickListener,
        CalendarView.OnViewChangeListener,
        DialogInterface.OnClickListener,
        View.OnClickListener {

    TextView mTextMonthDay;

    TextView mTextYear;

    TextView mTextLunar;

    TextView mTextCurrentDay;

    CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;

    private AlertDialog mMoreDialog;

    private AlertDialog mFuncDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar_lib01_main;
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
                //mCalendarView.scrollToCalendar(2018,7,14);
                Log.e("scrollToCurrent", "   --  " + mCalendarView.getSelectedCalendar());
            }
        });
        findViewById(R.id.iv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMoreDialog == null) {
                    mMoreDialog = new AlertDialog.Builder(CalendarLib01Activity.this)
                            .setTitle(R.string.list_dialog_title)
                            .setItems(R.array.list_dialog_items, CalendarLib01Activity.this)
                            .create();
                }
                mMoreDialog.show();
            }
        });

        final DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                mCalendarLayout.expand();
                                break;
                            case 1:
                                mCalendarLayout.shrink();
                                break;
                            case 2:
                                mCalendarView.scrollToPre(true);
                                break;
                            case 3:
                                mCalendarView.scrollToNext(true);
                                break;
                            case 4:
                                mCalendarView.scrollToCurrent(true);
                                break;
                        }
                    }
                };

        findViewById(R.id.iv_func).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFuncDialog == null) {
                    mFuncDialog = new AlertDialog.Builder(CalendarLib01Activity.this)
                            .setTitle(R.string.func_dialog_title)
                            .setItems(R.array.func_dialog_items, listener)
                            .create();
                }
                mFuncDialog.show();
            }
        });

        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnMonthChangeListener(this);
        mCalendarView.setOnDateLongClickListener(this, true);
        mCalendarView.setOnViewChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
    }

    @SuppressWarnings("unused")
    @Override
    protected void initData() {
        final List<Calendar> schemes = new ArrayList<>();
        final int year = mCalendarView.getCurYear();
        final int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();
        for (int y = 1997; y < 2082; y++) {
            for (int m = 1; m <= 12; m++) {
                map.put(getSchemeCalendar(y, m, 1, 0xFF40db25, "假").toString(),
                        getSchemeCalendar(y, m, 1, 0xFF40db25, "假"));
                map.put(getSchemeCalendar(y, m, 2, 0xFFe69138, "游").toString(),
                        getSchemeCalendar(y, m, 2, 0xFFe69138, "游"));
                map.put(getSchemeCalendar(y, m, 3, 0xFFdf1356, "事").toString(),
                        getSchemeCalendar(y, m, 3, 0xFFdf1356, "事"));
                map.put(getSchemeCalendar(y, m, 4, 0xFFaacc44, "车").toString(),
                        getSchemeCalendar(y, m, 4, 0xFFaacc44, "车"));
                map.put(getSchemeCalendar(y, m, 5, 0xFFbc13f0, "驾").toString(),
                        getSchemeCalendar(y, m, 5, 0xFFbc13f0, "驾"));
                map.put(getSchemeCalendar(y, m, 6, 0xFF542261, "记").toString(),
                        getSchemeCalendar(y, m, 6, 0xFF542261, "记"));
                map.put(getSchemeCalendar(y, m, 7, 0xFF4a4bd2, "会").toString(),
                        getSchemeCalendar(y, m, 7, 0xFF4a4bd2, "会"));
                map.put(getSchemeCalendar(y, m, 8, 0xFFe69138, "车").toString(),
                        getSchemeCalendar(y, m, 8, 0xFFe69138, "车"));
                map.put(getSchemeCalendar(y, m, 9, 0xFF542261, "考").toString(),
                        getSchemeCalendar(y, m, 9, 0xFF542261, "考"));
                map.put(getSchemeCalendar(y, m, 10, 0xFF87af5a, "记").toString(),
                        getSchemeCalendar(y, m, 10, 0xFF87af5a, "记"));
                map.put(getSchemeCalendar(y, m, 11, 0xFF40db25, "会").toString(),
                        getSchemeCalendar(y, m, 11, 0xFF40db25, "会"));
                map.put(getSchemeCalendar(y, m, 12, 0xFFcda1af, "游").toString(),
                        getSchemeCalendar(y, m, 12, 0xFFcda1af, "游"));
                map.put(getSchemeCalendar(y, m, 13, 0xFF95af1a, "事").toString(),
                        getSchemeCalendar(y, m, 13, 0xFF95af1a, "事"));
                map.put(getSchemeCalendar(y, m, 14, 0xFF33aadd, "学").toString(),
                        getSchemeCalendar(y, m, 14, 0xFF33aadd, "学"));
                map.put(getSchemeCalendar(y, m, 15, 0xFF1aff1a, "码").toString(),
                        getSchemeCalendar(y, m, 15, 0xFF1aff1a, "码"));
                map.put(getSchemeCalendar(y, m, 16, 0xFF22acaf, "驾").toString(),
                        getSchemeCalendar(y, m, 16, 0xFF22acaf, "驾"));
                map.put(getSchemeCalendar(y, m, 17, 0xFF99a6fa, "校").toString(),
                        getSchemeCalendar(y, m, 17, 0xFF99a6fa, "校"));
                map.put(getSchemeCalendar(y, m, 18, 0xFFe69138, "车").toString(),
                        getSchemeCalendar(y, m, 18, 0xFFe69138, "车"));
                map.put(getSchemeCalendar(y, m, 19, 0xFF40db25, "码").toString(),
                        getSchemeCalendar(y, m, 19, 0xFF40db25, "码"));
                map.put(getSchemeCalendar(y, m, 20, 0xFFe69138, "火").toString(),
                        getSchemeCalendar(y, m, 20, 0xFFe69138, "火"));
                map.put(getSchemeCalendar(y, m, 21, 0xFF40db25, "假").toString(),
                        getSchemeCalendar(y, m, 21, 0xFF40db25, "假"));
                map.put(getSchemeCalendar(y, m, 22, 0xFF99a6fa, "记").toString(),
                        getSchemeCalendar(y, m, 22, 0xFF99a6fa, "记"));
                map.put(getSchemeCalendar(y, m, 23, 0xFF33aadd, "假").toString(),
                        getSchemeCalendar(y, m, 23, 0xFF33aadd, "假"));
                map.put(getSchemeCalendar(y, m, 24, 0xFF40db25, "校").toString(),
                        getSchemeCalendar(y, m, 24, 0xFF40db25, "校"));
                map.put(getSchemeCalendar(y, m, 25, 0xFF1aff1a, "假").toString(),
                        getSchemeCalendar(y, m, 25, 0xFF1aff1a, "假"));
                map.put(getSchemeCalendar(y, m, 26, 0xFF40db25, "议").toString(),
                        getSchemeCalendar(y, m, 26, 0xFF40db25, "议"));
                map.put(getSchemeCalendar(y, m, 27, 0xFF95af1a, "假").toString(),
                        getSchemeCalendar(y, m, 27, 0xFF95af1a, "假"));
                map.put(getSchemeCalendar(y, m, 28, 0xFF40db25, "码").toString(),
                        getSchemeCalendar(y, m, 28, 0xFF40db25, "码"));
            }
        }

        for (int y = 1997; y < 2082; y++) {
            for (int m = 1; m <= 12; m++) {
                schemes.add(getSchemeCalendar(y, m, 1, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 2, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 3, 0xFFe69138, "假"));
                schemes.add(getSchemeCalendar(y, m, 4, 0xFFe69138, "假"));
                schemes.add(getSchemeCalendar(y, m, 5, 0xFFdf1356, "假"));
                schemes.add(getSchemeCalendar(y, m, 6, 0xFFdf1356, "假"));
                schemes.add(getSchemeCalendar(y, m, 7, 0xFFaacc44, "事"));
                schemes.add(getSchemeCalendar(y, m, 8, 0xFFaacc44, "议"));
                schemes.add(getSchemeCalendar(y, m, 9, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 10, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 11, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 12, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 13, 0xFFedc56d, "记"));
                schemes.add(getSchemeCalendar(y, m, 14, 0xFFedc56d, "记"));
                schemes.add(getSchemeCalendar(y, m, 15, 0xFFaacc44, "假"));
                schemes.add(getSchemeCalendar(y, m, 16, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 17, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 18, 0xFFbc13f0, "记"));
                schemes.add(getSchemeCalendar(y, m, 19, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 20, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 21, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 22, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 23, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 24, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 25, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 26, 0xFF13acf0, "假"));
                schemes.add(getSchemeCalendar(y, m, 37, 0xFF40db25, "假"));
                schemes.add(getSchemeCalendar(y, m, 38, 0xFF40db25, "假"));
            }
        }
        //28560 数据量增长不会影响UI响应速度，请使用这个API替换
        mCalendarView.setSchemeDate(map);

        //可自行测试性能差距
        //mCalendarView.setSchemeDate(schemes);

        findViewById(R.id.ll_flyme).setOnClickListener(this);
        findViewById(R.id.ll_simple).setOnClickListener(this);
        findViewById(R.id.ll_colorful).setOnClickListener(this);
        findViewById(R.id.ll_index).setOnClickListener(this);
        findViewById(R.id.ll_tab).setOnClickListener(this);
        findViewById(R.id.ll_single).setOnClickListener(this);
        findViewById(R.id.ll_solar_system).setOnClickListener(this);
        findViewById(R.id.ll_progress).setOnClickListener(this);
        findViewById(R.id.ll_custom).setOnClickListener(this);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case 0:
                mCalendarView.setWeekStarWithSun();
                break;
            case 1:
                mCalendarView.setWeekStarWithMon();
                break;
            case 2:
                mCalendarView.setWeekStarWithSat();
                break;
            case 3:
                if (mCalendarView.isSingleSelectMode()) {
                    mCalendarView.setSelectDefaultMode();
                } else {
                    mCalendarView.setSelectSingleMode();
                }
                break;
            case 4:
                mCalendarView.setWeekView(CustomWeekView.class);
                mCalendarView.setMonthView(CustomMonthView.class);
                mCalendarView.setWeekBar(CustomWeekBar.class);
                break;
            case 5:
                mCalendarView.setAllMode();
                break;
            case 6:
                mCalendarView.setOnlyCurrentMode();
                break;
            case 7:
                mCalendarView.setFixMode();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_flyme:
                MeiZuActivity.show(this);
                break;
            case R.id.ll_custom:
                CustomActivity.show(this);
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
            case R.id.ll_tab:
                CalendarViewPagerActivity.show(this);
                break;
            case R.id.ll_single:
                SingleActivity.show(this);
                break;
            case R.id.ll_solar_system:
                SolarActivity.show(this);
                break;
            case R.id.ll_progress:
                ProgressActivity.show(this);
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
        //Log.e("onDateSelected", "  -- " + calendar.getYear() + "  --  " + calendar.getMonth() + "  -- " + calendar.getDay());
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
        if (isClick) {
            Toast.makeText(this, getCalendarText(calendar), Toast.LENGTH_SHORT).show();
        }
        Log.e("onDateSelected", "  -- " + calendar.getYear() +
                "  --  " + calendar.getMonth() +
                "  -- " + calendar.getDay() +
                "  --  " + isClick);
    }

    @Override
    public void onDateLongClick(Calendar calendar) {
        Toast.makeText(this, "长按不选择日期\n" + getCalendarText(calendar), Toast.LENGTH_SHORT).show();
    }

    private static String getCalendarText(Calendar calendar) {
        return String.format("新历%s \n 农历%s \n 公历节日：%s \n 农历节日：%s \n 节气：%s \n 是否闰月：%s",
                calendar.getMonth() + "月" + calendar.getDay() + "日",
                calendar.getLunarCakendar().getMonth() + "月" + calendar.getLunarCakendar().getDay() + "日",
                TextUtils.isEmpty(calendar.getGregorianFestival()) ? "无" : calendar.getGregorianFestival(),
                TextUtils.isEmpty(calendar.getTraditionFestival()) ? "无" : calendar.getTraditionFestival(),
                TextUtils.isEmpty(calendar.getSolarTerm()) ? "无" : calendar.getSolarTerm(),
                calendar.getLeapMonth() == 0 ? "否" : String.format("闰%s月", calendar.getLeapMonth()));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMonthChange(int year, int month) {
        //Log.e("onMonthChange", "  -- " + year + "  --  " + month);
        Calendar calendar = mCalendarView.getSelectedCalendar();
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onViewChange(boolean isMonthView) {
        Log.e("onViewChange", "  ---  " + (isMonthView ? "月视图" : "周视图"));
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

}

