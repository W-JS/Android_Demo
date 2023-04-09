package com.android.widget_extra.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.android.widget_extra.utils.MeasureUtils;

public class ICI28ActionGroupView extends ViewGroup {
    private int width, height;
    private static final int ACTION_WIDTH = 176;

    public ICI28ActionGroupView(Context context) {
        super(context);
    }

    public ICI28ActionGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ICI28ActionGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            this.measureChild(child, MeasureUtils.getMesureWidth(child, ACTION_WIDTH), heightMeasureSpec);
            int cw = child.getMeasuredWidth();
            // int ch = child.getMeasuredHeight();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int everyWidth = width / count;
        int visiWidth = Math.min(everyWidth, ACTION_WIDTH);
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);

            //for coverity
            if (v == null) {
                continue;
            }

            v.layout(visiWidth * i, 0, visiWidth * i + visiWidth, bottom);
        }
    }
}
