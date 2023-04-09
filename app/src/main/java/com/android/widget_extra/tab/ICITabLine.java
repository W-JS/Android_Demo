package com.android.widget_extra.tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wjs.android.demo.R;

//import com.android.internal.R;

public class ICITabLine extends View {
    private Paint mPaint;
    private int[] positions;
    private int[] colors;
    private int height, width;
    private int itemHeight;
    private int centerX;

    public ICITabLine(Context context) {
        super(context);
        init();
    }

    public ICITabLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICITabLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        calPosition();
    }

    private void calPosition() {
        int startSelectLine = centerX - itemHeight / 2;
        int endSelectLine = centerX + itemHeight / 2;
        positions = new int[8];
        colors = new int[7];
        //342长度线段
        int startLinebeforeOneSelect = startSelectLine - 342 > 0 ? startSelectLine - 342 : 0;
        int startLineAfterOneSelect = endSelectLine + 342 > width ? width : endSelectLine + 342;
        //402线段
        int startLineBeforeTwoSelect = -1;
        int startLineAfterTwoSelect = Integer.MAX_VALUE;
        if (startLinebeforeOneSelect > 0) {
            startLineBeforeTwoSelect = startLinebeforeOneSelect - 402 > 0 ? startLinebeforeOneSelect - 402 : 0;
        }
        if (startLineAfterOneSelect < width) {
            startLineAfterTwoSelect = startLineAfterOneSelect + 402 > width ? width : startLineAfterOneSelect + 402;
        }

        int startLineBeforeThreeSelect = -1;
        int startLineAfterThreeSelect = Integer.MAX_VALUE;
        if (startLineBeforeTwoSelect > 0) {
            startLineBeforeThreeSelect = startLineBeforeTwoSelect - 694 > 0 ? startLineBeforeTwoSelect - 694 : 0;
        }
        if (startLineAfterTwoSelect < width) {
            startLineAfterThreeSelect = startLineAfterTwoSelect + 694 > width ? width : startLineAfterTwoSelect + 694;
        }

        if (startLineBeforeThreeSelect >= 0) {
            positions[0] = startLineBeforeThreeSelect;
            colors[0] = getResources().getColor(R.color.ici28_tab_tabline_line_select_three);
        } else {
            positions[0] = -1;
        }
        if (startLineBeforeTwoSelect >= 0) {
            positions[1] = startLineBeforeTwoSelect;
            colors[1] = getResources().getColor(R.color.ici28_tab_tabline_line_select_two);

        } else {
            positions[1] = -1;
        }
        if (startLinebeforeOneSelect >= 0) {
            positions[2] = startLinebeforeOneSelect;
            colors[2] = getResources().getColor(R.color.ici28_tab_tabline_line_select_one);

        } else {
            positions[2] = -1;
        }

        if (startSelectLine >= 0) {
            positions[3] = startSelectLine;
            colors[3] = getResources().getColor(R.color.ici28_tab_tabline_line_selected);
        } else {

            positions[3] = -1;
        }
        if (endSelectLine <= width) {
            positions[4] = endSelectLine;
            colors[4] = getResources().getColor(R.color.ici28_tab_tabline_line_select_one);
        }


        if (startLineAfterOneSelect <= width) {
            positions[5] = startLineAfterOneSelect;
            colors[5] = getResources().getColor(R.color.ici28_tab_tabline_line_select_two);
        } else {
            positions[5] = -1;
        }
        if (startLineAfterTwoSelect <= width) {
            positions[6] = startLineAfterTwoSelect;
            colors[6] = getResources().getColor(R.color.ici28_tab_tabline_line_select_three);

        } else {
            positions[6] = -1;

        }
        if (startLineAfterThreeSelect <= width) {
            positions[7] = startLineAfterThreeSelect;
        } else {
            positions[7] = -1;
        }
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
        calPosition();
        postInvalidate();
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        calPosition();
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (positions.length != (colors.length-1))
//        {
//            throw new RuntimeException("颜色与线段数量不匹配");
//        }

        for (int i = 0; i < positions.length - 1; i++) {
            if (positions[i] == -1 || positions[i] == width) {
                continue;
            }
            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setColor(colors[i]);
//            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.FILL);
            if (i != positions.length - 2) {
                canvas.drawRect(positions[i], 0, positions[i + 1], height, mPaint);
            } else {
                canvas.drawRect(positions[i], 0, width, height, mPaint);
            }
        }
    }
}
