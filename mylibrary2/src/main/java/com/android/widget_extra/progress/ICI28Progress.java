package com.android.widget_extra.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.wjs.android.mylibrary2.R;

public class ICI28Progress extends View implements ICICommonView {
    private Paint paint;
    private int width, height;
    private int lineWidth, lineHeight;
    private int progress;
    private Scroller scroller;

    public ICI28Progress(Context context) {
        super(context);
        init();
    }

    public ICI28Progress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICI28Progress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        lineWidth = w - getPaddingLeft() - getPaddingRight();
        lineHeight = h - getPaddingTop() - getPaddingBottom();
        if (w != 0) {
            float linePrecent = w / 100f;
            int scollX = (int) (-1 * linePrecent * progress);
            if (isAnimation) {
                scroller.startScroll(scroller.getCurrX(), 0, scollX, 0, 3000);
                isAnimation = false;
            } else {
                scrollTo(scollX + w, 0);
            }
        }
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }

    /**
     * 逻辑如下
     * <--------100%进度条----------->初始滑动到该位置<--------0%进度条----------->
     * 从右往左滑动 进度越来越大
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //100%显示效果
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorLine);
        canvas.drawRect(getPaddingLeft(), getPaddingTop(), lineWidth, lineHeight, paint);


        paint.reset();
        paint.setAntiAlias(true);
        if (lightBitmap != null) {
            int width = lightBitmap.getWidth();
            canvas.drawBitmap(lightBitmap, lineWidth - width, getPaddingTop(), paint);

        }

        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(colorStoke);
        canvas.drawRect(lineWidth, getPaddingTop(), 2 * lineWidth, lineHeight, paint);

        paint.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorBg);
        canvas.drawRect(lineWidth, getPaddingTop() + 2, 2 * lineWidth, lineHeight - 2, paint);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX() + width, 0);
        }
    }

    public void setPrecent(int progress) {
        this.progress = progress;
        if (width != 0) {
            float linePrecent = width / 100f;
            int scollX = (int) (-1 * linePrecent * progress);
            scrollTo(scollX + width, 0);
        }
    }

    private boolean isAnimation;

    public void playtoProgress(int progress, int duration) {

        this.progress = progress;
        isAnimation = true;
//        scrollBy(100,0);
        if (width != 0) {
            float linePrecent = width / 100f;
            int scollX = (int) (-1 * linePrecent * progress);

            scroller.startScroll(scroller.getCurrX(), 0, scollX, 0, duration);
            isAnimation = false;
        }
    }

    private int colorLine;
    private int colorStoke;
    private int colorBg;
    private Bitmap lightBitmap;

    @Override
    public void initCommon() {
        paint = new Paint();
        paint.setAntiAlias(true);
        scroller = new Scroller(getContext(), new LinearInterpolator());
    }

    @Override
    public void initCher() {
        colorStoke = 0xFF16171A;
        colorLine = 0xFF4C81FF;
        colorBg = 0xFF454D60;
        lightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ici28c_progress_loadingbarpercent_head);
    }

    @Override
    public void initBuck() {
        colorStoke = 0xFF16171A;
        colorLine = 0xFFF4DAA5;
        colorBg = 0xFF454D60;
        lightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ici28b_silder_progress_highlight);
    }
}
