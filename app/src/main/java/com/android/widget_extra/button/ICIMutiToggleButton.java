package com.android.widget_extra.button;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ICIMutiToggleButton extends LinearLayout implements ICICommonView {
    private int mIndex = -1;
    private List<CheckedTextView> checkedTextViews = new ArrayList<>();
    private List<String> items = new ArrayList<>();
    private OnToggleCheckChangeListner OnToggleCheckChangeListner;
    private Typeface textFont;

    //是否点击后立即跳转到选中选项，默认打开，关闭状态下只回调APP由APP自己设置
    private boolean isSwitchWithClick = true;

    public ICIMutiToggleButton(Context context) {
        super(context);
        init(context, null);
    }

    public ICIMutiToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ICIMutiToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ICIMutiToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    public List<CheckedTextView> getCheckedTextViews() {
        return checkedTextViews;
    }

    private void init(Context context, AttributeSet attrs) {
        ICICommonViewManager.viewInit(this, context);
    }


    public void setSwitchWithClick(boolean switchWithClick) {
        isSwitchWithClick = switchWithClick;
    }

    private int textColorResouce;
    private int backgroundResouce;

    private void addCheckView() {
        for (int i = 0; i < items.size(); i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.ici_toggle_button, this);
            CheckedTextView v = (CheckedTextView) getChildAt(i);
            if (v == null) {
                return;
            }
            v.setTextColor(getResources().getColor(textColorResouce));
            v.setBackgroundResource(backgroundResouce);
            v.setText(items.get(i));
            v.setElevation(30);
            v.setAllCaps(false);
            checkedTextViews.add(v);
            v.setTypeface(textFont);
//            v.setPadding(5,5,5,5);
            final int finalI = i;
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTo(finalI, false);
                }
            });
        }
    }


    private void changeTo(int index, boolean user) {
        if (mIndex == index) {
            return;
        }

        if (!isSwitchWithClick && OnToggleCheckChangeListner != null && !user) {

            OnToggleCheckChangeListner.onSelectChange(index);
            return;
        }

        for (CheckedTextView view : checkedTextViews) {
            view.setChecked(false);
        }
        checkedTextViews.get(index).setChecked(true);
        mIndex = index;
        if (OnToggleCheckChangeListner != null && !user) {
            OnToggleCheckChangeListner.onSelectChange(mIndex);
        }
    }

    public void setIndex(int index) {
        changeTo(index, true);
    }

    public int getIndex() {
        return mIndex;
    }

    public void setAllItem(List allItems) {
        items.clear();
        items.addAll(allItems);
        refreshView();
    }

    public void setAllItem(String[] allItem) {
        items.clear();
        items.addAll(Arrays.asList(allItem));
        refreshView();
    }

    public void setOnToggleCheckChangeListner(ICIMutiToggleButton.OnToggleCheckChangeListner onToggleCheckChangeListner) {
        OnToggleCheckChangeListner = onToggleCheckChangeListner;
    }

    private void refreshView() {
        mIndex = -1;
        removeAllViews();
        checkedTextViews.clear();
        addCheckView();
        changeTo(0, true);
    }

    @Override
    public void initCommon() {
        setOrientation(HORIZONTAL);
    }

    @Override
    public void initCher() {
        setBackgroundResource(R.drawable.ici28c_mutitoggle_bg);
        int defaultIndex = -1;
        addCheckView();
        changeTo(defaultIndex, false);
        textFont = FontUtils.getTypeFace(FontUtils.TYPE_THIN);
        backgroundResouce = R.drawable.ici28c_status_twotogglebutton;
        textColorResouce = R.color.ici28c_color_textswitch_text;
    }

    @Override
    public void initBuck() {
        setBackgroundResource(R.drawable.ici28b_mutitoggle_bg);
        int defaultIndex = -1;
        addCheckView();
        changeTo(defaultIndex, false);
        textFont = FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN);
        backgroundResouce = R.drawable.ici28b_status_twotogglebutton;
        textColorResouce = R.color.ici28b_color_textswitch_text;
    }


    /**
     *
     */
    public interface OnToggleCheckChangeListner {
        /**
         * 回调事件
         *
         * @param index 改变之后标签页位置
         */
        void onSelectChange(int index);
    }

}
