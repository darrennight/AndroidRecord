package com.hao.androidrecord.calendar.lib01;


import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者　　: 李坤
 * 创建时间: 2018/8/12 0012 13:05
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：日历对象
 */
public final class Calendar implements Serializable {
    private static final long serialVersionUID = 141315161718191143L;

    /**
     * 年
     */
    private int year;

    /**
     * 月1-12
     */
    private int month;

    /**
     * 如果是闰月，则返回闰月
     */
    private int leapMonth;

    /**
     * 日1-31
     */
    private int day;

    /**
     * 是否是闰年
     */
    private boolean isLeapYear;

    /**
     * 是否是本月,这里对应的是月视图的本月，而非当前月份，请注意
     */
    private boolean isCurrentMonth;

    /**
     * 是否是今天
     */
    private boolean isCurrentDay;

    /**
     * 农历字符串，没有特别大的意义，用来做简单的农历或者节日标记
     * 建议通过lunarCakendar获取完整的农历日期
     */
    private String lunar;


    /**
     * 24节气
     */
    private String solarTerm;


    /**
     * 公历节日
     */
    private String gregorianFestival;

    /**
     * 传统农历节日
     */
    private String traditionFestival;

    /**
     * 计划，可以用来标记当天是否有任务,这里是默认的，如果使用多标记，请使用下面API
     * using addScheme(int schemeColor,String scheme); multi scheme
     */
    private String scheme;

    /**
     * 各种自定义标记颜色、没有则选择默认颜色，如果使用多标记，请使用下面API
     * using addScheme(int schemeColor,String scheme); multi scheme
     */
    private int schemeColor;


    /**
     * 多标记
     * multi scheme,using addScheme();
     */
    private List<Scheme> schemes;

    /**
     * 是否是周末
     */
    private boolean isWeekend;

    /**
     * 星期,0-6 对应周日到周一
     */
    private int week;
    /**
     * 这个日期是否可以点击
     */
    private boolean click = true;

    /**
     * 获取完整的农历日期
     */
    private Calendar lunarCakendar;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }


    public void setCurrentMonth(boolean currentMonth) {
        this.isCurrentMonth = currentMonth;
    }

    public boolean isCurrentDay() {
        return isCurrentDay;
    }

    public void setCurrentDay(boolean currentDay) {
        isCurrentDay = currentDay;
    }


    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }


    public String getScheme() {
        return scheme;
    }


    public void setScheme(String scheme) {
        this.scheme = scheme;
    }


    public int getSchemeColor() {
        return schemeColor;
    }

    public void setSchemeColor(int schemeColor) {
        this.schemeColor = schemeColor;
    }


    public List<Scheme> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes) {
        this.schemes = schemes;
    }


    public void addScheme(Scheme scheme) {
        if (schemes == null) {
            schemes = new ArrayList<>();
        }
        schemes.add(scheme);
    }

    public void addScheme(int schemeColor, String scheme) {
        if (schemes == null) {
            schemes = new ArrayList<>();
        }
        schemes.add(new Scheme(schemeColor, scheme));
    }

    public void addScheme(int type, int schemeColor, String scheme) {
        if (schemes == null) {
            schemes = new ArrayList<>();
        }
        schemes.add(new Scheme(type, schemeColor, scheme));
    }

    public void addScheme(int type, int schemeColor, String scheme, String other) {
        if (schemes == null) {
            schemes = new ArrayList<>();
        }
        schemes.add(new Scheme(type, schemeColor, scheme, other));
    }

    public void addScheme(int schemeColor, String scheme, String other) {
        if (schemes == null) {
            schemes = new ArrayList<>();
        }
        schemes.add(new Scheme(schemeColor, scheme, other));
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Calendar getLunarCakendar() {
        return lunarCakendar;
    }

    public void setLunarCakendar(Calendar lunarCakendar) {
        this.lunarCakendar = lunarCakendar;
    }

    public String getSolarTerm() {
        return solarTerm;
    }

    public void setSolarTerm(String solarTerm) {
        this.solarTerm = solarTerm;
    }

    public String getGregorianFestival() {
        return gregorianFestival;
    }

    public void setGregorianFestival(String gregorianFestival) {
        this.gregorianFestival = gregorianFestival;
    }


    public int getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(int leapMonth) {
        this.leapMonth = leapMonth;
    }

    public boolean isLeapYear() {
        return isLeapYear;
    }

    public void setLeapYear(boolean leapYear) {
        isLeapYear = leapYear;
    }

    public String getTraditionFestival() {
        return traditionFestival;
    }

    public void setTraditionFestival(String traditionFestival) {
        this.traditionFestival = traditionFestival;
    }

    public boolean hasScheme() {
        if (schemes != null && schemes.size() != 0) {
            return true;
        }
        if (!TextUtils.isEmpty(scheme)) {
            return true;
        }
        return false;
    }

    public boolean isSameMonth(Calendar calendar) {
        return year == calendar.getYear() && month == calendar.getMonth();
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public int compareTo(Calendar calendar) {
        return toString().compareTo(calendar.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Calendar) {
            if (((Calendar) o).getYear() == year && ((Calendar) o).getMonth() == month && ((Calendar) o).getDay() == day) {
                return true;
            }
        }
        return super.equals(o);
    }

    @Override
    public String toString() {
        return year + "" + (month < 10 ? "0" + month : month) + "" + (day < 10 ? "0" + day : day);
    }

    /**
     * 事件标记服务，现在多类型的事务标记建议使用这个
     */
    public final static class Scheme implements Serializable {
        private int type;
        private int shcemeColor;
        private String scheme;
        private String other;

        public Scheme() {
        }

        public Scheme(int type, int shcemeColor, String scheme, String other) {
            this.type = type;
            this.shcemeColor = shcemeColor;
            this.scheme = scheme;
            this.other = other;
        }

        public Scheme(int type, int shcemeColor, String scheme) {
            this.type = type;
            this.shcemeColor = shcemeColor;
            this.scheme = scheme;
        }

        public Scheme(int shcemeColor, String scheme) {
            this.shcemeColor = shcemeColor;
            this.scheme = scheme;
        }

        public Scheme(int shcemeColor, String scheme, String other) {
            this.shcemeColor = shcemeColor;
            this.scheme = scheme;
            this.other = other;
        }

        public int getShcemeColor() {
            return shcemeColor;
        }

        public void setShcemeColor(int shcemeColor) {
            this.shcemeColor = shcemeColor;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

    }
}
