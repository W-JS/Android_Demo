package com.wjs.android.demo.utils;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.android.widget_extra.dialog.ICIDialog;

public class DialogUtils {

    private final static String TAG = DialogUtils.class.getSimpleName();

    private Context mContext;
    private static DialogUtils instance;
    private Dialog mTestDialog = null;

    private DialogUtils(Context context) {
        mContext = context;
    }

    public static DialogUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DialogUtils(context);
        }
        return instance;
    }


    /**
     * 显示更新主题Dialog
     *
     * @param content
     */
    public void showTestDialog(String content) {
        if (isShowing()) {
            Log.d(TAG, "showTestDialog: mTestDialog.isShowing(): " + mTestDialog.isShowing());
            return;
        }
        mTestDialog = new ICIDialog.Builder(mContext)
                .setContent(content, 1)
                .setFirstButton("确定", onThemeDialogUpdateButtonClickLinster, true)
                .setSecondButton("取消", onThemeDialogLaterButtonClickLinster)
                //背景透明度
                .setDimAmount(0.7f)
                .build();
        mTestDialog.show();
        Log.d(TAG, "showTestDialog: ");
    }

    private ICIDialog.OnDialogButtonClickLinster onThemeDialogUpdateButtonClickLinster = new ICIDialog.OnDialogButtonClickLinster() {
        @Override
        public void onButtonClick(Dialog dialog, int i) {
            Log.d(TAG, "onButtonClick: 确定");
            dismissDialog();
        }
    };

    private ICIDialog.OnDialogButtonClickLinster onThemeDialogLaterButtonClickLinster = new ICIDialog.OnDialogButtonClickLinster() {
        @Override
        public void onButtonClick(Dialog dialog, int i) {
            Log.d(TAG, "onButtonClick: 取消");
            dismissDialog();
        }
    };

    public boolean isShowing() {
        if (mTestDialog == null) {
            Log.d(TAG, "isShowing: mTestDialog == null");
            return false;
        }
        boolean showing = mTestDialog.isShowing();
        Log.d(TAG, "isShowing: mTestDialog: " + showing);
        return showing;
    }

    public void dismissDialog() {
        if (isShowing()) {
            mTestDialog.dismiss();
            mTestDialog = null;
            Log.d(TAG, "dismissDialog: ");
        }
    }

}
