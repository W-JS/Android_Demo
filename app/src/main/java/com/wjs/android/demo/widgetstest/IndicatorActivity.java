package com.wjs.android.demo.widgetstest;

import android.os.Bundle;

import com.android.widget_extra.other.ICIIndicatorView;
import com.android.widget_extra.tab.ICI28TabView;
import com.wjs.android.demo.R;

import java.util.Arrays;

public class IndicatorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ICI28TabView ici28TabView = findViewById(R.id.ici_inficator_tab);
        final ICIIndicatorView indicatorView = findViewById(R.id.ici_indicator);
        indicatorView.setCount(3);
        ici28TabView.setItems(Arrays.asList(new String[]{"选项一", "选项二", "选项三"}));
        ici28TabView.setOnTabClickListner(new ICI28TabView.OnTabClickListner() {
            @Override
            public void onTabClick(int position) {
                indicatorView.setIndex(position);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_indicator;
    }
}