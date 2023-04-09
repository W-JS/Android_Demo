package com.android.widget_extra.list;

public class ICIListDimen {
    //无左侧图片  文字距离
    public static final int LEFTGAP_NOIMAGE = 128;

    //有左侧图片  文字距离
    public static final int LEFTGAP_HASIMAGE = 126;


    //左侧图片宽120
    public static final int LEFT_LEFTIMAGE_WIDTH = 120;

    public static final int MID_MIANTEXT_SIZE = 40;

    public static final int MID_TEXT_OTHER_SIZE = 30;

    //第一行与第二行间距10px
    public static final int MID_MIANTEXT_2RDTEXT_GAP = 10;

    //第二行与第三行间距9像素
    public static final int MID_2RDTEXT_3RD_GAP = 10;

    //右侧有checkbox radiobuttton,switch,next时候最右边留白控件
    public static final int RIGHT_GAP_HAS_WIDGET = 50;


    //右侧为图片按钮时最右侧留白空间
    public static final int RIGHT_GAP_HAS_IMAGEVIEW = 28;

    //右侧为图片按钮时两个图片按钮间距
    public static final int RIGHT_IAMGEVIEW_GAP = 10;

    //单个ACTION宽度
    public static final int ACTION_WIDTH = 176;


    private int leftGap;
    private int rightGap;

    public int getLeftGap() {
        return leftGap;
    }

    public void setLeftGap(int leftGap) {
        this.leftGap = leftGap;
    }

    public int getRightGap() {
        return rightGap;
    }

    public void setRightGap(int rightGap) {
        this.rightGap = rightGap;
    }
}
