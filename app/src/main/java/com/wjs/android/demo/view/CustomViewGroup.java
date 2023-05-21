package com.wjs.android.demo.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wjs.android.demo.R;

/**
 * @Description 自定义ViewGroup的实现
 * @Date 2023/5/21 20:40:45
 * @Author jinshengw
 * 参考: https://blog.csdn.net/linmiansheng/article/details/17934397
 */
public class CustomViewGroup extends ViewGroup {

    private final static String TAG = CustomViewGroup.class.getSimpleName();

    private final int mPadding = 10;

    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "CustomViewGroup: ");
        init();
    }

    public void init() {
        Log.d(TAG, "init: ");

        CustomView customView1 = new CustomView(getContext(), R.drawable.photo1, 0, Color.YELLOW);
        addView(customView1);

        CustomView customView2 = new CustomView(getContext(), R.drawable.photo2, 30, Color.CYAN);
        addView(customView2);

        CustomView customView3 = new CustomView(getContext(), R.drawable.photo3, 45, Color.MAGENTA);
        addView(customView3);

        CustomView customView4 = new CustomView(getContext(), R.drawable.photo4, 60, Color.YELLOW);
        addView(customView4);

        CustomView customView5 = new CustomView(getContext(), R.drawable.photo5, -30, Color.CYAN);
        addView(customView5);

        CustomView customView6 = new CustomView(getContext(), R.drawable.photo6, -45, Color.MAGENTA);
        addView(customView6);
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
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(parentHeight / childCount, MeasureSpec.AT_MOST);
            Log.d(TAG, "onMeasure: measureChild: widthMeasureSpec: " + widthMeasureSpec + ",childHeightMeasureSpec: " + childHeightMeasureSpec);
            measureChild(child, widthMeasureSpec, childHeightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout: changed: " + changed + ",left: " + left + ",top: " + top + ",right: " + right + ",bottom: " + bottom);
        int startX = 0;
        int startY = 0;
        int childCount = getChildCount();
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
            startY += measuredHeight + mPadding;
        }
    }

}
