package com.android.widget_extra.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

//import com.android.internal.R;
import com.android.widget_extra.button.ICIIconButton;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.wjs.android.mylibrary2.R;

import java.util.ArrayList;
import java.util.List;

public class ICI28TabView extends RelativeLayout implements ICICommonView {
    private ImageView backImageView;
    private View leftGapView, rightGapView, bottomLine;
    private LinearLayout mid_group, right_group;
    private ICITabLine iciTabLine;
    private int leftGap;
    private int rightGap;
    private int itemGap;
    private boolean hasRightButton;
    private int itemWidth;
    private List<String> allItems;
    private boolean hasBack;
    private boolean hasCustomerItemWidth;
    private List<ICI28TabItemView> allViews;
    private OnTabClickListner onTabClickListner;
    private int mIndex = -1;
    private OnTabTouchListner onTabTouchListner;
    private ICIIconButton rightButton_search, rightButton_setting;


    public ICI28TabView(Context context) {
        super(context);
        init();
    }

    public ICI28TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICI28TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }

    @Override
    public void initCher() {
        setBackgroundResource(R.drawable.ici28c_navibar_bg);
        bottomLine.setBackgroundResource(R.drawable.ici28c_navibar_line);
        backImageView.setImageResource(R.drawable.ici28c_back);
        rightButton_search.setImageResource(R.drawable.ici28c_icon_60_search);
        rightButton_setting.setImageResource(R.drawable.ici28c_icon_60_setting);
    }


    @Override
    public void initBuck() {
        bottomLine.setBackgroundColor(0x33767F9D);
        backImageView.setImageResource(R.drawable.ici28b_ic_72_back_normal);
        rightButton_search.setImageResource(R.drawable.ici28b_ic_60_search_normal);
        rightButton_setting.setImageResource(R.drawable.ici28b_ic_60_setting);
    }

    @Override
    public void initCommon() {
        LayoutInflater.from(getContext()).inflate(R.layout.ici28c_tabview, this, true);
        backImageView = findViewById(R.id.ici_tabview_back);
        leftGapView = findViewById(R.id.ici_tabview_leftgap);
        mid_group = findViewById(R.id.ici_tabview_group);
        rightGapView = findViewById(R.id.ici_tabview_rightgap);
        right_group = findViewById(R.id.ici_tabview_rightgroup);
        rightButton_search = findViewById(R.id.ici_tab_search);
        rightButton_setting = findViewById(R.id.ici_tab_setting);
        iciTabLine = findViewById(R.id.ici_tab_line);
        bottomLine = findViewById(R.id.ici_tabview_bootomLine);
        allViews = new ArrayList<>();
        allItems = new ArrayList<>();
    }

    public void setTabContentDescription(int index, String desc) {
        if (index < 0 || index >= allViews.size()) {
            throw new RuntimeException("index 大于tab item数目");
        }
        allViews.get(index).setContentDescription(desc);
    }

    public List<ICI28TabItemView> getAllItemViews() {
        return allViews;
    }

    private void refreshView() {
        allViews.clear();
        mid_group.removeAllViews();

//        right_group.removeAllViews();

        if (!hasCustomerItemWidth && !hasBack && !hasRightButton) {
            calNoBackWidth();
        } else if (hasBack && !hasRightButton && !hasCustomerItemWidth) {
            calHasBackWidth();
        } else if (hasBack && hasRightButton && !hasCustomerItemWidth) {
            calHasBackAndAction();
        }
        if (itemWidth == 0 || leftGap == 0 || rightGap == 0) {
            throw new RuntimeException("请设置合理设置itemWidth,leftGap,rightGap参数");
        }
        LayoutParams leftParams = (LayoutParams) leftGapView.getLayoutParams();
        leftParams.width = leftGap;
        leftGapView.setLayoutParams(leftParams);

        LayoutParams rightParams = (LayoutParams) rightGapView.getLayoutParams();
        rightParams.width = rightGap;
        rightGapView.setLayoutParams(rightParams);

        for (int i = 0; i < allItems.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(itemWidth, 114);
            String content = allItems.get(i);
            ICI28TabItemView ici28TabItemView = null;
            if (i == 0) {
                ici28TabItemView = getItemView(content);
            } else {
                layoutParams.leftMargin = itemGap;
                ici28TabItemView = getItemView(content);
            }
            final int finalI = i;
            ici28TabItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTabClickListner != null) {
                        onTabClickListner.onTabClick(finalI);
                    }
                    switchToItem(finalI, true);

                }
            });
            ici28TabItemView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (onTabTouchListner != null) {
                        onTabTouchListner.onTouch(finalI, event);
                    }
                    return false;
                }
            });
            allViews.add(ici28TabItemView);
            mid_group.addView(ici28TabItemView, layoutParams);
        }

        if (hasBack) {
            backImageView.setVisibility(VISIBLE);
        }

        if (hasRightButton) {
            right_group.setVisibility(VISIBLE);
        } else {
            right_group.setVisibility(GONE);
        }

    }

    public void setCurentIndex(int position) {
        if (position < 0 || position > allItems.size() - 1) {
            throw new RuntimeException("超过可选值");
        }
        switchToItem(position, false);
    }

    private void switchToItem(int position, boolean isAnim) {
        if (mIndex == position) {
            return;
        }
        if (mIndex != -1) {
            allViews.get(mIndex).setTabSelected(false, isAnim);
        }
        allViews.get(position).setTabSelected(true, isAnim);
        mIndex = position;
        iciTabLine.setItemHeight(itemWidth);
        int selectIetmCentx = -1;
        if (!hasBack) {
            selectIetmCentx = allViews.get(position).getLeft() + itemWidth / 2 + leftGap;
        } else {
            selectIetmCentx = allViews.get(position).getLeft() + itemWidth / 2 + leftGap + 128;
        }
        iciTabLine.setCenterX(selectIetmCentx);
    }

    public int getSelectedPosition() {
        return mIndex;
    }

    private void calNoBackWidth() {

        int size = allItems.size();
        //两个之间差2个像素
        itemGap = 2;

        switch (size) {
            case 2:
                itemWidth = 680;
                leftGap = 196;
                rightGap = 196;
                break;
            case 3:
                itemWidth = 480;
                leftGap = 155;
                rightGap = 155;
                break;
            case 4:
                itemWidth = 370;
                leftGap = 134;
                rightGap = 134;
                break;
            case 5:
                itemWidth = 310;
                leftGap = 98;
                rightGap = 98;
                break;
            case 6:
                itemWidth = 282;
                leftGap = 26;
                rightGap = 26;
                break;
            default:
//                throw new RuntimeException("不属于控件库默认支持选项数，请自行设置每个item宽度");
        }
    }

    private void calHasBackWidth() {
        int size = allItems.size();
        //两个之间差2个像素
        itemGap = 2;
        switch (size) {
            case 2:
                itemWidth = 332;
                leftGap = 1;
                rightGap = 700;
                break;
            default:
//                throw new RuntimeException("不属于控件库默认支持选项数，请自行设置每个item宽度");

        }
    }


    private void calHasBackAndAction() {
        int size = allItems.size();
        //两个之间差2个像素
        itemGap = 0;
        switch (size) {
            case 2:
                itemWidth = 332;
                leftGap = 1;
                rightGap = 700;
                break;
            case 3:
                itemWidth = 332;
                leftGap = 1;
                rightGap = 368;
                break;
            case 4:
                itemWidth = 278;
                leftGap = 1;
                rightGap = 243;
                break;
            case 5:
                itemWidth = 238;
                leftGap = 1;
                rightGap = 170;

                break;
            default:
//                throw new RuntimeException("不属于控件库默认支持选项数，请自行设置每个item宽度");

        }

    }

    private ICI28TabItemView getItemView(String content) {
        ICI28TabItemView ici28TabItemView = new ICI28TabItemView(getContext());
        ici28TabItemView.setContent(content);
        return ici28TabItemView;
    }


    public interface OnTabClickListner {
        public void onTabClick(int position);
    }

    public interface OnTabTouchListner {
        void onTouch(int position, MotionEvent motionEvent);
    }


    public void setOnTabClickListner(OnTabClickListner onTabClickListner) {
        this.onTabClickListner = onTabClickListner;
    }

    public void setLeftGap(int leftGap) {
        this.leftGap = leftGap;
    }

    public void setRightGap(int rightGap) {
        this.rightGap = rightGap;
    }

    public void setHasRightButton(boolean hasRightButton) {
        this.hasRightButton = hasRightButton;
        refreshView();
    }

    public void setBackOnclickListner(OnClickListener onclickListner) {
        backImageView.setOnClickListener(onclickListner);
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
        hasCustomerItemWidth = true;
    }

    public void setHasBack(boolean hasBack) {
        this.hasBack = hasBack;
        refreshView();
    }

    public void setItems(List<String> allItems) {
        this.allItems.addAll(allItems);
        refreshView();
    }

    public void reset() {
        allItems.clear();
        mIndex = -1;
    }

    public void setRightButtonClick(OnClickListener searchClick, OnClickListener settingClick) {
        rightButton_search.setOnClickListener(searchClick);
        rightButton_setting.setOnClickListener(settingClick);
    }
}
