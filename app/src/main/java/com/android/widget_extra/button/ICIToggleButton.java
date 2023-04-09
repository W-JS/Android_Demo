package com.android.widget_extra.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

//import com.android.internal.R;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.demo.R;

@SuppressLint("AppCompatCustomView")
public class ICIToggleButton extends CheckedTextView {
    public ICIToggleButton(Context context) {
        super(context);
        init();
    }

    public ICIToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.ici_status_togglebutton);
        setTypeface(FontUtils.getDefaultFont());
        setAllCaps(false);
    }

}
