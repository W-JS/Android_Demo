package com.wjs.android.demo.widgetstest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wjs.android.demo.R;

public class WidgetsTestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.sample_button).setOnClickListener(this);
        findViewById(R.id.sample_tabview).setOnClickListener(this);
        findViewById(R.id.sample_subheader).setOnClickListener(this);
        findViewById(R.id.sample_select).setOnClickListener(this);
        findViewById(R.id.sample_datepick).setOnClickListener(this);
        findViewById(R.id.sample_list).setOnClickListener(this);
        findViewById(R.id.sample_navibar).setOnClickListener(this);
        findViewById(R.id.sample_progress).setOnClickListener(this);
        findViewById(R.id.sample_mutitoggle).setOnClickListener(this);
        findViewById(R.id.sample_textdeilf).setOnClickListener(this);
        findViewById(R.id.sample_dialog).setOnClickListener(this);
        findViewById(R.id.sample_toast).setOnClickListener(this);
        findViewById(R.id.sample_indocator).setOnClickListener(this);
        findViewById(R.id.sample_scrollview).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_widgets_test;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_button:
                startMeActivity(ButtonActivity.class);
                break;
            case R.id.sample_tabview:
                startMeActivity(TabViewActivity.class);
                break;
            case R.id.sample_navibar:
                startMeActivity(NavigationBarActivity.class);
                break;
            case R.id.sample_list:
                startMeActivity(ListItemActivity.class);
                break;
            case R.id.sample_select:
                startMeActivity(SelectionControlActivity.class);
                break;
            case R.id.sample_indocator:
                startMeActivity(IndicatorActivity.class);
                break;
            case R.id.sample_datepick:
                startMeActivity(DatePickActivity.class);
                break;
            case R.id.sample_progress:
                startMeActivity(ProgressActivity.class);
                break;
            case R.id.sample_textdeilf:
                startMeActivity(TexfieldActivity.class);
                break;
            case R.id.sample_dialog:
                startMeActivity(DialogActivity.class);
                break;
            case R.id.sample_toast:
                startMeActivity(ToastActivity.class);
                break;
            case R.id.sample_scrollview:
                startMeActivity(ScrollViewActivity.class);
                break;
            default:
        }

    }

    private void startMeActivity(Class className) {
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }
}
