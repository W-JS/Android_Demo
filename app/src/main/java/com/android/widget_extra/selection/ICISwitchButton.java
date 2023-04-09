package com.android.widget_extra.selection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Switch;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.demo.R;

public class ICISwitchButton extends Switch implements ICICommonView {
    private int width, height;
    private Bitmap bitmap;
    private float position;

    public ICISwitchButton(Context context) {
        super(context);
        init();
    }

    public ICISwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICISwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICISwitchButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }


    private void init() {
        ICICommonViewManager.viewInit(this, getContext());

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public void initCommon() {
        setTextOff("");
        setTextOn("");

    }

    @Override
    public void initCher() {
        setThumbResource(R.drawable.ici28c_switch_check_status);
        setTrackResource(R.drawable.ici28c_selectioncontrols_switch_bg);
        setTypeface(FontUtils.getDefaultFont());
    }

    @Override
    public void initBuck() {
        setThumbResource(R.drawable.ici28b_switch_check_status);
        setTrackResource(R.drawable.ici28b_selectioncontrols_switch_bg);
        setTypeface(FontUtils.getDefaultFont());
    }
}
