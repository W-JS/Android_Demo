package com.android.widget_extra.button;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.Button;

import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.utils.ICDrawableUtils;
import com.android.widget_extra.utils.ICIColorUtils;
import com.wjs.android.mylibrary2.R;

@SuppressLint("AppCompatCustomView")
public class ICIRealButton extends Button implements ICICommonView {
    private StateListDrawable greenDrawable, redDrawable, blueDrawable, grayDrawable;
    public static final int COLOR_DEFAULT = 3;
    public static final int COLOR_GREEN = 1;
    public static final int COLOR_RED = 2;
    @Deprecated
    public static final int COLOR_GRAY = 3;
    public static final int COLOR_BLUE = 4;
    @Deprecated
    public static final int COLOR_YELLOW = 5;
    private int buttonColor = COLOR_DEFAULT;
    private StateListAnimator stateListAnimator;
    private boolean initAnima = false;

    public ICIRealButton(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ICIRealButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ICIRealButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public ICIRealButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

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

    public int getColorType() {
        return buttonColor;
    }

    public void setBackgroundColor(int color) {
        buttonColor = color;
        switch (buttonColor) {
            case COLOR_GREEN:
                setBackground(greenDrawable);
                break;
            case COLOR_RED:
                setBackground(redDrawable);
                break;
            case COLOR_GRAY:
                setBackground(grayDrawable);
                break;
            case COLOR_BLUE:
                setBackground(blueDrawable);
                break;
            case COLOR_YELLOW:
                break;
        }

    }

    @Override
    public void initCher() {
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        Drawable greenNormalDrawable = ICDrawableUtils.getGradientDrawable(new int[]{0xFF09531E, 0xCC051500}, 90, 0xFF20B74A);
        Drawable greenPressDrawable = ICDrawableUtils.getDrawable(0xE6085D20, 4, 0xFF29E65E);
        ;
        Drawable greenDisableDrawable = ICDrawableUtils.getGradientDrawable(new int[]{0xB209531E, 0xB2051500}, 90, 0xB220B74A);
        ;
        greenDrawable = ICDrawableUtils.getPressSelector(greenNormalDrawable, greenPressDrawable, greenDisableDrawable);

        Drawable redNormalDrawable = ICDrawableUtils.getGradientDrawable(new int[]{0xCC811516, 0xCC1D0101}, 90, 0xFFED2D30);
        Drawable redPressDrawable = ICDrawableUtils.getDrawable(0xE6731617, 4, 0xFFFF3134);
        ;
        Drawable redDisableDrawable = ICDrawableUtils.getGradientDrawable(new int[]{0xB2811516, 0xB21D0101}, 90, 0xB2ED2D30);
        ;
        redDrawable = ICDrawableUtils.getPressSelector(redNormalDrawable, redPressDrawable, redDisableDrawable);


        Drawable blueNormalDrawable = ICDrawableUtils.getGradientDrawable(new int[]{0xE116377D, 0xE6010921}, 0, 0x80A9C0F9);
        Drawable bluePressDrawable = ICDrawableUtils.getDrawable(0xE6203A7A, 4, 0x80C8D8FF);
        ;
        Drawable blueDisableDrawable = ICDrawableUtils.getGradientDrawable(new int[]{0xB216377D, 0xB2010921}, 0, 0xB2A9C0F9);
        ;
        blueDrawable = ICDrawableUtils.getPressSelector(blueNormalDrawable, bluePressDrawable, blueDisableDrawable);


        Drawable grayNormalDrawable = ICDrawableUtils.getDrawable(0x4D232938, 4, 0x809BB2EC);
        Drawable grayPressDrawable = ICDrawableUtils.getDrawable(0xCC283861, 4, 0x809BB2EC);
        ;
        Drawable grayDisableDrawable = ICDrawableUtils.getDrawable(0x4D232938, 4, 0x80B0BEE1);
        ;
        grayDrawable = ICDrawableUtils.getPressSelector(grayNormalDrawable, grayPressDrawable, grayDisableDrawable);

        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_realbutton_press_scal_animation);

        setBackgroundColor(COLOR_GRAY);
//        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
    }

    @Override
    public void initBuck() {
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        Drawable greenNormalDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_green_normal);
        Drawable greenPressDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_green_pressed);
        Drawable greenDisableDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_green_disable);
        greenDrawable = ICDrawableUtils.getPressSelector(greenNormalDrawable, greenPressDrawable, greenDisableDrawable);

        Drawable redNormalDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_red_normal);
        Drawable redPressDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_red_pressed);
        Drawable redDisableDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_red_disable);
        redDrawable = ICDrawableUtils.getPressSelector(redNormalDrawable, redPressDrawable, redDisableDrawable);


        Drawable blueNormalDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_blue_normal);
        Drawable bluePressDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_blue_pressed);
        Drawable blueDisableDrawable = getResources().getDrawable(R.drawable.ici28b_button_realbutton_blue_disable);
        blueDrawable = ICDrawableUtils.getPressSelector(blueNormalDrawable, bluePressDrawable, blueDisableDrawable);


        Drawable grayNormalDrawable = getResources().getDrawable(R.drawable.ici28b_button_ghostbutton_normal);
        Drawable grayPressDrawable = getResources().getDrawable(R.drawable.ici28b_button_ghostbutton_pressed);
        Drawable grayDisableDrawable = getResources().getDrawable(R.drawable.ici28b_button_ghostbutton_disable);
        grayDrawable = ICDrawableUtils.getPressSelector(grayNormalDrawable, grayPressDrawable, grayDisableDrawable);

        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_realbutton_press_scal_animation);

        setBackgroundColor(COLOR_GRAY);
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
    }

    @Override
    public void initCommon() {
        setTextColor(ICIColorUtils.getEnableColorSelector(Color.parseColor("#FFFFFFFF"), Color.parseColor("#33FFFFFF")));
        setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
        setAllCaps(false);
    }
}
