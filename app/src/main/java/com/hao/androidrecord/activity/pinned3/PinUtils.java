package com.hao.androidrecord.activity.pinned3;

import android.content.Context;
import android.util.DisplayMetrics;

public class PinUtils {
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
