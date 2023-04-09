package com.android.widget_extra.utils;

import android.view.View;
import android.view.View.MeasureSpec;

public class MeasureUtils {

    public static int getMesureWidth(View v, int originWidth) {
        return MeasureSpec.makeMeasureSpec(originWidth + v.getPaddingLeft() + v.getPaddingRight(), MeasureSpec.AT_MOST);
    }

    public static int getMesureHeight(View v, int originHeight) {
        return MeasureSpec.makeMeasureSpec(originHeight + v.getPaddingTop() + v.getPaddingBottom(), MeasureSpec.AT_MOST);
    }


}
