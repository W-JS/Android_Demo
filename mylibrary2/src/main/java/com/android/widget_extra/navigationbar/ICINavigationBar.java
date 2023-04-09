package com.android.widget_extra.navigationbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.android.internal.R;
import com.android.widget_extra.button.ICIFlatButton;
import com.android.widget_extra.button.ICIIconButton;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.selection.ICISwitchButton;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.mylibrary2.R;

public class ICINavigationBar extends RelativeLayout implements ICICommonView {
    private ImageView backIV;
    private TextView titleTV;
    private LinearLayout group;
    private ImageView lineView;


    public ICINavigationBar(Context context) {
        super(context);
        init();
    }

    public ICINavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICINavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICINavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(1754, MeasureSpec.AT_MOST), 114);
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }

    @Override
    public void initCher() {
        titleTV.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        lineView.setImageResource(R.drawable.ici28c_navibar_line);
        setBackgroundResource(R.drawable.ici28c_navibar_bg);
        backIV.setImageResource(R.drawable.ici28c_back);
    }

    @Override
    public void initBuck() {
        titleTV.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        LayoutParams lineParams = (LayoutParams) lineView.getLayoutParams();
        lineParams.height = 2;
        lineView.setBackgroundColor(Color.parseColor("#33767F9D"));
        backIV.setImageResource(R.drawable.ici28b_ic_72_back_normal);
    }

    @Override
    public void initCommon() {
        inflate(getContext(), R.layout.ici_navigationbar, this);
        backIV = findViewById(R.id.ici_navi_back);
        titleTV = findViewById(R.id.ici_navi_title);
        group = findViewById(R.id.ici_navigation_group);
        lineView = findViewById(R.id.ici_navigation_line);
    }


    public void setTitle(String title) {
        titleTV.setText(title);
    }

    public void setOnBackclick(OnClickListener clickListener) {
        backIV.setOnClickListener(clickListener);
        backIV.setVisibility(VISIBLE);
    }


    /**
     * 图片资源需要60*60规格
     *
     * @param resId
     * @param onClickListener
     * @return
     */
    public ImageView addImageButton(int resId, OnClickListener onClickListener) {
        ICIIconButton imageView = new ICIIconButton(getContext());
        imageView.setImageResource(resId);
        imageView.setOnClickListener(onClickListener);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(130, 114);
        imageView.setPadding(35, 27, 35, 27);
        group.addView(imageView, layoutParams);
        return imageView;
    }

    public ICISwitchButton addSwitchButton(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        ICISwitchButton iciSwitchButton = new ICISwitchButton(getContext());
        iciSwitchButton.setOnCheckedChangeListener(onCheckedChangeListener);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(166, 114);
        layoutParams.rightMargin = 20;
        group.addView(iciSwitchButton, layoutParams);
        return iciSwitchButton;
    }


    @Deprecated
    public ICIFlatButton addFlatButton(String buttonName, OnClickListener onClickListener) {
        ICIFlatButton iciFlatButton = new ICIFlatButton(getContext());
        iciFlatButton.setColor(ICIFlatButton.COLOR_ORANGE);
        iciFlatButton.setText(buttonName);
        iciFlatButton.setOnClickListener(onClickListener);
        iciFlatButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(166, 114);
        iciFlatButton.setGravity(Gravity.CENTER);
        group.addView(iciFlatButton, layoutParams);
        return iciFlatButton;
    }


    /**
     * 如果不能满足需求 可在该处自定义加入
     *
     * @param v
     */
    public void addCustomerView(View v) {
        group.addView(v);
    }

    /**
     * 重置界面，需要重新添加控件
     */
    public void resetView() {
        group.removeAllViews();
        backIV.setVisibility(GONE);
        titleTV.setText("1");
    }

    public void setLineVisiable(boolean show) {
        if (show) {
            lineView.setVisibility(VISIBLE);
        } else {
            lineView.setVisibility(GONE);
        }
    }

}
