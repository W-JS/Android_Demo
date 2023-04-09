package com.android.widget_extra.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;

public class ICIHorizontalScrollView extends HorizontalScrollView implements ICICommonView {
    private static final int PROGRESS_HIDE_TIME = 2000;
    private boolean visiProgress = false;
    private float lineBGWidth = 180;
    private float lineHeight = 3;
    private float viewWidth;
    private float viewHeight;
    private Paint paint;
    private float measureWidth;
    private int mMaxXOverscrollDistance;
    private final static int HIDE_PROGRESS = 1;
    private boolean isHideAble = true;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HIDE_PROGRESS:
                    visiProgress = false;
                    invalidate();
                    break;
            }
        }
    };

    public ICIHorizontalScrollView(Context context) {
        super(context);
        init();
    }

    public ICIHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildAt(0) != null) {
            measureWidth = getChildAt(0).getMeasuredWidth();
        }
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, mMaxXOverscrollDistance, maxOverScrollY, isTouchEvent);
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
        paint = new Paint();
        paint.setAntiAlias(true);
        setHorizontalScrollBarEnabled(false);
        mMaxXOverscrollDistance = 80;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    public void setHideAble(boolean hideAble) {
        isHideAble = hideAble;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        visiProgress = true;
        Message message = handler.obtainMessage(HIDE_PROGRESS);
        handler.removeMessages(HIDE_PROGRESS);
        handler.sendMessageDelayed(message, PROGRESS_HIDE_TIME);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        paint.setColor(lineBackColor);
        if (measureWidth == 0 || viewWidth == 0) {
            return;
        }
        if (isHideAble && !visiProgress) {
            return;
        }
        if (viewWidth >= measureWidth) {
            //当前界面未超过一屏，不需要滑动条
            return;
        }
        float sizePrecenter = viewWidth / measureWidth;
        float linewidth = 180 * sizePrecenter;
        float lineCanScrollWidth = lineBGWidth - linewidth;
        float canScrollWidth = measureWidth - viewWidth;
        float scrollPrecent = getScrollX() / canScrollWidth;
        float lineScrollwidth = lineCanScrollWidth * scrollPrecent;
        int x = getScrollX();
        int linestart = (int) (viewWidth / 2 - lineBGWidth / 2);
        int lineTop = (int) (viewHeight - 20);
        int lineright = (int) (viewWidth / 2 + lineBGWidth / 2);
        int lineBottom = (int) (viewHeight - 17);
        canvas.drawRect(x + linestart, lineTop, x + lineright, lineBottom, paint);
        paint.setColor(lineColor);
        canvas.drawRect(x + linestart + lineScrollwidth, lineTop, x + linestart + lineScrollwidth + linewidth, lineBottom, paint);
    }

    private int lineColor, lineBackColor;

    @Override
    public void initCommon() {

    }

    @Override
    public void initCher() {
        lineColor = 0xFFB0BEBE;
        lineBackColor = 0x4DB0BEE1;
    }

    @Override
    public void initBuck() {
        lineColor = 0x33525868;
        lineBackColor = 0xFF525868;
    }
}
