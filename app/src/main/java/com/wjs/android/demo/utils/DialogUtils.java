package com.wjs.android.demo.utils;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.android.widget_extra.dialog.ICICustomDialog;

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
     */
    public void showTestDialog() {
        if (isShowing()) {
            Log.d(TAG, "showTestDialog: mTestDialog.isShowing(): " + mTestDialog.isShowing());
            return;
        }

        boolean isZn = true;
        String content = "当前未使用默认主题，切换壁纸时将为您同步切换到默认主题。";
        String title = "继续切换壁纸？";
        String remind = "不再提醒";
        String button1Text = "继续";
        String button2Text = "取消";
        if (!isZn) {
            content = "Default theme is not \"Active\", and it will be applied at the same time if switch the wallpaper.";
            title = "Continue Switching Wallpaper？";
            remind = "Do not show again";
            button1Text = "Continue";
            button2Text = "Cancel";
        }
        mTestDialog = newDialog(content, title, remind, button1Text, button2Text);
        mTestDialog.show();
        Log.d(TAG, "showTestDialog: ");
    }

    private Dialog newDialog(String content, String title, String remind, String button1, String button2) {
        ICICustomDialog.Builder builder = new ICICustomDialog.Builder(mContext);
        if (content != null && !"".equals(content)) {
            builder.setContent(content);
        }
        if (title != null && !"".equals(title)) {
            builder.setTitle(title);
        }
        if (remind != null && !"".equals(remind)) {
            builder.setRemind(remind);
        }

        builder.setCheckBox(new ICICustomDialog.OnDialogButtonClickListener() {
            @Override
            public void onButtonClick(Dialog dialog, int which) {
                String msg = "";
                if (which == 3) {
                    msg = "已选中";
                } else if (which == 4) {
                    msg = "未选中";
                }
                Log.d(TAG, "onButtonClick: 复选框 " + msg);
            }
        });
        if (button1 != null && !"".equals(button1)) {
            builder.setFirstButton(button1, new ICICustomDialog.OnDialogButtonClickListener() {
                @Override
                public void onButtonClick(Dialog dialog, int which) {
                    Log.d(TAG, "onButtonClick: 继续");
                    dismissDialog();
                }
            }, true);
        }
        if (button2 != null && !"".equals(button2)) {
            builder.setSecondButton(button2, new ICICustomDialog.OnDialogButtonClickListener() {
                @Override
                public void onButtonClick(Dialog dialog, int which) {
                    Log.d(TAG, "onButtonClick: 取消");
                    dismissDialog();
                }
            });
        }

        //设置优先级  不设置默认为普通dialog
//        builder.setPrority(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
        return builder.build();
    }

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
