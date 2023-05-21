package com.wjs.android.demo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class CustomViewGroupActivity extends AppCompatActivity {

    private final static String TAG = CustomViewGroupActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);
    }
}