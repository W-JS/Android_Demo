package com.wjs.android.demo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wjs.android.mylibrary.utils.DateTimeUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String todayDateTime = DateTimeUtils.getTodayDateTime();
        Toast.makeText(MainActivity.this, "调用jar包方法测试时间：" + todayDateTime, Toast.LENGTH_SHORT).show();
    }
}