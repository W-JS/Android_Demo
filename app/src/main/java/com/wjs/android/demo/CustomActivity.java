package com.wjs.android.demo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class CustomActivity extends AppCompatActivity {

    private final static String TAG = CustomActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        Log.d(TAG, "onCreate: ");
    }
}