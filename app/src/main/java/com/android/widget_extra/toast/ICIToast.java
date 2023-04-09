package com.android.widget_extra.toast;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.internal.R;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.utils.ICITypeUtils;
import com.android.widget_extra.utils.WidgetCoverityUtils;
import com.wjs.android.demo.R;

import java.lang.reflect.Field;

public class ICIToast {
    public static Toast makeToastWithAction(Context context, String content, String buttonName, int line, final View.OnClickListener onClickListener) {
        final Toast mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(36);
        int textWidth = (int) textPaint.measureText(content);

        int leftMrign = 282;
        int rightMrigin = 68;
        int maxTextWidth = 932;
        int minTextWidth = 370;
        int minWidth = 720;
        line = 1;
        int width = 0;
        int height = 0;
        if (textWidth <= minTextWidth) {
            width = minWidth;
        } else if (textWidth <= maxTextWidth) {
            width = textWidth + leftMrign + rightMrigin;
        } else {
            width = maxTextWidth;
            line = 2;
        }
        if (line == 1) {
            height = 104;
        } else {
            height = 154;
        }


        View v = getMainView(context);
        Button ICIFlatButton = v.findViewById(R.id.ici_toast_button);
        View splitView = v.findViewById(R.id.ici_toast_split);
        TextView textView = v.findViewById(R.id.ici_toast_content);
        ViewGroup viewGroup = v.findViewById(R.id.ici_toast_viewgroup);
        if (WidgetCoverityUtils.isNull(ICIFlatButton, splitView, textView, viewGroup)) {
            return Toast.makeText(context, content, Toast.LENGTH_LONG);
        }

        ICIFlatButton.setText(buttonName);
        ICIFlatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.cancel();
                onClickListener.onClick(v);
            }
        });
        ICIFlatButton.setVisibility(View.VISIBLE);

        splitView.setVisibility(View.VISIBLE);


        textView.setText(content);
        textView.setTypeface(com.android.widget_extra.utils.FontUtils.getDefaultFont());


        RelativeLayout.LayoutParams viewGroupparams = (RelativeLayout.LayoutParams) viewGroup.getLayoutParams();
        viewGroupparams.width = width;
        viewGroupparams.height = height;
        viewGroup.setLayoutParams(viewGroupparams);

        mToast.setView(v);
        makeToastTouchable(mToast, width + 20, height + 10);
        return mToast;
    }

    private static View getMainView(Context context) {
        View mainView = LayoutInflater.from(context).inflate(R.layout.ici_toast_button, null);
        if (WidgetCoverityUtils.isNull(mainView)) {
            return new View(context);
        }

        View bgParentView = mainView.findViewById(R.id.ici_toast_viewgroup);
        ImageView bgIV = mainView.findViewById(R.id.ici_toast_bg);
        Button actionBTN = mainView.findViewById(R.id.ici_toast_button);
        ImageView iconView = mainView.findViewById(R.id.ici_toast_icon);
        View splitView = mainView.findViewById(R.id.ici_toast_split);
        TextView contentText = mainView.findViewById(R.id.ici_toast_content);

        if (WidgetCoverityUtils.isNull(bgParentView, bgIV, actionBTN, iconView, splitView, contentText)) {

        }
        switch (ICITypeUtils.getCarType(context)) {
            case ICITypeUtils.TYPE_CHER:
                bgParentView.setBackgroundColor(Color.parseColor("#80000000"));
                bgIV.setImageResource(R.drawable.ici28c_toast_bg);
                actionBTN.setBackgroundResource(R.drawable.ici28c_toast_button_status);
                actionBTN.setTextColor(Color.parseColor("#FFFFB300"));
                splitView.setBackgroundColor(context.getResources().getColor(R.color.ici_toast_color_split));
                contentText.setTextColor(context.getResources().getColor(R.color.ici_toast_textcolor));
                contentText.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
                break;
            case ICITypeUtils.TYPE_BUCK:
//                bgParentView.setBackgroundColor(Color.parseColor("#66000000"));
                bgIV.setImageResource(R.drawable.ici28b_toast_bg);
                actionBTN.setBackgroundResource(R.drawable.ici28b_toast_button_status);
                actionBTN.setTextColor(Color.parseColor("#FFE5B586"));
                splitView.setBackgroundColor(Color.parseColor("#FF979797"));
                contentText.setTextColor(Color.parseColor("#FFFFFFFF"));
                contentText.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
                break;
        }
        return mainView;
    }

    public static ICIDilogToast makeDialogToastWithAction(Context context, String content, int time, String actionName, final View.OnClickListener onActionClick) {
        final ICIDilogToast mDialog = new ICIDilogToast(context, R.style.ici_dialog_style);
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(36);
        int textWidth = (int) textPaint.measureText(content);

        int leftMrign = 282;
        int rightMrigin = 68;
        int maxTextWidth = 932;
        int minTextWidth = 370;
        int minWidth = 720;
        int line = 1;
        int width = 0;
        int height = 0;
        if (textWidth <= minTextWidth) {
            width = minWidth;
        } else if (textWidth <= maxTextWidth) {
            width = textWidth + leftMrign + rightMrigin;
        } else {
            width = maxTextWidth;
            line = 2;
        }
        if (line == 1) {
            height = 104;
        } else {
            height = 154;
        }

        View rootView = getMainView(context);
        Button button = rootView.findViewById(R.id.ici_toast_button);
        View splitView = rootView.findViewById(R.id.ici_toast_split);
        TextView textView = rootView.findViewById(R.id.ici_toast_content);
        if (WidgetCoverityUtils.isNull(button, splitView, textView)) {
            return null;
        }

        button.setText(actionName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                onActionClick.onClick(v);
            }
        });
        button.setVisibility(View.VISIBLE);

        splitView.setVisibility(View.VISIBLE);


        textView.setText(content);


        Window window = mDialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(width + 20, height + 10);
        mDialog.setContentView(rootView, param);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;
        window.setDimAmount(0f);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                return false;
            }
        });
        mDialog.setTime(time);
        return mDialog;
    }


    public static ICIDilogToast makeDialogToastOnlyText(Context context, String content, int time) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(36);
        int textWidth = (int) textPaint.measureText(content);
        int leftMargin = 68;
        int rightMrigin = 68;

        int line = 1;
        int maxTextWidth = 1048;
        int minTextWidth = 384;
        line = 1;
        int width = 0;
        int height = 0;
        if (textWidth <= minTextWidth) {
            textWidth = minTextWidth;
        } else if (textWidth <= maxTextWidth) {
            textWidth = textWidth;
        } else {
            textWidth = maxTextWidth;
            line = 2;
        }


        if (line == 1) {
            height = 104;
        } else {
            height = 154;
        }
        width = textWidth + leftMargin + rightMrigin;


        View rootView = getMainView(context);


        TextView textView = rootView.findViewById(R.id.ici_toast_content);
        if (WidgetCoverityUtils.isNull(textView)) {
            return null;
        }
        textView.setText(content);
        textView.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.topMargin = 27;
        params.leftMargin = rightMrigin;
        params.rightMargin = rightMrigin;
        params.bottomMargin = 27;
        textView.setLayoutParams(params);

        final ICIDilogToast mDialog = new ICIDilogToast(context, R.style.ici_dialog_style);
        Window window = mDialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width + 20, height + 10);
        mDialog.setContentView(rootView, layoutParams);

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;
        window.setDimAmount(0f);
        window.setAttributes(windowAttributes);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                return false;
            }
        });
        mDialog.setTime(time);
        return mDialog;
    }


    public static Toast makeToastOnlyText(Context context, String content, int line) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(36);
        textPaint.setTypeface(FontUtils.getDefaultFont());
        int leftMargin = 68;
        int rightMrigin = 68;
        int textWidth = (int) textPaint.measureText(content);

        int maxTextWidth = 1048;
        int minTextWidth = 384;
        line = 1;
        int width = 0;
        int height = 0;
        if (textWidth <= minTextWidth) {
            textWidth = minTextWidth;
        } else if (textWidth <= maxTextWidth) {
            textWidth = textWidth;
        } else {
            textWidth = maxTextWidth;
            line = 2;
        }
        width = textWidth + leftMargin + rightMrigin;

        if (line == 1) {
            height = 104;
        } else {
            height = 154;
        }

        View v = getMainView(context);


        TextView textView = v.findViewById(R.id.ici_toast_content);
        ViewGroup viewGroup = v.findViewById(R.id.ici_toast_viewgroup);

        if (WidgetCoverityUtils.isNull(textPaint, viewGroup)) {
            return null;
        }

        textView.setText(content);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.topMargin = 27;
        params.leftMargin = leftMargin;
        params.rightMargin = rightMrigin;
        params.bottomMargin = 27;
        textView.setLayoutParams(params);


        RelativeLayout.LayoutParams viewGroupparams = (RelativeLayout.LayoutParams) viewGroup.getLayoutParams();
        viewGroupparams.width = width;
        viewGroupparams.height = height;
        viewGroup.setLayoutParams(viewGroupparams);

        Toast mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        mToast.setView(v);
        makeToastTouchable(mToast, width + 20, height + 10);
        return mToast;
    }

    public static Toast makeToastWithIcon(Context context, Drawable drawable, String content, int line) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(36);
        int textWidth = (int) textPaint.measureText(content);

        int leftMrign = 169;
        int rightMrigin = 53;
        int maxTextWidth = 1060;
        int minTextWidth = 298;
        int minWidth = 520;
        line = 1;
        int width = 0;
        int height = 0;
        if (textWidth <= minTextWidth) {
            width = minWidth;
        } else if (textWidth <= maxTextWidth) {
            width = textWidth + leftMrign + rightMrigin;
        } else {
            //两行为1036
            width = 1036;
            line = 2;
        }
        if (line == 1) {
            height = 104;
        } else {
            height = 154;
        }

        View v = getMainView(context);
        ImageView iconView = v.findViewById(R.id.ici_toast_icon);
        TextView textView = v.findViewById(R.id.ici_toast_content);
        ViewGroup viewGroup = v.findViewById(R.id.ici_toast_viewgroup);

        if (WidgetCoverityUtils.isNull(iconView, textPaint, viewGroup)) {
            return null;
        }

        iconView.setImageDrawable(drawable);
        iconView.setVisibility(View.VISIBLE);
        if (line == 2) {
            RelativeLayout.LayoutParams iconparams = (RelativeLayout.LayoutParams) iconView.getLayoutParams();
            iconparams.width = 100;
            iconparams.height = 100;
            iconparams.leftMargin = 48;
            iconparams.topMargin = 27;
            iconView.setLayoutParams(iconparams);
        }


        textView.setText(content);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.topMargin = 27;
        if (line == 1) {
            params.leftMargin = 169;
        } else {
            params.leftMargin = 178;
        }
        params.rightMargin = 53;
        params.bottomMargin = 27;
        textView.setLayoutParams(params);


        RelativeLayout.LayoutParams viewGroupparams = (RelativeLayout.LayoutParams) viewGroup.getLayoutParams();
        viewGroupparams.width = width;
        viewGroupparams.height = height;
        viewGroup.setLayoutParams(viewGroupparams);

        Toast mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        mToast.setView(v);
        makeToastTouchable(mToast, width + 20, height + 10);
        return mToast;
    }

    public static ICIDilogToast makeDialogToastWithIcon(Context context, Drawable drawable, String content, int time) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(36);
        int textWidth = (int) textPaint.measureText(content);

        int leftMrign = 169;
        int rightMrigin = 53;
        int maxTextWidth = 1060;
        int minTextWith = 298;
        int minWidth = 520;
        int line = 1;
        int width = 0;
        int height = 0;
        if (textWidth <= minTextWith) {
            width = minWidth;
        } else if (textWidth <= maxTextWidth) {
            width = textWidth + leftMrign + rightMrigin;
        } else {
            //两行为1036
            width = 1036;
            line = 2;
        }
        if (line == 1) {
            height = 104;
        } else {
            height = 154;
        }

        View rootView = getMainView(context);
        TextView textView = rootView.findViewById(R.id.ici_toast_content);
        ImageView iconView = rootView.findViewById(R.id.ici_toast_icon);

        if (WidgetCoverityUtils.isNull(textPaint, iconView)) {
            return null;
        }
        textView.setText(content);
        textView.setGravity(Gravity.LEFT);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.topMargin = 27;
        if (line == 1) {
            params.leftMargin = 169;
        } else {
            params.leftMargin = 178;
        }
        params.rightMargin = 53;
        params.bottomMargin = 27;
        textView.setLayoutParams(params);


        iconView.setImageDrawable(drawable);
        iconView.setVisibility(View.VISIBLE);
        if (line == 2) {
            RelativeLayout.LayoutParams iconparams = (RelativeLayout.LayoutParams) iconView.getLayoutParams();
            iconparams.width = 100;
            iconparams.height = 100;
            iconparams.leftMargin = 48;
            iconparams.topMargin = 27;
            iconView.setLayoutParams(iconparams);
        }

        final ICIDilogToast mDialog = new ICIDilogToast(context, R.style.ici_dialog_style);
        Window window = mDialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        ViewGroup.LayoutParams viewgroupLayoutParam = new ViewGroup.LayoutParams(width + 20, height + 10);
        mDialog.setContentView(rootView, viewgroupLayoutParam);

        WindowManager.LayoutParams param = window.getAttributes();
        param.gravity = Gravity.BOTTOM;
        window.setDimAmount(0f);
        window.setAttributes(param);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                return false;
            }
        });
        mDialog.setTime(time);
        return mDialog;
    }


    private static void makeToastTouchable(Toast mToast, int width, int height) {
        try {
            Object mTN;
            mTN = getField(mToast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams != null
                        && mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    //显示与隐藏动画
//                    params.windowAnimations = R.style.ClickToast;
                    //Toast可点击
                    params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                    //设置viewgroup宽高
                    params.width = width; //设置Toast宽度为屏幕宽度
                    params.height = height; //设置高度
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }

}
