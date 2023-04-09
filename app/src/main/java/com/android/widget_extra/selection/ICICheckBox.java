package com.android.widget_extra.selection;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.CheckBox;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.demo.R;

@SuppressLint("AppCompatCustomView")
public class ICICheckBox extends CheckBox implements ICICommonView {
    private int[] animationUnchecked;
    private static final int[] animationCHERUnchecked = new int[]{
            R.drawable.ici28c_animation_checkbox_turn_off_00001,
            R.drawable.ici28c_animation_checkbox_turn_off_00002,
            R.drawable.ici28c_animation_checkbox_turn_off_00003,
            R.drawable.ici28c_animation_checkbox_turn_off_00004,
            R.drawable.ici28c_animation_checkbox_turn_off_00005,
            R.drawable.ici28c_animation_checkbox_turn_off_00006,
            R.drawable.ici28c_animation_checkbox_turn_off_00007};
    private static final int[] animationBuckUnchecked = new int[]{
            R.drawable.ici28b_animation_checkbox_turn_off_00001,
            R.drawable.ici28b_animation_checkbox_turn_off_00002,
            R.drawable.ici28b_animation_checkbox_turn_off_00003,
            R.drawable.ici28b_animation_checkbox_turn_off_00004,
            R.drawable.ici28b_animation_checkbox_turn_off_00005,
            R.drawable.ici28b_animation_checkbox_turn_off_00006,
            R.drawable.ici28b_animation_checkbox_turn_off_00007};

    private int[] animationChecked;

    private static int[] animationCherChecked = new int[]{
            R.drawable.ici28c_animation_checkbox_turn_on_00001,
            R.drawable.ici28c_animation_checkbox_turn_on_00002,
            R.drawable.ici28c_animation_checkbox_turn_on_00003,
            R.drawable.ici28c_animation_checkbox_turn_on_00004,
            R.drawable.ici28c_animation_checkbox_turn_on_00005,
            R.drawable.ici28c_animation_checkbox_turn_on_00006,
            R.drawable.ici28c_animation_checkbox_turn_on_00007,
            R.drawable.ici28c_animation_checkbox_turn_on_00008,
            R.drawable.ici28c_animation_checkbox_turn_on_00009
    };

    private static int[] animationBuckChecked = new int[]{
            R.drawable.ici28b_animation_checkbox_turn_on_00001,
            R.drawable.ici28b_animation_checkbox_turn_on_00002,
            R.drawable.ici28b_animation_checkbox_turn_on_00003,
            R.drawable.ici28b_animation_checkbox_turn_on_00004,
            R.drawable.ici28b_animation_checkbox_turn_on_00005,
            R.drawable.ici28b_animation_checkbox_turn_on_00006,
            R.drawable.ici28b_animation_checkbox_turn_on_00007,
            R.drawable.ici28b_animation_checkbox_turn_on_00008,
            R.drawable.ici28b_animation_checkbox_turn_on_00009
    };

    public ICICheckBox(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ICICheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ICICheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public ICICheckBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }


    public void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        ICICommonViewManager.viewInit(this, getContext());


    }


    private boolean isChange;

    @Override
    public void setChecked(boolean checked) {
        if (checked == isChecked()) {
            isChange = true;
        } else {
            isChange = false;
        }
        super.setChecked(checked);

    }

    public void setUncheckDrawable(int drawable) {
        if (isChange) {
            return;
        }
        Drawable leftDrawable = getLayerDrawable(animationUnchecked[drawable]);
        setButtonDrawable(leftDrawable);
    }

    public void setCheckedDrawable(int drawable) {
        if (isChange) {
            return;
        }
        Drawable leftDrawable = getLayerDrawable(animationChecked[drawable]);
        setButtonDrawable(leftDrawable);
    }

    private LayerDrawable getLayerDrawable(int redId) {
        InsetDrawable insetDrawable = new InsetDrawable(getResources().getDrawable(redId, null), 0, 0, 0, 0);
        Drawable visiDrawable = getResources().getDrawable(R.drawable.ici28c_selectioncontrols_checkbox_box_normal, null);
        return new LayerDrawable(new Drawable[]{visiDrawable, insetDrawable});
    }

    @Override
    public void initCommon() {

    }

    @Override
    public void initCher() {
        setButtonDrawable(null);
        setTextColor(getResources().getColor(R.color.ici28c_color_text_status));
        setTypeface(FontUtils.getDefaultFont());
        setButtonDrawable(getResources().getDrawable(R.drawable.ici28c_checkbox_status, null));
        setBackground(null);

        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_checkbox_animation);
        setStateListAnimator(stateListAnimator);
        animationUnchecked = animationCHERUnchecked;
        animationChecked = animationCherChecked;
    }

    @Override
    public void initBuck() {
        setButtonDrawable(null);
        setTextColor(getResources().getColor(R.color.ici28b_color_text_status));
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        setButtonDrawable(getResources().getDrawable(R.drawable.ici28b_checkbox_status, null));
        setBackground(null);
        animationUnchecked = animationBuckUnchecked;
        animationChecked = animationBuckChecked;
    }
}
