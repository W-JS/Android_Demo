package com.android.widget_extra.wheelview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ICIDatePickTextView extends TextView {
    public ICIDatePickTextView(Context context) {
        super(context);
    }

    public ICIDatePickTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ICIDatePickTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ICIDatePickTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            int deltaHeight = (getPaint().getFontMetricsInt().ascent - getPaint().getFontMetricsInt().top + getPaint().getFontMetricsInt().bottom - getPaint().getFontMetricsInt().descent);
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() - deltaHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int topDelta = (getPaint().getFontMetricsInt().ascent - getPaint().getFontMetricsInt().top);
        setTop(-topDelta);
        super.onDraw(canvas);
    }
}
