package com.android.widget_extra.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.wjs.android.mylibrary2.R;

public class ICIProgressCircular extends ImageView implements ICICommonView {
    public static int SIZE_BIG = 170;
    public static int SIZE_MEDIUM = 98;
    public static int SIZE_SMALL = 60;
    private RotateAnimation rotate;
    private int bigDrawable, midDrawable, smallDrawable;

    public ICIProgressCircular(Context context) {
        super(context);
        init();
    }

    public ICIProgressCircular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIProgressCircular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIProgressCircular(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int min = Math.min(w, h);
        if (min >= SIZE_BIG) {
            setImageResource(bigDrawable);
        } else if (min >= SIZE_MEDIUM) {
            setImageResource(midDrawable);
        } else {
            setImageResource(bigDrawable);
        }
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }

    public void start() {
        if (rotate == null) {
            rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            LinearInterpolator lin = new LinearInterpolator();
            rotate.setInterpolator(lin);
            rotate.setDuration(1000);//设置动画持续时间
            rotate.setRepeatCount(-1);//设置重复次数
            rotate.setFillAfter(false);//动画执行完后是否停留在执行完的状态
            rotate.setStartOffset(10);//执行前的等待时间
        }
        setAnimation(rotate);
        startAnimation(rotate);
    }

    public void stop() {
        if (rotate != null) {
            rotate.cancel();
        }
    }

    @Override
    public void initCommon() {
        setScaleType(ScaleType.FIT_XY);
    }

    @Override
    public void initCher() {
        bigDrawable = R.drawable.ici28c_progress_circular_indeterminate_large;
        midDrawable = R.drawable.ici28c_progress_circular_indeterminate_medium;
        smallDrawable = R.drawable.ici28c_progress_circular_indeterminate_small;
    }

    @Override
    public void initBuck() {
        bigDrawable = R.drawable.ici28b_progress_circular_indeterminate_large;
        midDrawable = R.drawable.ici28b_progress_circular_indeterminate_medium;
        smallDrawable = R.drawable.ici28b_progress_circular_indeterminate_small;
    }
}
