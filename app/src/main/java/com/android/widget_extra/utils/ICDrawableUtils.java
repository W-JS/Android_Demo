package com.android.widget_extra.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class ICDrawableUtils {
    /**
     * 获取纯色圆角图片
     *
     * @param rgb    颜色
     * @param radius 圆角半径
     * @return
     */
    public static Drawable getDrawable(int rgb, int radius, int stroke) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(rgb);
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);//矩形
        gradientDrawable.setCornerRadius(radius);//四周圆角半径
        gradientDrawable.setStroke(1, stroke);//边框厚度与颜色
        return gradientDrawable;
    }

    public static Drawable getGradientDrawable(int[] colors, int orient, int stokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(colors);
        gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);//矩形
        gradientDrawable.setCornerRadius(4);//四周圆角半径
        gradientDrawable.setStroke(1, stokeColor);//边框厚度与颜色
        return gradientDrawable;
    }

    /**
     * 获取按压状态选择器
     *
     * @param normalDrawable
     * @param pressDrawable
     * @return
     */
    public static StateListDrawable getPressSelector(Drawable normalDrawable, Drawable pressDrawable, Drawable disableDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //给当前的颜色选择器添加选中图片选中指向状态，未选中图片指向状态
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        //设置disable状态
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disableDrawable);
        return stateListDrawable;
    }

    /**
     * @param normalDrawable  未checked状态图片
     * @param checkedDrawable checked状态图片
     * @return
     */
    public static StateListDrawable getCheckedSelector(Drawable normalDrawable, Drawable checkedDrawable, Drawable disableDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //给当前的颜色选择器添加选中图片选中指向状态，未选中图片指向状态
        stateListDrawable.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_enabled}, checkedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        //设置Disable状态
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disableDrawable);
        return stateListDrawable;
    }


}
