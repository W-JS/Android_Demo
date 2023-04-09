package com.android.widget_extra.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ICIIndicatorView extends LinearLayout implements ICICommonView {
    private int count = 5;
    private int mIndex = -1;
    private List<CheckedTextView> checkedTextViews = new ArrayList<>();

    public ICIIndicatorView(Context context) {
        super(context);
        init();
    }

    public ICIIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
        setOrientation(HORIZONTAL);
        for (int i = 0; i < count; i++) {
            CheckedTextView checkedTextView = new CheckedTextView(getContext());
            LayoutParams params = new LayoutParams(20, 2);
            if (i != 0) {
                params.leftMargin = 10;
            }
            checkedTextView.setBackgroundResource(background);
            checkedTextView.setTypeface(FontUtils.getDefaultFont());
            addView(checkedTextView, params);
            checkedTextViews.add(checkedTextView);
        }
    }


    private void changeTo(int index) {
        if (index == mIndex) {
            return;
        }
        for (int i = 0; i < checkedTextViews.size(); i++) {
            CheckedTextView view = checkedTextViews.get(i);
            view.setChecked(false);
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            if (i != index) {
                params.width = 27;
            } else {
                params.width = 50;
            }
            view.setLayoutParams(params);
        }
        checkedTextViews.get(index).setChecked(true);
        mIndex = index;
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

    private int background;

    @Override
    public void initCommon() {

    }

    @Override
    public void initCher() {
        background = R.drawable.ici28c_status_indicator;
    }

    @Override
    public void initBuck() {
        background = R.drawable.ici28b_status_indicator;
    }
}
