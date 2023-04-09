package com.android.widget_extra.progress;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.wjs.android.mylibrary2.R;

public class ICISlider extends LinearLayout implements ICICommonView {
    private int viewWidth;
    private int leftNum, rightNum;
    private View leftView, rightView, leftEmptyView, rightEmptyView;
    private ImageView imageView2;
    private ViewGroup lineGroup;
    private int mIndex = Integer.MAX_VALUE;
    private float mLastX, mLastY, imgTouchx;
    private int oldIndex;
    private OnSliderImageLayoutChange onSliderImageLayoutChange;
    private OnSliderProgressChangeListner onSliderProgressChangeLisstner;
    private Animator pressAnimator, realseAnimator;
    private boolean canClickTouch = true;

    public ICISlider(Context context) {
        super(context);
        init();
    }

    public ICISlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICISlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICISlider(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());

    }


    public void setCanClickTouch(boolean canClickTouch) {
        this.canClickTouch = canClickTouch;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
//
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Rect rect = new Rect();
                imageView2.getHitRect(rect);
                if (rect.contains((int) ev.getX(), (int) ev.getY())) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:


                break;
        }
        return true;
    }

    private static final int TOUCH_MODE_NONE = 0;
    private static final int TOUCH_MODE_X = 1;
    private static final int TOUCH_MODE_Y = 2;
    private boolean isMove = false;
    private int touchMode;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!canClickTouch) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchMode = TOUCH_MODE_NONE;
                mLastX = event.getX();
                mLastY = event.getY();
                isMove = false;
                return true;
            case MotionEvent.ACTION_MOVE:
                float gap = event.getX() - mLastX;
                if (touchMode == TOUCH_MODE_NONE) {
                    if (Math.abs(gap) > 5 && Math.abs(gap) > Math.abs(event.getY() - mLastY)) {
                        touchMode = TOUCH_MODE_X;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        pressAnimator.start();
                    }
                }
                if (touchMode == TOUCH_MODE_X) {

                    isMove = true;
                    float size = (viewWidth - 60f) / (float) (Math.abs(leftNum) + rightNum);
                    int index = judgeIndex(event.getX());
                    setIndexInternel(index, true);
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (touchMode == TOUCH_MODE_X) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isMove) {
                    int index = judgeIndex(event.getX());
                    setIndexInternel(index, true);
                }
                if (touchMode == TOUCH_MODE_X) {
                    realseAnimator.start();
                    touchMode = TOUCH_MODE_NONE;
                }
                break;
        }
        return super.onTouchEvent(event);
    }


    public void setLineHeight(int width) {
        ViewGroup.LayoutParams layoutParams = lineGroup.getLayoutParams();
        layoutParams.height = width;
        lineGroup.setLayoutParams(layoutParams);
    }

    public ImageView getLineImageView() {
        return imageView2;
    }

    public void setAllItem(int left, int right) {
        leftNum = left;
        rightNum = right;
    }

    private int judgeIndex(float x) {
        int index = 0;
        int leftAbs = Math.abs(leftNum);
        float step = (viewWidth - 60f + (imageView2.getWidth() / 2)) / (rightNum + leftAbs);
        int selectIndex = (int) (x / step);
        if (x % step > step / 2) {
            selectIndex = selectIndex + 1;
        }
        if (selectIndex > leftAbs) {
            index = selectIndex - leftAbs;
//                    setIndexInternel(selectIndex-leftAbs,true);
        } else {
            index = (leftAbs - selectIndex) * -1;
//                    setIndexInternel((leftAbs-selectIndex)*-1,true);
        }
        return index;

    }


    public void setIndex(int index) {
        setIndexInternel(index, false);
    }

    private void setIndexInternel(int index, boolean isFromTouch) {

        if (index > rightNum) {
            index = rightNum;
        }

        if (index < leftNum) {
            index = leftNum;
        }
        if (this.mIndex == index) {
            return;
        }

        if (leftNum == 0 && rightNum == 0) {
            throw new RuntimeException("未初始化");
        }
        if (index >= 0) {

            leftView.setVisibility(GONE);

            LinearLayout.LayoutParams leftParams = (LayoutParams) leftEmptyView.getLayoutParams();
            leftParams.weight = Math.abs(leftNum);
            leftEmptyView.setLayoutParams(leftParams);

            rightView.setVisibility(VISIBLE);
            LinearLayout.LayoutParams rightParams = (LayoutParams) rightView.getLayoutParams();
            rightParams.weight = index;
            rightView.setLayoutParams(rightParams);

            LinearLayout.LayoutParams rightEmptyParams = (LayoutParams) rightEmptyView.getLayoutParams();
            rightEmptyParams.weight = rightNum - index;
            rightEmptyView.setLayoutParams(rightEmptyParams);

        } else if (index < 0) {
            int leftIndexAbs = -1 * index;
            rightView.setVisibility(GONE);
            LinearLayout.LayoutParams leftParams = (LayoutParams) rightEmptyView.getLayoutParams();
            leftParams.weight = rightNum;
            rightEmptyView.setLayoutParams(leftParams);

            leftView.setVisibility(VISIBLE);
            LinearLayout.LayoutParams rightParams = (LayoutParams) leftView.getLayoutParams();
            rightParams.weight = leftIndexAbs;
            leftView.setLayoutParams(rightParams);

            LinearLayout.LayoutParams rightEmptyParams = (LayoutParams) leftEmptyView.getLayoutParams();
            rightEmptyParams.weight = Math.abs(leftNum) - leftIndexAbs;
            leftEmptyView.setLayoutParams(rightEmptyParams);
        }
        mIndex = index;

        if (onSliderProgressChangeLisstner != null && isFromTouch) {
            onSliderProgressChangeLisstner.onSliderProgressChange(mIndex);
        }
    }

    public void setOnSliderProgressChangeListner(OnSliderProgressChangeListner onSliderProgressChangeLisstner) {
        this.onSliderProgressChangeLisstner = onSliderProgressChangeLisstner;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int leftMargin = 0;
        if (mIndex < 0) {
            leftMargin = leftView.getLeft();

        }
        if (mIndex == 0) {
            leftMargin = leftEmptyView.getRight();
        }
        if (mIndex > 0) {
            leftMargin = rightView.getRight();
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
        if (layoutParams.leftMargin != leftMargin) {
            layoutParams.leftMargin = leftMargin;
            imageView2.setLayoutParams(layoutParams);
            if (onSliderImageLayoutChange != null) {
                onSliderImageLayoutChange.onLayoutChange(leftMargin);
            }
        }
    }


    public void setOnSliderImageLayoutChange(OnSliderImageLayoutChange onSliderImageLayoutChange) {
        this.onSliderImageLayoutChange = onSliderImageLayoutChange;
    }

    @Override
    public void initCommon() {
        LayoutInflater.from(getContext()).inflate(R.layout.ici_slider_layout, this);
        leftView = findViewById(R.id.ici_slider_left);
        rightView = findViewById(R.id.ici_slider_right);
        leftEmptyView = findViewById(R.id.ici_slider_left_empty);
        rightEmptyView = findViewById(R.id.ici_slider_right_empty);
        lineGroup = findViewById(R.id.line_viewgroup);
        imageView2 = findViewById(R.id.ici_slider_image2);

        //for coverity
        if (imageView2 == null) {
            return;
        }


        imageView2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!canClickTouch) {
                    return false;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pressAnimator.start();
                        oldIndex = mIndex;
                        imgTouchx = event.getX();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        int index = judgeIndex(event.getX());
                        setIndexInternel(index, true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        realseAnimator.start();
                        break;
                }

                return true;
            }
        });

        if (leftView != null) {
            ((SliderColorView) leftView).setDirection(SliderColorView.DIRECTION_LEFT);
        }

        pressAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.ici28c_slider_press);
        pressAnimator.setTarget(imageView2);

        realseAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.ici28c_slider_realse);
        realseAnimator.setTarget(imageView2);
    }

    @Override
    public void initCher() {
        imageView2.setImageResource(R.drawable.ici28c_slider_bar_normal);
    }

    @Override
    public void initBuck() {
        imageView2.setImageResource(R.drawable.ici28b_slider_button_normal);
    }

    public interface OnSliderProgressChangeListner {
        void onSliderProgressChange(int newIndex);
    }

    public interface OnSliderImageLayoutChange {
        void onLayoutChange(int x);
    }
}
