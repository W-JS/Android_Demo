package com.android.widget_extra.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;

public class ICIVerticalScrollView extends ScrollView implements ICICommonView {
    private static final int PROGRESS_HIDE_TIME = 2000;
    private boolean visiProgress = false;
    private float viewWidth;
    private float viewHeight;
    private Paint paint;
    private float measureHeight;
    private final static int HIDE_PROGRESS = 1;
    private boolean isProgressHideAble = true;
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

    public ICIVerticalScrollView(Context context) {
        super(context);
        init();
    }

    public ICIVerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
        paint = new Paint();
        paint.setAntiAlias(true);
        setVerticalScrollBarEnabled(false);
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildAt(0) != null) {
            measureHeight = getChildAt(0).getMeasuredHeight();
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }


    public void setProgressHideAble(boolean progressHideAble) {
        isProgressHideAble = progressHideAble;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isProgressHideAble && !visiProgress) {
            return;
        }
        if (viewHeight >= measureHeight) {
            //当前界面未超过一屏，不需要滑动条
            return;
        }
        int scrollY = getScrollY();
        float sizePrecenter = viewHeight / measureHeight;
        float lineBgHeight = viewHeight - 40;
        float lineHeight = lineBgHeight * sizePrecenter;
        float lineCanScrollHeight = lineBgHeight - lineHeight;
        float canScrollHeight = measureHeight - viewHeight;
        float scrollPrecent = getScrollY() / canScrollHeight;
        float lineScrollwidth = lineCanScrollHeight * scrollPrecent;
        paint.setColor(lineColor);
        float start = viewWidth - 19;
        float top = 20;
        float right = start + 3;
        float bottom = top + lineHeight;
        canvas.drawRect(start, top + scrollY + lineScrollwidth, right, bottom + scrollY + lineScrollwidth, paint);
    }

    private int lineColor;

    @Override
    public void initCommon() {

    }

    @Override
    public void initCher() {
        lineColor = 0xFF4E5A79;
    }

    @Override
    public void initBuck() {
        lineColor = 0xFF525868;
    }
}
