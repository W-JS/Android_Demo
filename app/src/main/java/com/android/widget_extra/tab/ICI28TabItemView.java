package com.android.widget_extra.tab;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.utils.MeasureUtils;
import com.wjs.android.demo.R;

public class ICI28TabItemView extends View implements ICICommonView {
    private static final int DEFAULT_TEXTSIZE = 40;
    private static final int SELECT_TEXTSIZE = 42;
    private Bitmap bgBitmap;
    private TextPaint mPaint;
    private String content;
    private boolean isTabSelected;
    private boolean isTabDisable;
    private boolean isTabPressed;
    private int width, height;
    private int textSize;
    private Bitmap notiBimap, pressBitmap;

    private LinearGradient selectTextColor, normalTextColor, disableTextColor;
    private int[] selectTextColors, normalTextColors, disableTextColors;
    private Typeface textFont;


    public ICI28TabItemView(Context context) {
        super(context);
        init();
    }

    public ICI28TabItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICI28TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        ICICommonViewManager.viewInit(this, getContext());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureUtils.getMesureHeight(this, 114));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean isHandle = super.onTouchEvent(event);
        if (isHandle) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isTabPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    isTabPressed = false;
                    break;
            }
            postInvalidate();
        }
        return isHandle;
    }

    public void setTabTextSize(int size) {

        textSize = size;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isTabDisable && isTabSelected && bgBitmap != null) {

            float scaleWidth = 1;
            float scaleHeight = 1;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            canvas.drawBitmap(bgBitmap, (width - bgBitmap.getWidth()) / 2, height - bgBitmap.getHeight(), mPaint);

            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setColor(0x0245C8);
            canvas.drawRect(0, height - 2, width, height, mPaint);
        }
        canvas.save();

        if (isTabDisable) {
            //disable状态
            drawText(canvas, disableTextColors, DEFAULT_TEXTSIZE);
        } else if (isTabPressed || isTabSelected) {
            //点击或者选中状态
            drawText(canvas, selectTextColors, textSize);
        } else {
            //常规状态
            drawText(canvas, normalTextColors, textSize);
        }

        canvas.restore();
    }

    private void drawText(Canvas canvas, int[] color, int textSize) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(textFont);


        mPaint.setSubpixelText(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Rect rect = new Rect();
        mPaint.getTextBounds(content, 0, content.length(), rect);

//        int requestWidth = rect.width();
//        if (requestWidth>width)
//        {
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 55, color, new float[]{0, 1}, Shader.TileMode.REPEAT);
//            throw new RuntimeException("字符串过长，给定空间无法完全显示");
        mPaint.setShader(linearGradient);

//        }else {
//            LinearGradient linearGradient = new LinearGradient(requestWidth/2*-1,0,requestWidth/2,0,color,new float[]{0,1}, Shader.TileMode.REPEAT);
//            mPaint.setShader(linearGradient);
//        }
        StaticLayout layoutopen = new StaticLayout(content, mPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        canvas.save();

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float offset = (fontMetrics.descent + fontMetrics.ascent) / 2;
//
        if (layoutopen.getLineCount() == 2) {
            canvas.translate(width / 2, 0);
            layoutopen.draw(canvas);
        } else {
            canvas.translate(width / 2, height / 2 - offset + 10);
            canvas.drawText(content, 0, -10, mPaint);
        }


        canvas.restore();


    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        postInvalidate();
    }

    public boolean isTabSelected() {
        return isTabSelected;
    }

    public void setTabSelected(boolean tabSelected, boolean anim) {
        isTabSelected = tabSelected;

        if (isTabSelected) {
            if (anim) {
                ObjectAnimator animator = ObjectAnimator.ofInt(this, "tabTextSize", 40, 42);
                animator.setDuration(160);
                animator.start();
            } else {
                textSize = 42;
                invalidate();
            }

        } else {
            if (anim) {
                ObjectAnimator animator = ObjectAnimator.ofInt(this, "tabTextSize", 42, 40);
                animator.setDuration(160);
                animator.start();
            } else {
                textSize = 40;
                invalidate();
            }

        }
    }


    public boolean isTabDisable() {
        return isTabDisable;
    }

    public void setTabDisable(boolean tabDisable) {
        isTabDisable = tabDisable;
        postInvalidate();
    }


    @Override
    public void initCher() {
        selectTextColors = new int[2];
        selectTextColors[0] = getResources().getColor(R.color.ici28_tab_tabitem_text_select_color);
        selectTextColors[1] = getResources().getColor(R.color.ici28_tab_tabitem_text_select_color);

        normalTextColors = new int[2];
        normalTextColors[0] = getResources().getColor(R.color.ici28_tab_tabitem_text_normal_color);
        normalTextColors[1] = getResources().getColor(R.color.ici28_tab_tabitem_text_normal_color);

        disableTextColors = new int[2];
        disableTextColors[0] = getResources().getColor(R.color.ici28_tab_tabitem_text_disable_color);
        disableTextColors[1] = getResources().getColor(R.color.ici28_tab_tabitem_text_disable_color);

        //for coverity
        if (getResources() == null) {
            return;
        }


        bgBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ici28c_tabs_light)).getBitmap();
        textFont = FontUtils.getTypeFace(FontUtils.TYPE_LIGHT);
    }

    @Override
    public void initBuck() {
        selectTextColors = new int[2];
        selectTextColors[0] = 0xFFFFEABF;
        selectTextColors[1] = 0xFFDBA06F;

        normalTextColors = new int[2];
        normalTextColors[0] = 0xE6FFFFFF;
        normalTextColors[1] = 0xE6FFFFFF;

        disableTextColors = new int[2];
        disableTextColors[0] = 0x4DFFFFFF;
        disableTextColors[1] = 0x4DFFFFFF;

        //for coverity
        if (getResources() == null) {
            return;

        }

        bgBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ici28b_tabs_light)).getBitmap();
        textFont = FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT);
    }

    @Override
    public void initCommon() {
        mPaint = new TextPaint();
        textSize = DEFAULT_TEXTSIZE;

//        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(),R.animator.ici28c_tab_check_animation);
//        setStateListAnimator(stateListAnimator);
    }
}
