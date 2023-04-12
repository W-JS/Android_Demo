package com.wjs.android.aidl_server;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyApplication extends Application {

    private final static String TAG = MyApplication.class.getSimpleName();

    private static MyApplication mContext;

    public static MyApplication getInstance() {
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        mContext = this;
        startMyService();
    }

    private void startMyService() {
        Log.d(TAG, "startMyService: ");
        startService(new Intent(this, MyService.class));
    }
}
