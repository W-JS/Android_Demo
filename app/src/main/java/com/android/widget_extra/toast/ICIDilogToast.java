package com.android.widget_extra.toast;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

//import com.yfvet.android.fgw.FgwManager;
//import com.yfvet.android.fgw.FgwStateResponseEventListener;

public class ICIDilogToast extends Dialog {
    private int time = 5000;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private View contentView;
    //    private  FgwManager mFgwManager;
    private boolean anyDismiss = false;

    public ICIDilogToast(Context context) {
        super(context);
    }

    public ICIDilogToast(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ICIDilogToast(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
//    private FgwStateResponseEventListener anyKeyListner;

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        contentView = view;
    }

    public View getContentView() {
        return contentView;
    }

    @Override
    public void show() {
        super.show();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isShowing()) {
                    dismiss();
                }
            }
        }, time);
        registerAnyKeyDismiss();
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }


    @Override
    protected void onStop() {
        super.onStop();
        unRegisterAnyKeyDismiss();
    }

    public ICIDilogToast setAnyKeyDismiss(boolean anykeyDismiss) {
        anyDismiss = anykeyDismiss;
        return this;
    }

    public ICIDilogToast registerAnyKeyDismiss() {
        if (!anyDismiss) {
            unRegisterAnyKeyDismiss();
            return this;
        }
//        if (anyKeyListner != null)
//        {
//            return this;
//        }
        Log.i("COMONUI_ICIDilogToast", "registerAnyKeyDismiss");
//        mFgwManager = (FgwManager) getContext().getSystemService("fgw");
//        anyKeyListner = new FgwStateResponseEventListener() {
//            @Override
//            public void onFgwResponseState(int key, int[] events, int i1) throws RemoteException {
//                if (key == 520)
//                {
//                    Log.i("COMONUI_ICIDilogToast","key = "+key+" events="+events);
//                    if (events !=null&&events.length>=1)
//                    {
//                        int keyCode = events[0];
//                        switch (keyCode)
//                        {
//                            case 1:
//                            case 2:
//                            case 3:
//                            case 4:
//                            case 5:
//                            case 6:
//                            case 7:
//                            case 8:
//                            case 9:
//                            case 10:
//                            case 11:
//                            case 12:
//                                if (ICIDilogToast.this.isShowing())
//                                {
//                                    ICIDilogToast.this.dismiss();
//                                }
//                                break;
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void activityBitMapCallback(Bitmap bitmap) throws RemoteException {
//
//            }
//        };
//        mFgwManager.RegisterFgwStateCallBack(anyKeyListner);
        return this;
    }

    private void unRegisterAnyKeyDismiss() {
//        if (anyKeyListner != null)
//        {
////            mFgwManager.UnRegisterFgwStateCallBack(anyKeyListner);
//            anyKeyListner = null;
//        }
    }

}
