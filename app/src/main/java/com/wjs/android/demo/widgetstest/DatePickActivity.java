package com.wjs.android.demo.widgetstest;

import android.os.Bundle;

import com.android.widget_extra.picker.ICIDateAndTimePicker;
import com.android.widget_extra.picker.ICIDatePicker;
import com.android.widget_extra.picker.ICIMonthPicker;
import com.android.widget_extra.picker.ICITimePicker;
import com.wjs.android.demo.R;

public class DatePickActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ICIDateAndTimePicker iciTimeAndDatePicker = findViewById(R.id.ici_date_timepick);
        iciTimeAndDatePicker.initDate(System.currentTimeMillis());
        iciTimeAndDatePicker.setOnAllTimePickListener(new ICIDateAndTimePicker.OnAllTimePickListener() {
            @Override
            public void onSelected(String year, String month, String day, String hour, String minute, String am) {
            }
        });

        ICIDatePicker iciDatePicker = findViewById(R.id.ici_datepick);
        iciDatePicker.initDate(System.currentTimeMillis());
        iciDatePicker.setOnDatePickListenerl(new ICIDatePicker.OnDatePickListener() {
            @Override
            public void onSelect(String year, String month, String day) {
            }
        });

        ICIMonthPicker iciMonthPicker = findViewById(R.id.ici_monthpick);
        iciMonthPicker.initDate(System.currentTimeMillis());
        iciMonthPicker.setOnMonthPickListener(new ICIMonthPicker.OnMonthPickListener() {
            @Override
            public void onSelect(String year, String month) {
            }
        });

        ICITimePicker iciTimePicker = findViewById(R.id.ici_timehpick);
        iciTimePicker.initDate(System.currentTimeMillis());
        iciTimePicker.setOnTimePickListener(new ICITimePicker.OnTimePickListener() {
            @Override
            public void onSelect(String hour, String min) {
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_datepick;
    }
}