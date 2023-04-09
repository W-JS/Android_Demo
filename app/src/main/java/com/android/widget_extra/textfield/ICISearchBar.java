package com.android.widget_extra.textfield;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.utils.StringUtils;
import com.android.widget_extra.utils.WidgetCoverityUtils;
import com.wjs.android.demo.R;

public class ICISearchBar extends RelativeLayout implements ICICommonView {
    private EditText editText;
    private ImageView voiceIV, deleteIV, searchIV;
    private View spiltView;
    private boolean hasDelete = false;

    public ICISearchBar(Context context) {
        super(context);
        init();
    }

    public ICISearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICISearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICISearchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }


    public ImageView addVoiceIV() {
        voiceIV.setVisibility(VISIBLE);
        judgeIsShowSplit();
        return voiceIV;
    }


    public ImageView addDeleteIV() {
        hasDelete = true;
        deleteIV.setVisibility(VISIBLE);
        judgeIsShowSplit();
        return deleteIV;
    }


    public EditText addEditText() {
        editText.setVisibility(VISIBLE);
        return editText;
    }

    private void judgeIsShowSplit() {
        if (voiceIV.getVisibility() == VISIBLE) {
            spiltView.setVisibility(VISIBLE);
        } else {
            spiltView.setVisibility(GONE);
        }
    }

    @Override
    public void initCommon() {

        LayoutInflater.from(getContext()).inflate(R.layout.ici_searchbar, this);
        editText = findViewById(R.id.ici_searchbar_edit);
        voiceIV = findViewById(R.id.ici_searchbar_voice);
        deleteIV = findViewById(R.id.ici_Searchbar_delete);
        spiltView = findViewById(R.id.ici_searchbar_split);
        searchIV = findViewById(R.id.ici_searchbar_icon);
        if (WidgetCoverityUtils.isNull(editText, voiceIV, deleteIV, spiltView, searchIV)) {
            return;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!hasDelete) {
                    return;
                }
                if (StringUtils.isEmpty(s.toString())) {
                    deleteIV.setVisibility(GONE);
                } else {
                    deleteIV.setVisibility(VISIBLE);
                }
                judgeIsShowSplit();
            }
        });
        deleteIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");

            }
        });
    }

    @Override
    public void initCher() {
        setBackgroundResource(R.drawable.ici28c_searchbar_corner_broad_normal);
        editText.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
        editText.setHintTextColor(Color.parseColor("#FF636A7B"));
        searchIV.setImageResource(R.drawable.ici28c_icon_60_search);
        deleteIV.setImageResource(R.drawable.ici28c_icon_60_close);
        spiltView.setBackgroundColor(Color.parseColor("#FF9EA7C0"));
        voiceIV.setImageResource(R.drawable.ici28c_icon_60_voice);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setBackgroundResource(R.drawable.ici28c_searchbar_corner_broad_selected);
                } else {
                    setBackgroundResource(R.drawable.ici28c_searchbar_corner_broad_normal);
                }
            }
        });


    }

    @Override
    public void initBuck() {
        setBackgroundResource(R.drawable.ici28b_searchbar_corner_broad_normal);
        editText.setHintTextColor(Color.parseColor("#FF5B6681"));
        editText.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        searchIV.setImageResource(R.drawable.ici28b_ic_60_search_normal);
        deleteIV.setImageResource(R.drawable.ici28b_icon_60_close);
        spiltView.setBackgroundColor(Color.parseColor("#FF9EA7C0"));
        voiceIV.setImageResource(R.drawable.ici28b_icon_60_voice);

        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setBackgroundResource(R.drawable.ici28b_searchbar_corner_broad_selected);
                } else {
                    setBackgroundResource(R.drawable.ici28b_searchbar_corner_broad_normal);
                }
            }
        });


    }
}
