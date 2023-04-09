package com.android.widget_extra.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.progress.ICISlider;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.mylibrary2.R;

public class ICIListSliderView extends RelativeLayout implements ICICommonView {
    private ICISlider iciSlider;
    private TextView leftTextView, rightTextView;

    public ICIListSliderView(Context context) {
        super(context);
        init();
    }


    public ICIListSliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIListSliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());


    }

    public ICISlider getIciSlider() {
        return iciSlider;
    }

    public TextView getLeftTextView() {
        return leftTextView;
    }

    public TextView getRightTextView() {
        return rightTextView;
    }

    @Override
    public void initCommon() {
        LayoutInflater.from(getContext()).inflate(R.layout.ici28c_list_slider, this);
        leftTextView = findViewById(R.id.ici_list_slider_content);
        rightTextView = findViewById(R.id.ici_list_slider_number);
        iciSlider = findViewById(R.id.ici_list_slider_sliderview);
    }

    @Override
    public void initCher() {
        leftTextView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
        rightTextView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
    }

    @Override
    public void initBuck() {
        leftTextView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        rightTextView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
    }
}
