package com.wjs.android.demo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.android.widget_extra.toast.ICIToast;

public class ToastUtils {

    private final static String TAG = ToastUtils.class.getSimpleName();

    private static final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private static Toast mTestToast;

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    @SuppressLint("ShowToast")
    public static void showTestToast(final Context context) {
        if (context == null) {
            return;
        }
        getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mTestToast != null) {
                    mTestToast.cancel();
                }
                String content = "这是一个测试Toast";
                mTestToast = ICIToast.makeToastOnlyText(context, content, 1);
                mTestToast.setDuration(Toast.LENGTH_SHORT);
                Log.d(TAG, "showTestToast: [" + content + "]");
                mTestToast.show();
            }
        });
    }
}
