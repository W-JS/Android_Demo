package com.android.widget_extra.dialog;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BoxShadowView extends View {
    private int width, height;
    private int top, bottom, left, right;
    private int radus = 100;
    private int color = 0x80FFFFFF;
    private Paint mPaint;

    public BoxShadowView(Context context) {
        super(context);
        init();
    }

    public BoxShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoxShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        mPaint.setMaskFilter(new BlurMaskFilter(radus, BlurMaskFilter.Blur.OUTER));
        top = 0;
        left = right = bottom = 10;
        canvas.drawRect(left, top, width - left, height - bottom, mPaint);
    }
}
