package com.android.widget_extra.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.wjs.android.demo.R;

//import com.android.internal.R;

@SuppressLint("AppCompatCustomView")
public class ICIImageButton extends ImageButton {
    private boolean onlyIcon;

    public ICIImageButton(Context context) {
        super(context);
        init();
    }

    public ICIImageButton(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public ICIImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setOnlyIcon(boolean onlyIcon) {
        this.onlyIcon = onlyIcon;
        init();
    }

    private void init() {
        if (!onlyIcon) {
            setBackgroundResource(R.drawable.ici_imagebutton_bg_status);
        }
    }

}
