package com.android.widget_extra.other;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

//import com.android.internal.R;

import com.wjs.android.mylibrary2.R;

import java.util.ArrayList;
import java.util.List;

public class ICISildeIndicator extends LinearLayout {
    private int count = 5;
    private int mIndex = -1;

    private List<CheckedTextView> checkedTextViews = new ArrayList<>();

    public ICISildeIndicator(Context context) {
        super(context);
        init();
    }

    public ICISildeIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICISildeIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICISildeIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setBackgroundResource(R.drawable.ici_corner_slideindicator_bg);
        for (int i = 0; i < count; i++) {
            CheckedTextView checkedTextView = new CheckedTextView(getContext());
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 2;
            if (i != 0) {
                params.leftMargin = 20;
            }
            checkedTextView.setBackgroundResource(R.drawable.ici28c_status_indicator);
            addView(checkedTextView, params);
            checkedTextViews.add(checkedTextView);
        }
        changeTo(0);
    }

    private void changeTo(int index) {
        if (index == mIndex) {
            return;
        }
        for (CheckedTextView view : checkedTextViews) {
            view.setChecked(false);
        }
        checkedTextViews.get(index).setChecked(true);
        mIndex = index;

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(32, 20);
        valueAnimator.start();
    }

    public void setIndex(int index) {
        changeTo(index);
    }

    public void setCount(int count) {
        this.count = count;
        removeAllViews();
        checkedTextViews.clear();
        init();
    }
}
