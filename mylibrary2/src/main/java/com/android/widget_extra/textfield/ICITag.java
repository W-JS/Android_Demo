package com.android.widget_extra.textfield;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

//import com.android.internal.R;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.mylibrary2.R;

@SuppressLint("AppCompatCustomView")
public class ICITag extends TextView {
    public ICITag(Context context) {
        super(context);
        init();
    }

    public ICITag(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICITag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        setTextColor(getResources().getColorStateList(R.color.ici28c_tag_textcolor, null));
        setTextSize(36);
        setTypeface(FontUtils.getDefaultFont());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setEnabled(false);
    }
}
