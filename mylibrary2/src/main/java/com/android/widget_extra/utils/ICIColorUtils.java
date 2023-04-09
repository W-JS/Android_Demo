package com.android.widget_extra.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;

public class ICIColorUtils {

    public static ColorStateList getEnableColorSelector(int colorEnable, int colorDisable) {
        int[][] status = new int[2][];
        //给当前的颜色选择器添加选中图片选中指向状态，未选中图片指向状态
        status[0] = new int[]{android.R.attr.state_enabled};
        status[1] = new int[]{-android.R.attr.state_enabled};
        return new ColorStateList(status, new int[]{colorEnable, colorDisable});
    }

    public static ColorStateList getChechColorSelector(int colorMormal, int colorCheched, int colorDisable) {
        int[][] status = new int[3][];
        //给当前的颜色选择器添加选中图片选中指向状态，未选中图片指向状态
        status[0] = new int[]{android.R.attr.state_enabled};
        status[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_checked};
        status[2] = new int[]{-android.R.attr.state_enabled};
        return new ColorStateList(status, new int[]{colorMormal, colorCheched, colorDisable});
    }

    public static ColorStateList getPressColorSelector(int colorNormal, int colorPress) {
        int[][] status = new int[2][];
        //给当前的颜色选择器添加选中图片选中指向状态，未选中图片指向状态
        status[0] = new int[]{android.R.attr.state_enabled};
        status[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
        return new ColorStateList(status, new int[]{colorNormal, colorPress});
    }

    public static int getGrandColor(int startColor, int endColor, int progress) {
        int startAlpha = startColor >> 24;
        int endAlpha = endColor >> 24;
        float aplhaStep = (startAlpha - endAlpha) / 100f;

        int startRed = (startColor & 0x00FF0000) >> 16;
        int endRed = (endColor & 0x00FF0000) >> 16;
        float redStep = (startRed - endRed) / 100f;


        int startGreen = (startColor & 0x0000ff00) >> 8;
        int endGreen = (startColor & 0x0000ff00) >> 8;
        float greenStep = (startGreen - endGreen) / 100f;


        int startBlue = startColor & 0x000000FF;
        int endBlue = endColor & 0x000000FF;
        float blueStep = (startBlue - endBlue) / 100f;


        int resultAplha = (int) (startAlpha + aplhaStep * progress);
        int resultRed = (int) (startRed + redStep * progress);
        int resultGreen = (int) (startGreen + greenStep * progress);
        int resultBlue = (int) (startBlue + blueStep * progress);

        return Color.argb(resultAplha, resultRed, resultGreen, resultBlue);
    }
}
