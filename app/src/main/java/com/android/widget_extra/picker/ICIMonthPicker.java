package com.android.widget_extra.picker;

import android.content.Context;
import android.util.AttributeSet;

public class ICIMonthPicker extends ICIDateAndTimePicker {
    private OnMonthPickListener onMonthPickListener;

    public ICIMonthPicker(Context context) {
        super(context);
        init();
    }

    public ICIMonthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIMonthPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setShowItem(true, true, false, false, false);
        setOnAllTimePickListener(new OnAllTimePickListener() {
            @Override
            public void onSelected(String year, String month, String day, String hour, String minute, String am) {
                if (onMonthPickListener != null) {
                    onMonthPickListener.onSelect(year, month);
                }
            }
        });
    }

    public void setOnMonthPickListener(OnMonthPickListener onMonthPickListener) {
        this.onMonthPickListener = onMonthPickListener;
    }

    public interface OnMonthPickListener {
        public void onSelect(String year, String month);
    }

}
