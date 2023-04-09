package com.android.widget_extra.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class ICIDialogBackground extends View {

    private int width, height;

    public ICIDialogBackground(Context context) {
        super(context);
    }

    public ICIDialogBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ICIDialogBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
