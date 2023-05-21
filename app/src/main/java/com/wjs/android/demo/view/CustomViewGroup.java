package com.wjs.android.demo.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.wjs.android.demo.R;

/**
 * @Description 自定义ViewGroup的实现
 * @Date 2023/5/21 20:40:45
 * @Author jinshengw
 * 参考: https://blog.csdn.net/linmiansheng/article/details/17934397
 * 参考: https://blog.csdn.net/linmiansheng/article/details/18031465
 */
public class CustomViewGroup extends ViewGroup implements View.OnTouchListener {

    private final static String TAG = CustomViewGroup.class.getSimpleName();

    private final int mPadding = 10;

    /**
     * 滑动的速度大于 200ms 的时候，手指松开，屏幕会自动滑向下一屏或者上一屏。
     */
    private final int mSnapVelocity = 200;

    /**
     * 共 3 列，每列 3 行
     */
    private final int mRows = 3;

    private Scroller mScroller;

    private float mDownX;

    private float mCurX;

    private int mDistanceX;

    private int mScreenWidth;

    private VelocityTracker mVelocityTracker;

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "CustomViewGroup: ");
        init(context);
    }

    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public void init(Context context) {
        Log.d(TAG, "init: ");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        // 获得屏幕宽度
        mScreenWidth = displayMetrics.widthPixels;

        // 获取scroller实例
        mScroller = new Scroller(context);

        // 设置触摸监听
        setOnTouchListener(this);

        //添加图片
        addCustomRotateView(context, R.drawable.photo1, 0, Color.YELLOW);
        addCustomRotateView(context, R.drawable.photo2, 30, Color.CYAN);
        addCustomRotateView(context, R.drawable.photo3, 45, Color.DKGRAY);

        addCustomRotateView(context, R.drawable.photo4, 60, Color.BLUE);
        addCustomRotateView(context, R.drawable.photo5, 75, Color.GREEN);
        addCustomRotateView(context, R.drawable.photo6, 90, Color.LTGRAY);

        addCustomRotateView(context, R.drawable.photo1, -30, Color.MAGENTA);
        addCustomRotateView(context, R.drawable.photo2, -45, Color.RED);
        addCustomRotateView(context, R.drawable.photo3, -60, Color.GRAY);
    }

    private void addCustomRotateView(Context context, int resId, float degree, int color) {
        addView(new CustomView(context, resId, degree, color));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure: widthMeasureSpec: " + widthMeasureSpec + ",heightMeasureSpec: " + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        parentHeight -= mPadding * childCount;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(parentHeight / childCount * mRows, MeasureSpec.AT_MOST);
            Log.d(TAG, "onMeasure: measureChild: widthMeasureSpec: " + widthMeasureSpec + ",childHeightMeasureSpec: " + childHeightMeasureSpec);
            measureChild(child, widthMeasureSpec, childHeightMeasureSpec);
        }
    }

    /**
     * 布局函数，将图片从左向右排列，每列 3 个
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout: changed: " + changed + ",left: " + left + ",top: " + top + ",right: " + right + ",bottom: " + bottom);
        int startX = 0;
        int startY = 0;
        int childCount = getChildCount();
        boolean flagVertical = true;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            int childLeft = startX;
            int childTop = startY;
            int childRight = childLeft + measuredWidth;
            int childBottom = startY + measuredHeight;
            Log.d(TAG, "onLayout: child: childLeft: " + childLeft + ",childTop: " + childTop + ",childRight: " + childRight + ",childBottom: " + childBottom);
            child.layout(childLeft, childTop, childRight, childBottom);
            if (flagVertical) {
                if ((i + 1) % mRows == 0) {
                    startX += measuredWidth;
                    flagVertical = false;
                    startY = 0;
                } else {
                    startY += measuredHeight + mPadding;
                }
            } else {
                flagVertical = true;
                startY += measuredHeight + mPadding;
            }
        }
    }

    /**
     * 重载View的computeScroll函数
     */
    @Override
    public void computeScroll() {
        // 滚动是否结束，返回true表明滚动还未结束，同时在函数里获取 mCurrX 和 mCurrY 的值
        if (mScroller.computeScrollOffset()) {
            // 滚动到当前位置
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 强制重新绘制 View,在框架里,View 的绘制会重新调用到 computeScroll 方法，达到循环绘制的目的
            postInvalidate();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 将事件添加到 mVelocityTracker，以计算斜坡的速度
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 当停止动画的时候，它会马上滚动到终点，然后将动画设置为结束。
                if (mScroller != null && !mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mDownX = event.getX();
                mCurX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                mDistanceX = (int) (moveX - mCurX);
                scrollBy(-mDistanceX, 0);
                mCurX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                float upX = event.getX();
                mDistanceX = (int) (upX - mDownX);
                mVelocityTracker.computeCurrentVelocity(1000);
                float vx = mVelocityTracker.getXVelocity();

                if (getScrollX() < 0) {
                    // 向右滚动（第一个屏幕）
                    mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0, 1000);
                } else if (getScrollX() > mScreenWidth * (getChildCount() / 3 - 1)) {
                    // 向左滚动
                    scrollToScreen(Direction.Current);
                } else {
                    if (mDistanceX > 0) {
                        // 向左滚动
                        if (mDistanceX > mScreenWidth / 2 || vx > mSnapVelocity) {
                            scrollToScreen(Direction.Current);
                        } else {
                            scrollToScreen(Direction.Next);
                        }
                    } else if (mDistanceX < 0) {
                        if (Math.abs(mDistanceX) > mScreenWidth / 2 || vx < -mSnapVelocity) {
                            // 向右滚动（下一个屏幕）
                            scrollToScreen(Direction.Next);
                        } else {
                            // 向左滚动（当前屏幕）
                            scrollToScreen(Direction.Current);
                        }
                    }
                }
//                mVelocityTracker.recycle();
                // 在这里一定要记得调用 invalidate() 来促使屏幕重绘，开始循环绘制的过程，不然。。。没有入口去滚。
                invalidate();
                break;
        }
        return true;
    }

    private void scrollToScreen(Direction to) {
        if (to == Direction.Next) {
            mScroller.startScroll(getScrollX(), 0, mScreenWidth - getScrollX() % mScreenWidth, 0, 1000);
        } else {
            mScroller.startScroll(getScrollX(), 0, -getScrollX() % mScreenWidth, 0, 1000);
        }
    }

    private enum Direction {
        Current, Next
    }
}
