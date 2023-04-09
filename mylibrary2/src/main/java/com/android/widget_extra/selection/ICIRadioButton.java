package com.android.widget_extra.selection;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.mylibrary2.R;


@SuppressLint("AppCompatCustomView")
public class ICIRadioButton extends RadioButton implements ICICommonView {
    private boolean initAnim = false;
    private StateListAnimator stateListAnimator;
    private int[] animationUnchecked;
    private int butttonDrawResID;
    private int backDrawRedId;

    private static final int[] animationCherUnchecked = new int[]{
            R.drawable.ici28c_animation_radiobutton_turn_off_00001,
            R.drawable.ici28c_animation_radiobutton_turn_off_00002,
            R.drawable.ici28c_animation_radiobutton_turn_off_00003,
            R.drawable.ici28c_animation_radiobutton_turn_off_00004,
            R.drawable.ici28c_animation_radiobutton_turn_off_00005,
            R.drawable.ici28c_animation_radiobutton_turn_off_00006,
            R.drawable.ici28c_animation_radiobutton_turn_off_00007};
    private static final int[] animationBuckUnchecked = new int[]{
            R.drawable.ici28b_animation_radiobutton_turn_off_00001,
            R.drawable.ici28b_animation_radiobutton_turn_off_00002,
            R.drawable.ici28b_animation_radiobutton_turn_off_00003,
            R.drawable.ici28b_animation_radiobutton_turn_off_00004,
            R.drawable.ici28b_animation_radiobutton_turn_off_00005,
            R.drawable.ici28b_animation_radiobutton_turn_off_00006,
            R.drawable.ici28b_animation_radiobutton_turn_off_00007};

    private int[] animationChecked;

    private static int[] animationCherChecked = new int[]{
            R.drawable.ici28c_animation_radiobutton_turn_on_00001,
            R.drawable.ici28c_animation_radiobutton_turn_on_00002,
            R.drawable.ici28c_animation_radiobutton_turn_on_00003,
            R.drawable.ici28c_animation_radiobutton_turn_on_00004,
            R.drawable.ici28c_animation_radiobutton_turn_on_00005,
            R.drawable.ici28c_animation_radiobutton_turn_on_00006,
            R.drawable.ici28c_animation_radiobutton_turn_on_00007,
            R.drawable.ici28c_animation_radiobutton_turn_on_00008,
            R.drawable.ici28c_animation_radiobutton_turn_on_00009
    };

    private static int[] animationBuckChecked = new int[]{
            R.drawable.ici28b_animation_radiobutton_turn_on_00001,
            R.drawable.ici28b_animation_radiobutton_turn_on_00002,
            R.drawable.ici28b_animation_radiobutton_turn_on_00003,
            R.drawable.ici28b_animation_radiobutton_turn_on_00004,
            R.drawable.ici28b_animation_radiobutton_turn_on_00005,
            R.drawable.ici28b_animation_radiobutton_turn_on_00006,
            R.drawable.ici28b_animation_radiobutton_turn_on_00007,
            R.drawable.ici28b_animation_radiobutton_turn_on_00008,
            R.drawable.ici28b_animation_radiobutton_turn_on_00009
    };

    public ICIRadioButton(Context context) {
        super(context);
        init();
    }

    public ICIRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        ICICommonViewManager.viewInit(this, getContext());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (!initAnim&&stateListAnimator != null&&event.getAction() == MotionEvent.ACTION_UP)
//        {
//            setStateListAnimator(stateListAnimator);
//            initAnim = true;
//        }
        return super.onTouchEvent(event);
    }


    public void setUncheckDrawable(int drawable) {

        //setStateListAnimator时或第一次绘制将触发动效，现象为第一次进会触发uncheck动效，
        // 这里为了避免该问题，第一个uncheck动效不执行
        if (!initAnim) {
            if (drawable == 6) {
                initAnim = true;
            }
            return;
        }
        Drawable leftDrawable = getLayerDrawable(animationUnchecked[drawable]);
        leftDrawable.setBounds(0, 0, 52, 52);
        setCompoundDrawables(leftDrawable, null, null, null);
        if (drawable == 6) {
            leftDrawable = getResources().getDrawable(butttonDrawResID);

            //for coverity
            if (leftDrawable == null) {
                return;
            }

            leftDrawable.setBounds(0, 0, 52, 52);
            setCompoundDrawables(leftDrawable, null, null, null);
        }
    }

    public void setCheckedDrawable(int drawable) {
        if (!initAnim) {
            if (drawable == 8) {
                initAnim = true;
            }
            return;
        }
        Drawable leftDrawable = getLayerDrawable(animationChecked[drawable]);
        leftDrawable.setBounds(0, 0, 52, 52);
        setCompoundDrawables(leftDrawable, null, null, null);
        if (drawable == 8) {
            leftDrawable = getResources().getDrawable(butttonDrawResID);

            //for coverity
            if (leftDrawable == null) {
                return;
            }

            leftDrawable.setBounds(0, 0, 52, 52);
            setCompoundDrawables(leftDrawable, null, null, null);
        }
    }

    private LayerDrawable getLayerDrawable(int redId) {
        InsetDrawable insetDrawable = new InsetDrawable(getResources().getDrawable(redId, null), 1, 1, 1, 1);
        Drawable visiDrawable = getResources().getDrawable(backDrawRedId, null);
        return new LayerDrawable(new Drawable[]{visiDrawable, insetDrawable});
    }

    @Override
    public void initCommon() {
        setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
        setMinWidth(136);
        setMinHeight(104);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void initCher() {
        setTextColor(getResources().getColor(R.color.ici28c_color_text_status));
//        setTextSize(R.dimen.ici_text_button);
        butttonDrawResID = R.drawable.ici28c_radio_status;
        setTypeface(FontUtils.getDefaultFont());
        Drawable leftDrawable = getResources().getDrawable(butttonDrawResID);

        //for coverity
        if (leftDrawable == null) {
            return;
        }

        leftDrawable.setBounds(0, 0, 52, 52);
        setCompoundDrawables(leftDrawable, null, null, null);
        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_checkbox_animation);
        setStateListAnimator(stateListAnimator);
        animationUnchecked = animationCherUnchecked;
        animationChecked = animationCherChecked;
        backDrawRedId = R.drawable.ici28c_selectioncontrols_radiobutton_off_pressed;
    }

    @Override
    public void initBuck() {
        setTextColor(getResources().getColor(R.color.ici28b_color_text_status));
//        setTextSize(R.dimen.ici_text_button);
        butttonDrawResID = R.drawable.ici28b_radio_status;
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        Drawable leftDrawable = getResources().getDrawable(butttonDrawResID);

        //for coverity
        if (leftDrawable == null) {
            return;
        }

        leftDrawable.setBounds(0, 0, 52, 52);
        setCompoundDrawables(leftDrawable, null, null, null);
        stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_checkbox_animation);
        setStateListAnimator(stateListAnimator);
        animationUnchecked = animationBuckUnchecked;
        animationChecked = animationBuckChecked;
        backDrawRedId = R.drawable.ici28b_selectioncontrols_radiobutton_off_pressed;

    }
}
