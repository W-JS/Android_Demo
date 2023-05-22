package com.wjs.android.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class CustomViewGroupActivity extends AppCompatActivity {

    private final static String TAG = CustomViewGroupActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: BEF: " + ev);
        boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent: AFT: " + dispatchTouchEvent + "," + ev);
        return dispatchTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: BEF: " + event);
        boolean touchEvent = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent: AFT: " + touchEvent + "," + event);
        return touchEvent;
    }
}