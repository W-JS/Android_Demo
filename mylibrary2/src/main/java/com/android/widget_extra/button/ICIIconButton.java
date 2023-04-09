package com.android.widget_extra.button;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.wjs.android.mylibrary2.R;

@SuppressLint("AppCompatCustomView")
public class ICIIconButton extends ImageView implements ICICommonView {
    private int width, height;
    private int size = 0;
    private Paint mPaint;
    private StateListAnimator stateListAnimator;
    private boolean initAnim = false;

    public ICIIconButton(Context context) {
        super(context);
        init();
    }

    public ICIIconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIIconButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!initAnim && stateListAnimator != null) {
                    initAnim = true;
                    setStateListAnimator(stateListAnimator);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int color = Color.argb(255 * size / 100, colorRgb[0], colorRgb[1], colorRgb[2]);
        int maxRadus = Math.min(width, height);
        int radus = (int) (size * maxRadus) / 100;
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, radus / 2, mPaint);
        super.onDraw(canvas);
    }

    public void setPressBackground(int size) {
        this.size = size;
        invalidate();
    }


    private Drawable getPressDrawable(int size) {
        GradientDrawable gradientDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.ici28c_iconbutton_bg, null);
        gradientDrawable.setSize(size, size);
        return gradientDrawable;
    }


    private int[] colorRgb = new int[3];

    @Override
    public void initCommon() {

    }

    @Override
    public void initCher() {
        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_iconbutton_press_animation);
        colorRgb[0] = 28;
        colorRgb[1] = 34;
        colorRgb[2] = 50;
    }

    @Override
    public void initBuck() {
        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_iconbutton_press_animation);
        colorRgb[0] = 0;
        colorRgb[1] = 0;
        colorRgb[2] = 0;
    }
}
