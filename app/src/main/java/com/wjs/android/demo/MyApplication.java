package com.wjs.android.demo;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * @Description
 * @Date 2023/4/24 13:42:37
 * @Author jinshengw
 */
public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        startMyService();
    }

    private void startMyService() {
        Log.i(TAG, "startMyService: ");
        startService(new Intent(this, MyService.class));
    }

    public static MyApplication getInstance() {
        return mContext;
    }
}
