package com.android.widget_extra.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ICIListTextView extends TextView {
    private OnVisiableChangeLisnter onVisiableChangeLisnter;

    public ICIListTextView(Context context) {
        super(context);
    }

    public ICIListTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ICIListTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ICIListTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (onVisiableChangeLisnter != null) {
            onVisiableChangeLisnter.onViewVisichange();
        }
    }

    public void setOnVisiableChangeLisnter(OnVisiableChangeLisnter onVisiableChangeLisnter) {
        this.onVisiableChangeLisnter = onVisiableChangeLisnter;
    }

    interface OnVisiableChangeLisnter {
        void onViewVisichange();
    }
}
