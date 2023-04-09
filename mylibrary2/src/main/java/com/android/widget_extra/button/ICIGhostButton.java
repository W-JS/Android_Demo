package com.android.widget_extra.button;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;

import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.utils.ICDrawableUtils;
import com.android.widget_extra.utils.ICIColorUtils;
import com.wjs.android.mylibrary2.R;

@SuppressLint("AppCompatCustomView")
public class ICIGhostButton extends Button implements ICICommonView {
    public Drawable normalDrawable;
    public Drawable pressDrawable;
    private Drawable disableDrawable;
    private StateListAnimator stateListAnimator;
    private StateListDrawable backgroundDrawable;
    private boolean initAnima = false;

    public ICIGhostButton(Context context) {
        super(context);
        init();
        ;
    }

    public ICIGhostButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIGhostButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIGhostButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, 0);
        init();
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!initAnima && stateListAnimator != null) {
                    initAnima = true;
                    setStateListAnimator(stateListAnimator);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setBackgroundAlpha(int progress) {
        final Drawable normalDrawable2 = ICDrawableUtils.getDrawable(ICIColorUtils.getGrandColor(0xCC283861, 0x4D232938, progress), 4, 0x809BB2EC);
        setBackground(normalDrawable2);
        if (progress == 100) {
            setBackground(backgroundDrawable);
        }

    }


    @Override
    public void initCher() {
        setTextColor(getResources().getColor(R.color.ici28c_color_text_status));
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        normalDrawable = ICDrawableUtils.getDrawable(0x4D232938, 4, 0x809BB2EC);
        pressDrawable = ICDrawableUtils.getDrawable(0xCC283861, 4, 0x809BB2EC);
        ;
        disableDrawable = ICDrawableUtils.getDrawable(0x4D232938, 4, 0x80B0BEE1);
        ;


        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_ghostbutton_press_scal_animation);

        backgroundDrawable = ICDrawableUtils.getPressSelector(normalDrawable, pressDrawable, disableDrawable);
        setBackground(backgroundDrawable);
    }

    @Override
    public void initBuck() {
        normalDrawable = getResources().getDrawable(R.drawable.ici28b_button_ghostbutton_normal);
        pressDrawable = getResources().getDrawable(R.drawable.ici28b_button_ghostbutton_pressed);
        disableDrawable = getResources().getDrawable(R.drawable.ici28b_button_ghostbutton_disable);

        setTextColor(getResources().getColor(R.color.ici28b_button_text_status));
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_ghostbutton_press_scal_animation);
        backgroundDrawable = ICDrawableUtils.getPressSelector(normalDrawable, pressDrawable, disableDrawable);
        setBackground(backgroundDrawable);
    }

    @Override
    public void initCommon() {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
        setGravity(Gravity.CENTER);
        setAllCaps(false);
    }
}
