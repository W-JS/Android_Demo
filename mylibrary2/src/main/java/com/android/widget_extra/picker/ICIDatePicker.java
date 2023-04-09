package com.android.widget_extra.picker;

import android.content.Context;
import android.util.AttributeSet;

public class ICIDatePicker extends ICIDateAndTimePicker {
    private OnDatePickListener onDatePickListenerl;

    public ICIDatePicker(Context context) {
        super(context);
        init();
    }

    public ICIDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setShowItem(true, true, true, false, false);
        setOnAllTimePickListener(new OnAllTimePickListener() {
            @Override
            public void onSelected(String year, String month, String day, String hour, String minute, String am) {
                if (onDatePickListenerl != null) {
                    onDatePickListenerl.onSelect(year, month, day);
                }
            }
        });
    }

    public void setOnDatePickListenerl(OnDatePickListener onDatePickListenerl) {
        this.onDatePickListenerl = onDatePickListenerl;
    }

    public interface OnDatePickListener {
        public void onSelect(String year, String month, String day);
    }
}
