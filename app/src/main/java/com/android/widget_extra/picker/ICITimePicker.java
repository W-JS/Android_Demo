package com.android.widget_extra.picker;

import android.content.Context;
import android.util.AttributeSet;


public class ICITimePicker extends ICIDateAndTimePicker {
    private OnTimePickListener onTimePickListener;


    public ICITimePicker(Context context) {
        super(context);
        init();
    }

    public ICITimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICITimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setShowItem(false, false, false, true, true);
        setOnAllTimePickListener(new OnAllTimePickListener() {
            @Override
            public void onSelected(String year, String month, String day, String hour, String minute, String am) {
                if (onTimePickListener != null) {
                    onTimePickListener.onSelect(hour, minute);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setOnTimePickListener(OnTimePickListener onTimePickListener) {
        this.onTimePickListener = onTimePickListener;
    }

    public interface OnTimePickListener {
        public void onSelect(String hour, String min);
    }
}
