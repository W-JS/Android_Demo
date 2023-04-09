package com.android.widget_extra.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.wjs.android.demo.R;

public class SliderColorView extends View implements ICICommonView {
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    private int direction = DIRECTION_RIGHT;
    private Paint mPaint;
    private int width, height;
    private Bitmap yellowBitmap;
    private Matrix matrix;

    public SliderColorView(Context context) {
        super(context);
        init();
    }


    public SliderColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SliderColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
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
        mPaint.setAntiAlias(true);
        mPaint.setColor(backColor);
        canvas.drawRect(0, 0, width, height, mPaint);
        mPaint.setColor(lineColor);
        canvas.drawRect(1, 1, width - 1, height - 1, mPaint);

        if (direction == DIRECTION_RIGHT) {
            if (width >= lightWidth) {
                canvas.drawBitmap(yellowBitmap, width - lightWidth, 0, mPaint);
            } else {
                float scal = (width * 1f) / lightWidth;
                matrix.reset();
                matrix.postScale(scal, 1);
                canvas.drawBitmap(yellowBitmap, matrix, mPaint);
            }
        } else {
            if (width >= lightWidth) {
                matrix.reset();
                matrix.postRotate(180, yellowBitmap.getWidth() / 2, yellowBitmap.getHeight() / 2);
                canvas.drawBitmap(yellowBitmap, matrix, mPaint);
            } else {
                float scal = (width * 1f) / lightWidth;
                matrix.reset();
                matrix.preScale(scal, 1, lightWidth, 8);
                matrix.postRotate(180, yellowBitmap.getWidth() / 2, yellowBitmap.getHeight() / 2);
                canvas.drawBitmap(yellowBitmap, matrix, mPaint);
            }

        }

    }

    public void setDirection(int direction) {
        this.direction = direction;
        postInvalidate();
    }

    @Override
    public void initCommon() {
        mPaint = new Paint();

        matrix = new Matrix();
    }

    private int lightWidth;
    private int backColor;
    private int lineColor;

    @Override
    public void initCher() {
        yellowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ici28c_slider_head);
        lightWidth = 150;
        backColor = 0xFF2125BE;
        lineColor = 0xFF4C81FF;
    }

    @Override
    public void initBuck() {
        yellowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ici28b_silder_progress_highlight);
        lightWidth = 240;
        backColor = 0xB3454D60;
        lineColor = 0xB3454D60;
    }
}
