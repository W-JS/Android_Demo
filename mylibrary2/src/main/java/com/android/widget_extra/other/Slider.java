package com.android.widget_extra.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.wjs.android.mylibrary2.R;

//import com.android.internal.R;

@SuppressLint("AppCompatCustomView")
public class Slider extends SeekBar {
    public Slider(Context context) {
        super(context);
        init();
    }

    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Slider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Slider(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    private void init() {
        setProgressDrawable(getResources().getDrawable(R.drawable.ici_slider));
        setThumb(getResources().getDrawable(R.drawable.ici_slider_thumb_status));
        setBackground(null);
        setSplitTrack(false);
        setPadding(30, 26, 30, 26);
    }

}
