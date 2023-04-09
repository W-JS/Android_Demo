package com.android.widget_extra.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.mylibrary2.R;

@SuppressLint("AppCompatCustomView")
public class ICISubHeader extends TextView implements ICICommonView {
    public static final int COLOR_GOLD = 0;
    public static final int COLOR_WHITE = 1;
    public int colorType = COLOR_GOLD;
    public int colorGold, colorWhite;

    public ICISubHeader(Context context) {
        super(context);
        init();
    }

    public ICISubHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICISubHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICISubHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }


    public void setColorType(int colorType) {
        this.colorType = colorType;
        if (colorType == COLOR_GOLD) {
            setTextColor(colorGold);
        } else if (colorType == COLOR_WHITE) {

            setTextColor(colorWhite);
        }

    }

    @Override
    public void initCher() {
        setTypeface(FontUtils.getTypeFace(com.android.widget_extra.utils.FontUtils.TYPE_THIN));
        setBackgroundResource(R.drawable.ici28c_subheader_bg);
        colorGold = getResources().getColor(R.color.ici28c_subheader_color_text_gold);
        colorWhite = getResources().getColor(R.color.ici28c_subheader_color_text_white);
        setColorType(colorType);
    }

    @Override
    public void initBuck() {
        setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        setBackgroundResource(R.drawable.ici28b_subheader_bg);
        colorGold = getResources().getColor(R.color.ici28b_subheader_color_text_gold);
        colorWhite = getResources().getColor(R.color.ici28b_subheader_color_text_white);
        setColorType(colorType);
    }

    @Override
    public void initCommon() {
        setPadding(60, 0, 0, 0);
        setGravity(Gravity.CENTER);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, 32);
        setBackgroundResource(R.drawable.ici28c_subheader_bg);
    }
}
