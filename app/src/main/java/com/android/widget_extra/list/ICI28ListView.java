package com.android.widget_extra.list;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

//import com.android.internal.R;
import com.android.widget_extra.button.ICIIconButton;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.selection.ICICheckBox;
import com.android.widget_extra.selection.ICIRadioButton;
import com.android.widget_extra.selection.ICISwitchButton;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.demo.R;

public class ICI28ListView extends LinearLayout implements Checkable, ICICommonView {
    private static final int ACTION_WIDTH = 176;
    private boolean hasLeftImageView;
    private int line = 0;
    private int actionNum = 0;
    private LinearLayout textGroup, rightGroup;
    private ICI28CActionView actionGroup;
    private ImageView leftImageView;
    private View leftGap, rightGap;
    private ICI28ListChild ici28ListChild;
    private ICIListDimen iciListDimen;
    private boolean hasBottomLine = true;
    private Drawable selectDrawable;
    private Scroller mScorller;


    private Typeface defaltFont;
    private ColorStateList mainTextColor, secoTextColor;
    private Drawable pressStatusDrawable;
    private int bottomLineColor, nextDrawableResouce;
    private boolean adaptiveHeight = true;
    private Resources resources;

    public ICI28ListView(Context context) {
        super(context);
        init();
    }

    public ICI28ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICI28ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (actionNum > 0) {
            actionGroup.layout(getMeasuredWidth(), 0, getMeasuredWidth() + ACTION_WIDTH * actionNum, b);
        }


    }


    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }

    @Override
    public void initCher() {
        defaltFont = FontUtils.getTypeFace(FontUtils.TYPE_THIN);
        selectDrawable = resources.getDrawable(R.drawable.ici28c_list_press_bg, null);
        pressStatusDrawable = resources.getDrawable(R.drawable.ici28c_list_bg, null);
        setBackground(pressStatusDrawable);
        mainTextColor = resources.getColorStateList(R.color.ici28c_color_list_main_tv, null);
        secoTextColor = resources.getColorStateList(R.color.ici28c_color_list_item_two_tv, null);
        bottomLineColor = 0x33B0BEE1;
        nextDrawableResouce = R.drawable.ici28c_ic_list_next_normal;
    }

    @Override
    public void initBuck() {
        defaltFont = FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN);
        selectDrawable = resources.getDrawable(R.drawable.ici28b_list_bg_preseed, null);
        pressStatusDrawable = resources.getDrawable(R.drawable.ici28b_list_bg, null);
        setBackground(pressStatusDrawable);
        mainTextColor = resources.getColorStateList(R.color.ici28b_color_list_main_tv, null);
        secoTextColor = resources.getColorStateList(R.color.ici28b_color_list_item_two_tv, null);
        bottomLineColor = 0x33767F9D;
        nextDrawableResouce = R.drawable.ici28b_ic_72_listnext_normal;
    }


    @Override
    public void initCommon() {
        resources = getResources();
        LayoutInflater.from(getContext()).inflate(R.layout.ici28c_list, this);
        setOrientation(HORIZONTAL);

        textGroup = findViewById(R.id.ici_list_textgroup);
        rightGroup = findViewById(R.id.ici_list_rightgroup);
        leftImageView = findViewById(R.id.ici_list_leftimage);
        ici28ListChild = new ICI28ListChild();

        leftGap = findViewById(R.id.ici_list_leftgap);
        rightGap = findViewById(R.id.ici_list_right_gap);
        iciListDimen = new ICIListDimen();


        setClickable(true);
        setClipChildren(false);

        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(getContext(), R.animator.ici28c_list_press_animation);
        setStateListAnimator(stateListAnimator);
        mScorller = new Scroller(getContext());
    }

    public void reset() {
        hasLeftImageView = false;
        leftImageView = null;
        line = 1;
        actionNum = 0;

        textGroup.removeAllViews();
        rightGroup.removeAllViews();
//        actionGroup.removeAllViews();
        ici28ListChild = new ICI28ListChild();

        originHeight = 0;
        extraHeights[0] = 0;
        extraHeights[1] = 0;
        extraHeights[2] = 0;
        adaptiveHeight = true;
    }

    public void setAdaptiveHeight(boolean adaptiveHeight) {
        this.adaptiveHeight = adaptiveHeight;
    }

    public void setBackgoundAplha(int aplha) {
        if (!isPressed) {
            setBackground(pressStatusDrawable);
            return;
        }
        isPressed = false;
        setBackground(selectDrawable);
        getBackground().mutate().setAlpha(aplha);
        if (aplha == 0) {
            setBackground(pressStatusDrawable);
        }
    }

    public void refreshView() {
        int newLine = 1;
        if (ici28ListChild.getTextView2nd() != null && ici28ListChild.getTextView2nd().getVisibility() == VISIBLE) {
            newLine++;
        }
        if (ici28ListChild.getTextView3rd() != null && ici28ListChild.getTextView3rd().getVisibility() == VISIBLE) {
            newLine++;
        }
        line = newLine;

        if (hasLeftImageView) {
            leftImageView.setVisibility(VISIBLE);
            ici28ListChild.getLeftImageView().setVisibility(VISIBLE);
            iciListDimen.setLeftGap(ICIListDimen.LEFTGAP_HASIMAGE);
        } else {
            if (ici28ListChild.getLeftImageView() != null) {
                ici28ListChild.getLeftImageView().setVisibility(GONE);
            }
            iciListDimen.setLeftGap(ICIListDimen.LEFTGAP_NOIMAGE);
        }


        switch (line) {
            case 1:
                if (ici28ListChild.getMainTextGroup() != null) {
                    LinearLayout.LayoutParams mainParams = (LinearLayout.LayoutParams) ici28ListChild.getMainTextGroup().getLayoutParams();
                    mainParams.topMargin = 34;
                    ici28ListChild.getMainTextGroup().setLayoutParams(mainParams);
                    if (ici28ListChild.getMainTextView() != null) {
                        LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) ici28ListChild.getMainTextView().getLayoutParams();
                        textParams.topMargin = 0;
                        ici28ListChild.getMainTextView().setLayoutParams(textParams);
                    }
                } else if (ici28ListChild.getMainTextView() != null) {
                    LinearLayout.LayoutParams mainParams = (LinearLayout.LayoutParams) ici28ListChild.getMainTextView().getLayoutParams();
                    mainParams.topMargin = 34;
                    ici28ListChild.getMainTextView().setLayoutParams(mainParams);
                }
//                if (ici28ListChild.getNotiImageView() != null)
//                {
//                    RelativeLayout.LayoutParams notiParams = (RelativeLayout.LayoutParams) ici28ListChild.getNotiImageView().getLayoutParams();
//                    notiParams.topMargin = 34+7;
//                    notiParams.leftMargin = 20;
//                    ici28ListChild.getNotiImageView().setLayoutParams(notiParams);
//                }
                break;
            case 3:
                LinearLayout.LayoutParams thridTextParam = (LinearLayout.LayoutParams) ici28ListChild.getTextView3rd().getLayoutParams();
                thridTextParam.topMargin = ICIListDimen.MID_2RDTEXT_3RD_GAP;
                ici28ListChild.getTextView3rd().setLayoutParams(thridTextParam);
            case 2:
                if (ici28ListChild.getMainTextGroup() != null) {
                    LinearLayout.LayoutParams mainParams = (LinearLayout.LayoutParams) ici28ListChild.getMainTextGroup().getLayoutParams();
                    mainParams.topMargin = 34;
                    ici28ListChild.getMainTextGroup().setLayoutParams(mainParams);
                    if (ici28ListChild.getMainTextView() != null) {
                        LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) ici28ListChild.getMainTextView().getLayoutParams();
                        textParams.topMargin = 0;
                        ici28ListChild.getMainTextView().setLayoutParams(textParams);
                    }

//                    if (ici28ListChild.getTextView2nd() != null)
//                    {
//                        LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) ici28ListChild.getTextView2nd().getLayoutParams();
//                        textParams.topMargin =0;
//                        ici28ListChild.getMainTextView().setLayoutParams(textParams);
//                    }
                } else if (ici28ListChild.getMainTextView() != null) {
                    LinearLayout.LayoutParams mainTextParam = (LinearLayout.LayoutParams) ici28ListChild.getMainTextView().getLayoutParams();
                    mainTextParam.topMargin = 28;
                    ici28ListChild.getMainTextView().setLayoutParams(mainTextParam);

                }
                LinearLayout.LayoutParams secondTextParam = (LinearLayout.LayoutParams) ici28ListChild.getTextView2nd().getLayoutParams();
                secondTextParam.topMargin = ICIListDimen.MID_MIANTEXT_2RDTEXT_GAP;
                ici28ListChild.getTextView2nd().setLayoutParams(secondTextParam);
//                if (ici28ListChild.getNotiImageView() != null)
//                {
//                    RelativeLayout.LayoutParams notiParams = (RelativeLayout.LayoutParams) ici28ListChild.getNotiImageView().getLayoutParams();
//                    notiParams.topMargin = 28+7;
//                    notiParams.leftMargin = 20;
//                    ici28ListChild.getNotiImageView().setLayoutParams(notiParams);
//                }
                break;
        }
        //添加右侧switch checkbox,radiobutton,nextImageView
        if (ici28ListChild.getRightSwitchButton() != null
                || ici28ListChild.getRightCheckbox() != null
                || ici28ListChild.getRightMoreImageView() != null
                || ici28ListChild.getRightRadioButton() != null
        ) {
            LinearLayout.LayoutParams rightGapParams = (LinearLayout.LayoutParams) rightGap.getLayoutParams();
            rightGapParams.width = ICIListDimen.RIGHT_GAP_HAS_WIDGET;
            rightGap.setLayoutParams(rightGapParams);
//            if (ici28ListChild.getRightSwitchButton() !=null)
//            {
//                rightGroup.addView(ici28ListChild.getRightSwitchButton());
//            }else if (ici28ListChild.getRightCheckbox() != null)
//            {
//                rightGroup.addView(ici28ListChild.getRightCheckbox());
//            }else if (ici28ListChild.getRightMoreImageView()!= null)
//            {
//                rightGroup.addView(ici28ListChild.getRightMoreImageView());
//            }else if (ici28ListChild.getRightRadioButton()!= null ){
//                rightGroup.addView(ici28ListChild.getRightRadioButton());
//            }
        }

        //右侧图片按钮
        if (ici28ListChild.getRightImageViews().size() != 0) {
            LinearLayout.LayoutParams rightGapParams = (LinearLayout.LayoutParams) rightGap.getLayoutParams();
            rightGapParams.width = ICIListDimen.RIGHT_GAP_HAS_IMAGEVIEW;
            for (int i = 0; i < ici28ListChild.getRightImageViews().size(); i++) {
                ImageView rightImageView = ici28ListChild.getRightImageViews().get(i);
                if (i != 0) {
                    LinearLayout.LayoutParams rightImageParam = (LinearLayout.LayoutParams) rightImageView.getLayoutParams();
                    rightImageParam.leftMargin = ICIListDimen.RIGHT_IAMGEVIEW_GAP;
                    rightImageView.setLayoutParams(rightImageParam);
                }
//                rightGroup.addView(rightImageView,rightGapParams);
            }
        }
        LinearLayout.LayoutParams leftParams = (LinearLayout.LayoutParams) leftGap.getLayoutParams();
        leftParams.width = iciListDimen.getLeftGap();
        leftGap.setLayoutParams(leftParams);
        requestLayout();

        switch (line) {
            case 1:
                originHeight = 120;
                break;
            case 2:
                originHeight = 150;
                break;
            case 3:
                originHeight = 192;
                break;
        }


        resetViewHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private Paint mPaint = new Paint();
    private int width, height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (hasBottomLine) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(bottomLineColor);
            canvas.drawRect(117 + getPaddingLeft(), height - 1, width - getPaddingRight(), height, mPaint);
        }
    }

    public void setHasBottomLine(boolean hasBottomLine) {
        this.hasBottomLine = hasBottomLine;
    }

    public ImageView addLeftImageView(int resId) {
        hasLeftImageView = true;
        leftImageView.setImageResource(resId);
        leftImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ici28ListChild.setLeftImageView(leftImageView);
        refreshView();
        return leftImageView;
    }

    private void getTextWidth(String text) {
        TextPaint textPaint = new TextPaint();

    }

    public ImageView addRightImageView(int resId) {
        ICIIconButton imageView = new ICIIconButton(getContext());
        imageView.setImageResource(resId);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setPadding(30, (width - 60) / 2, 30, (width - 60) / 2);
        if (ici28ListChild.getRightImageViews().size() >= 1) {
            params.leftMargin = 10;
        }
        ici28ListChild.getRightImageViews().add(imageView);
        rightGroup.addView(imageView, params);
        refreshView();
        return imageView;
    }

    public ICIRadioButton addRightRadioButton() {
        ICIRadioButton radioButton = new ICIRadioButton(getContext());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(72, 72);
        radioButton.setPadding(10, 10, 10, 10);
        ici28ListChild.setRightRadioButton(radioButton);
        rightGroup.addView(radioButton, param);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked = ici28ListChild.getRightRadioButton().isChecked();
                if (!ischecked) {
                    ici28ListChild.getRightRadioButton().setChecked(true);
                }
            }
        });
        refreshView();
        return radioButton;
    }

    public ICISwitchButton addRightSwitchButton() {

        ICISwitchButton iciSwitchButton = new ICISwitchButton(getContext());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ici28ListChild.setRightSwitchButton(iciSwitchButton);
        rightGroup.addView(iciSwitchButton, param);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ici28ListChild.getRightSwitchButton().isChecked();
                if (isChecked) {
                    ici28ListChild.getRightSwitchButton().setChecked(false);
                } else {
                    ici28ListChild.getRightSwitchButton().setChecked(true);
                }
            }
        });
        refreshView();
        return iciSwitchButton;
    }


    public ICICheckBox addRightCheckBox() {
        ICICheckBox iciCheckBox = new ICICheckBox(getContext());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(72, 72);
        iciCheckBox.setPadding(0, 0, 0, 0);
        ici28ListChild.setRightCheckbox(iciCheckBox);
        rightGroup.addView(iciCheckBox, param);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ici28ListChild.getRightCheckbox().isChecked();
                if (isChecked) {
                    ici28ListChild.getRightCheckbox().setChecked(false);
                } else {
                    ici28ListChild.getRightCheckbox().setChecked(true);
                }
            }
        });
        refreshView();
        return iciCheckBox;
    }

    public ImageView addRightNextImageView() {
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(176, ViewGroup.LayoutParams.MATCH_PARENT);
        param.width = 60;
        param.height = 72;
        param.gravity = Gravity.CENTER;
        imageView.setImageResource(nextDrawableResouce);
        rightGroup.addView(imageView, param);
        ici28ListChild.setRightMoreImageView(imageView);
        refreshView();
        return imageView;
    }

    @Deprecated
    public ImageView addActionImageView(int resid, int color) {
        if (actionGroup == null) {
            actionGroup = new ICI28CActionView(getContext());
            addView(actionGroup);
            actionNum = 2;
        }


        ImageView imageView = new ImageView(getContext());
        refreshView();
        return imageView;
    }

    public ICI28CActionView addActionView() {
        if (actionGroup == null) {
            actionGroup = new ICI28CActionView(getContext());
            addView(actionGroup);
        }
        actionNum = 2;
        return actionGroup;
    }

    public TextView addMainTextView() {
        line++;

        final TextView mainTextView = new TextView(getContext());
        mainTextView.setTextColor(mainTextColor);
        mainTextView.setTextSize(resources.getDimension(R.dimen.ici_list_dimen_maintext));
        mainTextView.setTypeface(defaltFont);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mainTextView.setMaxWidth(500);

        if (ici28ListChild.getMainTextGroup() != null) {
            //当有notiimage时  textgroup第一项为一个横向布局的linear  textview添加到这个 children View第一个
            ici28ListChild.getMainTextGroup().addView(mainTextView, 0, params);
        } else {
            // 当没有时TextView直接添加入textGroup 第一行
            textGroup.addView(mainTextView, params);
        }
        mainTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(resources.getDimension(R.dimen.ici_list_dimen_maintext));
                textPaint.setTypeface(defaltFont);
                int width = (int) textPaint.measureText(s.toString());
                //line+1为当前
                int line = width / mainTextView.getMaxWidth();
                extraHeights[0] = line * 52;
                refreshView();
            }
        });
        ici28ListChild.setMainTextView(mainTextView);
        refreshView();
        return mainTextView;
    }


    private void resetViewHeight() {
        int height = originHeight;
        if (ici28ListChild.getMainTextView() != null && ici28ListChild.getMainTextView().getVisibility() == VISIBLE && ici28ListChild.getMainTextView().getMaxLines() > 1) {
            height = height + extraHeights[0];
        }
        if (ici28ListChild.getTextView2nd() != null && ici28ListChild.getTextView2nd().getVisibility() == VISIBLE && ici28ListChild.getTextView2nd().getMaxLines() > 1) {
            height = height + extraHeights[1];
        }
        if (ici28ListChild.getTextView3rd() != null && ici28ListChild.getTextView3rd().getVisibility() == VISIBLE && ici28ListChild.getTextView3rd().getMaxLines() > 1) {
            height = height + extraHeights[2];
        }
        if (getLayoutParams().height != height) {
            getLayoutParams().height = height;
        }
    }


    public TextView add2ndTextView() {
        final ICIListTextView textView2 = new ICIListTextView(getContext());
        textView2.setTextSize(resources.getDimension(R.dimen.ici_list_dimen_othertext));
        textView2.setTextColor(secoTextColor);
        textView2.setTypeface(defaltFont);
        textView2.setMaxWidth(500);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textGroup.addView(textView2, params);
        ici28ListChild.setTextView2nd(textView2);
        textView2.setOnVisiableChangeLisnter(new ICIListTextView.OnVisiableChangeLisnter() {
            @Override
            public void onViewVisichange() {
                refreshView();
            }
        });
        textView2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //根据输入文字多少 动态调整控件高度


                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(resources.getDimension(R.dimen.ici_list_dimen_othertext));
                textPaint.setTypeface(defaltFont);
                int width = (int) textPaint.measureText(s.toString());
                //line+1为当前
                int line = width / textView2.getMaxWidth();
                extraHeights[1] = line * 32;

                resetViewHeight();
            }
        });
        line++;
        refreshView();
        return textView2;
    }


    @Override
    protected void dispatchVisibilityChanged(View changedView, int visibility) {
        super.dispatchVisibilityChanged(changedView, visibility);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (changedView == ici28ListChild.getTextView2nd() || changedView == ici28ListChild.getTextView3rd()) {
            refreshView();
        }
    }

    public TextView add3rdTextView() {
        ICIListTextView textView3 = new ICIListTextView(getContext());
        textView3.setTextSize(resources.getDimension(R.dimen.ici_list_dimen_othertext));
        textView3.setMaxWidth(500);
        textView3.setTextColor(secoTextColor);
        textView3.setTypeface(defaltFont);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        line++;
        textGroup.addView(textView3, params);
        ici28ListChild.setTextView3rd(textView3);
        textView3.setOnVisiableChangeLisnter(new ICIListTextView.OnVisiableChangeLisnter() {
            @Override
            public void onViewVisichange() {
                refreshView();
            }
        });
        textView3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //根据输入文字多少 动态调整控件高度
                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(resources.getDimension(R.dimen.ici_list_dimen_othertext));
                textPaint.setTypeface(defaltFont);
                int width = (int) textPaint.measureText(s.toString());
                //line+1为当前
                int line = width / 500;
                extraHeights[2] = line * 32;
                resetViewHeight();
            }
        });
        refreshView();
        return textView3;
    }

    public ImageView addNotiImageView(int resId) {

        //已经添加第一行 先删除mainTextview使用横向布局Linear替换 便于图片添加
        if (ici28ListChild.getMainTextView() != null) {
            textGroup.removeView(ici28ListChild.getMainTextView());
        }

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams mainTextChildrenParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(mainTextChildrenParam);
        textGroup.addView(linearLayout, 0);
        ici28ListChild.setMainTextGroup(linearLayout);

        //这里重新将mainTextView添加到子布局Linear中
        if (ici28ListChild.getMainTextView() != null) {
            linearLayout.addView(ici28ListChild.getMainTextView());
        }

        //添加图片
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setImageResource(resId);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.addView(imageView, imageParams);
        LinearLayout.LayoutParams relativeParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeParams.gravity = Gravity.CENTER;
        ici28ListChild.getMainTextGroup().addView(relativeLayout, relativeParams);
        ici28ListChild.setNotiImageView(imageView);
        return imageView;
    }

    public int ACTION_OVER = 40;
    public int touchOrien;
    public static final int TOUCH_ORIENT_NONE = -1;
    public static final int TOUCH_ORIENT_X = 0;
    public static final int TOUCH_ORIRNT_Y = 1;
    public int mLastX, mLastY;
    private int touchX;
    private boolean isActionAllVisiable = false;
    private boolean isDeleteOpen = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (actionNum > 0) {
            int actionMenuWidth = ACTION_WIDTH * actionNum;
            int scrollX = getScrollX();
            int x = (int) event.getX();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (getScrollX() == actionMenuWidth) {
                        isActionAllVisiable = true;
                    } else {
                        isActionAllVisiable = false;
                    }
                    mLastX = (int) event.getX();
                    mLastY = (int) event.getY();
                    touchX = (int) event.getX();
                    touchOrien = TOUCH_ORIENT_NONE;
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (touchOrien == TOUCH_ORIENT_NONE) {
                        int xGap = (int) Math.abs(mLastX - event.getX());
                        int yGap = (int) Math.abs(mLastY - event.getY());
                        if (xGap >= yGap) {
                            touchOrien = TOUCH_ORIENT_X;
                        } else {
                            getParent().requestDisallowInterceptTouchEvent(false);
                            return false;
                        }
                    }
                    int move = mLastX - x;
                    if (scrollX + move < actionMenuWidth + ACTION_OVER && scrollX + move > 0) {
                        scrollTo(scrollX + move, 0);
                    } else if (scrollX + move < 0) {
                        scrollTo(0, 0);
                    }
                    if (scrollX + move >= actionMenuWidth + ACTION_OVER) {
                        scrollTo(actionMenuWidth + ACTION_OVER - 1, 0);
                    }
                    if (scrollX + move >= actionMenuWidth + ACTION_OVER && isActionAllVisiable) {
                        isDeleteOpen = true;
                        scrollTo(actionMenuWidth + ACTION_OVER, 0);
                    }
                    mLastX = x;
                    actionGroup.setVisiWidth(getScrollX());
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchOrien = TOUCH_ORIENT_NONE;
                    if (isDeleteOpen) {
                        isDeleteOpen = false;

                        closeActionView();
                        originHeight = getLayoutParams().height;
                        Animator animator = AnimatorInflater.loadAnimator(getContext(), R.animator.ici28c_list_delete);
                        animator.setTarget(ICI28ListView.this);
                        animator.start();

                    } else if (scrollX > (actionMenuWidth / 2) && scrollX < actionMenuWidth + ACTION_OVER) {
                        if (x - touchX < 0) {
                            mScorller.startScroll(scrollX, 0, actionMenuWidth - scrollX, 0, 240);

                        } else {
                            mScorller.startScroll(scrollX, 0, actionMenuWidth - scrollX, 0, 240);
                        }
                        //                        scrollTo(actionMenuWidth,0);
                        requestLayout();
                    } else {
                        mScorller.startScroll(scrollX, 0, -scrollX, 0, 240);
//                        scrollTo(0,0);
                    }
                    break;

            }

        }
        return super.onTouchEvent(event);
    }


    private int originHeight = 0;
    private int[] extraHeights = new int[]{0, 0, 0};

    public void setViewHeight(float progress) {
        if (progress == 0) {
            scrollTo(0, 0);
            getLayoutParams().height = originHeight;
            actionGroup.onPressRealse();
        }

    }

    private void closeActionView() {
        scrollTo(0, 0);
//        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.ici28c_list_delete_hide_anima);
//        this.startAnimation(animation);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        int actionMenuWidth = ACTION_WIDTH * actionNum;
        if (mScorller.computeScrollOffset()) {
            scrollTo(mScorller.getCurrX(), 0);
            actionGroup.setVisiWidth(getScrollX());
            requestLayout();
        }

    }

    private float xDistance, yDistance, xLast, yLast;
    private boolean isPressed = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (actionNum > 0) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //父容器禁止拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                    xDistance = yDistance = 0f;
                    xLast = ev.getX();
                    yLast = ev.getY();
                    isPressed = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float curX = ev.getX();
                    final float curY = ev.getY();

                    xDistance += Math.abs(curX - xLast);
                    yDistance += Math.abs(curY - yLast);
                    xLast = curX;
                    yLast = curY;
                    if (xDistance <= ViewConfiguration.get(getContext()).getScaledTouchSlop()
                            || yDistance <= ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return super.onInterceptTouchEvent(ev);
                    }
                    if (xDistance < yDistance) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        return super.onInterceptTouchEvent(ev);
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    getParent().requestDisallowInterceptTouchEvent(false);
                    break;
                default:
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace);
        int[] newState = null;
        if (checked) {
            newState = new int[drawableState.length + 1];
            System.arraycopy(drawableState, 0, newState, 0, drawableState.length);
            mergeDrawableStates(newState, new int[]{android.R.attr.state_checked});
        } else {
            newState = drawableState;
        }
        return newState;

    }

    public void setDisable(boolean disable) {
        setEnabled(disable);
    }

    public void setOnListActionClick(ICI28CActionView.OnListActionClick onListActionClick) {

    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (line >= 3) {
            ici28ListChild.getTextView3rd().setEnabled(enabled);
        }
        if (line >= 2) {
            ici28ListChild.getTextView2nd().setEnabled(enabled);
        }
        if (line >= 1) {
            ici28ListChild.getMainTextView().setEnabled(enabled);
        }
    }

    private boolean checked;

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        setChecked(false);
    }


    public void hideAction() {
        if (actionNum > 0 && getScrollX() >= 0) {
            scrollTo(0, 0);
        }
    }


}
