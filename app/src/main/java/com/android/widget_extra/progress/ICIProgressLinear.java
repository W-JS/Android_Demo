package com.android.widget_extra.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.wjs.android.demo.R;

//import com.android.internal.R;

public class ICIProgressLinear extends View {
    private static final int STYLE_BAR = 1;
    private static final int STYLE_PERCENT = 2;

    private Paint mPaint;
    private int percent;
    private int width, height;
    private int gapWidth = 12;
    private int oldPercent;
    private LinearGradient linearGradient;
    private int style = STYLE_PERCENT;

    public ICIProgressLinear(Context context) {
        super(context);
        init();
    }

    public ICIProgressLinear(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIProgressLinear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIProgressLinear(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop() - getPaddingBottom();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int percentWidth = width / 100;
        int visiWidth = percentWidth * percent;
        int start = getPaddingLeft();
        int top = getPaddingTop();
        int right = getPaddingLeft() + width;
        int bottom = getPaddingTop() + height;

        //画背景
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.ici28_progress_linear_background, null));
        canvas.drawRect(getPaddingLeft(), getPaddingTop(), width + getPaddingLeft(), height + getPaddingTop(), mPaint);
//
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.ici28_progress_linear_line));
        mPaint.setStrokeWidth(30);
        if (style == STYLE_PERCENT) {

            Path linePath = new Path();
            int count = width / (2 * gapWidth);
            for (int i = 0; i < count; i++) {
                canvas.save();
                linePath.reset();
                linePath.moveTo(2 * i * gapWidth - gapWidth, 0);
                linePath.lineTo(2 * i * gapWidth - 2 * gapWidth, height);
                linePath.lineTo(2 * i * gapWidth - gapWidth, height);
                linePath.lineTo(2 * i * gapWidth, 0);
                canvas.clipPath(linePath);//截取路径所绘制的图形
                canvas.drawColor(getResources().getColor(R.color.ici28_progress_linear_line));
                canvas.restore();
            }
        }
//        canvas.restore();
//        //画进度
        if (oldPercent != percent) {
            linearGradient = new LinearGradient(start, top, visiWidth, top, new int[]{getResources().getColor(R.color.ici_progress_linear_start, null),
                    getResources().getColor(R.color.ici_progress_linear_end, null)}, new float[]{0, 1f}, Shader.TileMode.REPEAT);
            oldPercent = percent;
        }
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setShader(linearGradient);
        canvas.drawRect(start, top, visiWidth + start, bottom, mPaint);
    }

    public void setPercent(int percent) {
        this.percent = percent;
        postInvalidate();
    }
}
