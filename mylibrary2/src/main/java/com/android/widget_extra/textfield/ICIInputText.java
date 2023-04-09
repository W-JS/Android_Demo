package com.android.widget_extra.textfield;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.utils.StringUtils;
import com.wjs.android.mylibrary2.R;

public class ICIInputText extends LinearLayout implements View.OnClickListener, ICICommonView {
    protected EditText editText;
    protected ImageView deleteIV, showIV;
    protected TextView suggestTV, errorTV;
    private View rightLine;
    private int inputType;
    private ViewGroup ici_editViewGroup;
    private boolean redBackGround = false;
    private boolean isShowPsw = false;
    private Scroller mScorller;
    private Animator objectAnimator;
    private int rightMsgNormalColor, rightMsgErrorColor;

    public ICIInputText(Context context) {
        super(context);
        init();
    }

    public ICIInputText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIInputText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIInputText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    private void init() {
        ICICommonViewManager.viewInit(this, getContext());

    }

    public void setScrollX(int x) {
        setTranslationX(x);
    }

    public ImageView addDeleteView() {
        deleteIV.setVisibility(VISIBLE);
        return deleteIV;
    }

    public EditText addEditText() {
        editText.setVisibility(VISIBLE);
        return editText;
    }


    public boolean isRedBackGround() {
        return redBackGround;
    }

    public ImageView addPasswordShowView() {
        showIV.setVisibility(VISIBLE);
        return showIV;
    }

    public TextView addErrorTextView() {
        errorTV.setVisibility(VISIBLE);
        return errorTV;
    }

    public TextView addSugguestTextView() {
        suggestTV.setVisibility(VISIBLE);
        return suggestTV;
    }

    public void setRedBackground(boolean isRed) {
        redBackGround = isRed;
        if (isRed) {
            ici_editViewGroup.setBackgroundResource(R.drawable.ici28c_edit_error);
        } else {
            ici_editViewGroup.setBackgroundResource(R.drawable.ici28c_input_corner_normal);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ici_input_button_delete) {
            editText.setText("");
        }
    }


    public TextView setRightErrorMessage(String content) {

        //for coverity
        if (errorTV.getText() == null) {
            return errorTV;
        }


        String originText = errorTV.getText().toString();
        if (originText != null && originText.equals(content)) {
            return errorTV;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(32);
        int width = (int) textPaint.measureText(content);
        if (width > 562) {
            errorTV.setTextSize(28);
        } else {
            errorTV.setTextSize(32);
        }
        errorTV.setVisibility(VISIBLE);
        errorTV.setText(content);
        errorTV.setTextColor(rightMsgErrorColor);
        setRedBackground(true);
        if (content == null || content.equals("")) {
            return errorTV;
        }

        return errorTV;
    }

    public void startErrorAnimator() {
        if (objectAnimator != null) {
            objectAnimator.setTarget(this);
            objectAnimator.start();
        }
    }

    public TextView setRightMessage(String content) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(32);
        int width = (int) textPaint.measureText(content);
        if (width > 562) {
            errorTV.setTextSize(28);
        } else {
            errorTV.setTextSize(32);
        }
        errorTV.setVisibility(VISIBLE);
        errorTV.setText(content);
        errorTV.setTextColor(rightMsgNormalColor);
        setRedBackground(false);


        return errorTV;
    }


    public void setRightInfoMessage(String hintMessage) {
        setRightMessage(hintMessage);
    }


    public void hideRightMessageView() {
        errorTV.setVisibility(VISIBLE);
    }

    /**
     * {@link TextView#setInputType}
     *
     * @param inputType
     */
    public void setInputType(int inputType) {
        this.inputType = inputType;
        editText.setInputType(inputType);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        editText.setEnabled(enabled);
    }

    @Override
    public void initCommon() {
        setOrientation(HORIZONTAL);
    }

    @Override
    public void initCher() {
        LayoutInflater.from(getContext()).inflate(R.layout.ici28c_input, this);
        editText = findViewById(R.id.ici_input_edit);
        deleteIV = findViewById(R.id.ici_input_button_delete);
        showIV = findViewById(R.id.ici_input_button_watch);
        suggestTV = findViewById(R.id.ici_input_suggest);
        errorTV = findViewById(R.id.ici_input_error_msg);
        rightLine = findViewById(R.id.ici_edit_rightline);
        mScorller = new Scroller(getContext());
        ici_editViewGroup = findViewById(R.id.ici_editViewGroup);

        deleteIV.setOnClickListener(this);

        //for coverity
        if (editText == null || deleteIV == null || showIV == null ||
                suggestTV == null || errorTV == null || rightLine == null
                || mScorller == null || ici_editViewGroup == null || deleteIV == null
        ) {
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
                if (!StringUtils.isEmpty(s.toString())) {
                    deleteIV.setVisibility(VISIBLE);
                    if (showIV.getVisibility() == VISIBLE) {
                        rightLine.setVisibility(VISIBLE);
                    }
                } else {
                    deleteIV.setVisibility(GONE);
                    rightLine.setVisibility(GONE);
                }
            }
        });
        ici_editViewGroup.setBackgroundResource(R.drawable.ici28c_edit_status);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (redBackGround) {
                    ici_editViewGroup.setBackgroundResource(R.drawable.ici28c_edit_error);
                } else if (hasFocus) {
                    ici_editViewGroup.setBackgroundResource(R.drawable.ici28c_input_corner_select);
                } else {
                    ici_editViewGroup.setBackgroundResource(R.drawable.ici28c_input_corner_normal);
                }
            }
        });

        showIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowPsw) {
                    isShowPsw = false;
                    showIV.setImageResource(R.drawable.ici28c_edit_hide);
                    editText.setInputType(inputType);
                    if (editText.getText() != null) {
                        editText.setSelection(editText.getText().length());
                    }
                } else {
                    isShowPsw = true;
                    showIV.setImageResource(R.drawable.ici28c_edit_show);
                    inputType = editText.getInputType();
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    if (editText.getText() != null) {
                        editText.setSelection(editText.getText().length());
                    }
                }
            }
        });
        editText.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
        suggestTV.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
        errorTV.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
        rightMsgNormalColor = 0xFFADB7CE;
        rightMsgErrorColor = 0xFFED2D30;

        objectAnimator = (Animator) AnimatorInflater.loadAnimator(getContext(), R.animator.ici28c_edit_error_left_animation);

    }

    @Override
    public void initBuck() {
        LayoutInflater.from(getContext()).inflate(R.layout.ici28b_input, this);
        editText = findViewById(R.id.ici_input_edit);
        deleteIV = findViewById(R.id.ici_input_button_delete);
        showIV = findViewById(R.id.ici_input_button_watch);
        suggestTV = findViewById(R.id.ici_input_suggest);
        errorTV = findViewById(R.id.ici_input_error_msg);
        rightLine = findViewById(R.id.ici_edit_rightline);
        mScorller = new Scroller(getContext());
        ici_editViewGroup = findViewById(R.id.ici_editViewGroup);

        //for coverity
        if (editText == null || deleteIV == null || showIV == null ||
                suggestTV == null || errorTV == null || rightLine == null
                || mScorller == null || ici_editViewGroup == null || deleteIV == null
        ) {
            return;
        }

        deleteIV.setOnClickListener(this);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())) {
                    deleteIV.setVisibility(VISIBLE);
                    if (showIV.getVisibility() == VISIBLE) {
                        rightLine.setVisibility(VISIBLE);
                    }
                } else {
                    deleteIV.setVisibility(GONE);
                    rightLine.setVisibility(GONE);
                }
            }
        });
        ici_editViewGroup.setBackgroundResource(R.drawable.ici28b_edit_status);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (redBackGround) {
                    ici_editViewGroup.setBackgroundResource(R.drawable.ici28b_edit_error);
                } else if (hasFocus) {
                    ici_editViewGroup.setBackgroundResource(R.drawable.ici28b_edit_press);
                } else {
                    ici_editViewGroup.setBackgroundResource(R.drawable.ici28b_edit_bg_normal);
                }
            }
        });

        showIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowPsw) {
                    isShowPsw = false;
                    showIV.setImageResource(R.drawable.ici28c_edit_hide);
                    editText.setInputType(inputType);
                    editText.setSelection(editText.getText().length());
                } else {
                    isShowPsw = true;
                    showIV.setImageResource(R.drawable.ici28c_edit_show);
                    inputType = editText.getInputType();
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    editText.setSelection(editText.getText().length());
                }
            }
        });

        rightMsgNormalColor = 0xFFB0BEE1;
        rightMsgErrorColor = 0xFFED2D30;

        editText.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        suggestTV.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
        errorTV.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN));
    }
}
