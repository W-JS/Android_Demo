package com.android.widget_extra.button;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;

//import com.android.internal.R;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.utils.ICDrawableUtils;
import com.android.widget_extra.utils.ICITypeUtils;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.wjs.android.demo.R;

@SuppressLint("AppCompatCustomView")
public class ICIFlatButton extends Button implements ICICommonView {
    public static final int COLOR_WHITE = 0;
    public static final int COLOR_ORANGE = 1;
    public static final int COLOR_BLUE = 3;
    private int colorType = COLOR_ORANGE;
    public Drawable normalDrawable;
    public Drawable pressDrawable;
    public Drawable disableDrawable;
    private StateListAnimator stateListAnimator;
    private boolean initAnim = false;

    public ICIFlatButton(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ICIFlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ICIFlatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public ICIFlatButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        R.drawable.ici_button_flat_pressed
        ICICommonViewManager.viewInit(this, getContext());

    }

    public void initCommon() {
        setGravity(Gravity.CENTER);
        setMaxWidth(800);
        setMinWidth(204);
        setMaxHeight(80);
        setMinHeight(80);
        setAllCaps(false);
    }

    public void initBuck() {
        normalDrawable = ICDrawableUtils.getDrawable(0x00FFFFFF, 4, 0x00FFFFFF);
        pressDrawable = ICDrawableUtils.getDrawable(0x4D77859C, 4, 0x00FFFFFF);
        ;
        disableDrawable = ICDrawableUtils.getDrawable(0x00FFFFFF, 4, 0x00FFFFFF);
        ;
        StateListDrawable listDrawable = ICDrawableUtils.getPressSelector(normalDrawable, pressDrawable, disableDrawable);
        setBackground(listDrawable);
//        setBackgroundResource(R.drawable.ici_button_flatbutton_status);
        setColor(colorType);
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        setTextColor(getResources().getColorStateList(R.color.ici28b_button_flatbutton_color_text_orange, null));
        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_flatbutton_press_animation);

    }

    public void initCher() {
        normalDrawable = ICDrawableUtils.getDrawable(0x00283861, 4, 0x00FFFFFF);
        pressDrawable = ICDrawableUtils.getDrawable(0xCC283861, 4, 0x809BB2EC);
        ;
        disableDrawable = ICDrawableUtils.getDrawable(0x00FFFFFF, 4, 0x00FFFFFF);
        ;
        StateListDrawable listDrawable = ICDrawableUtils.getPressSelector(normalDrawable, pressDrawable, disableDrawable);

        setBackground(listDrawable);
//        setBackgroundResource(R.drawable.ici_button_flatbutton_status);
        setColor(colorType);
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        setTextColor(getResources().getColorStateList(R.color.ici28c_button_flatbutton_color_text_orange, null));
        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_flatbutton_press_animation);

    }

    @Deprecated
    public void setColor(int colorType) {
        if (ICITypeUtils.getCarType(getContext()) == ICITypeUtils.TYPE_BUCK) {
            setTextColor(getResources().getColorStateList(R.color.ici28b_button_flatbutton_color_text, null));
            return;
        }
        switch (colorType) {
            case COLOR_WHITE:
                setTextColor(Color.WHITE);
                break;
            case COLOR_ORANGE:

                break;
            case COLOR_BLUE:
                setTextColor(getResources().getColorStateList(R.color.ici28c_button_flatbutton_color_text_blue, null));
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!initAnim && stateListAnimator != null) {
                    initAnim = true;
                    setStateListAnimator(stateListAnimator);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setBackgroundAlpha(int progress) {
        progress = progress * 10;
        int stoke = 0x80 / 25 * progress;
        Drawable normalDrawable2 = ICDrawableUtils.getDrawable(Color.argb(progress, 28, 38, 61), 4, 0x809BB2EC);
        ;
        setBackground(normalDrawable2);
        if (progress == 0) {
            StateListDrawable listDrawable = ICDrawableUtils.getPressSelector(normalDrawable, pressDrawable, disableDrawable);
            setBackground(listDrawable);
        }
    }
}
