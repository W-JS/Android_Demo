package com.android.widget_extra.card;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.wjs.android.mylibrary2.R;

//import com.android.internal.R;

public class ICICardFramentLayoutView extends FrameLayout {
    public static final int CONNECT_TRUE = 1;
    public static final int CONNECT_FALSE = 2;
    private int status;

    public ICICardFramentLayoutView(Context context) {
        super(context);
        init();
    }

    public ICICardFramentLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICICardFramentLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICICardFramentLayoutView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected void init() {
        setBackgroundResource(R.drawable.ici28c_card_bg_unconnected);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        switch (status) {
            case CONNECT_TRUE:
                setBackgroundResource(R.drawable.ici28c_card_bg_unconnected);
                break;
            case CONNECT_FALSE:
                setBackgroundResource(R.drawable.ici28c_card_bg_connected);
                break;
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setBackgroundResource(R.drawable.ici28c_card_bg_unconnected);
    }
}
