package com.android.widget_extra.card;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.wjs.android.mylibrary2.R;

//import com.android.internal.R;

@SuppressLint("AppCompatCustomView")
public class ICICardAddView extends ImageView {
    public ICICardAddView(Context context) {
        super(context);
        init();
    }

    public ICICardAddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICICardAddView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.ici28c_cardadd_status);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
