package com.android.widget_extra.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.StaticLayout;

//import com.android.internal.R;
import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.scroll.ICIVerticalScrollView;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.demo.R;

public class ICIDialog extends RelativeLayout implements ICICommonView {
    private OnDialogButtonClickLinster firsDialogtButtonClickListner, sencondDialogButtonClickListner, thridDialogButtonClickListner;
    private TextView titleView;
    private TextView contentView;
    private ImageView imageView;
    private Button firstButton, sencondButton, thridButton;
    private View buttonSplit1, buttonSplit2;
    private ImageView dialogBackgroundIV;
    private View dialogBgParent, topSplit;
    private ICIVerticalScrollView contentScrollView;
    private int textSize;

    private ICIDialog(Context context) {
        super(context);
        init();
    }

    private ICIDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private ICIDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ICIDialog(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        ICICommonViewManager.viewInit(this, getContext());
    }

    @Override
    public void initCommon() {
        LayoutInflater.from(getContext()).inflate(R.layout.ici_dialog, ICIDialog.this);
        contentScrollView = findViewById(R.id.ici_dialog_scroll);
        titleView = findViewById(R.id.ici_dialog_title);
        contentView = findViewById(R.id.ici_dialog_content);
        imageView = findViewById(R.id.ici_dialog_icon);
        firstButton = findViewById(R.id.ici_dialog_btn1);
        sencondButton = findViewById(R.id.ici_dialog_btn2);
        thridButton = findViewById(R.id.ici_dialog_btn3);
        buttonSplit1 = findViewById(R.id.ici_dialog_split1);
        buttonSplit2 = findViewById(R.id.ici_dialog_split2);
        dialogBgParent = findViewById(R.id.ici28_dialogvisiable);
        dialogBackgroundIV = findViewById(R.id.ici_dialog_bg);
        topSplit = findViewById(R.id.ici_dialog_topsplite);

    }

    @Override
    public void initCher() {
        textSize = 38;
        dialogBackgroundIV.setImageResource(R.drawable.ici28c_dialogs_bg);
        dialogBgParent.setBackgroundColor(Color.parseColor("#80000000"));
        contentView.setTextColor(getResources().getColor(R.color.ici_dialog_color_text));
        topSplit.setBackgroundColor(Color.parseColor("#4DB0BEE1"));

        firstButton.setBackgroundResource(R.drawable.ici28c_dialog_button_status);

        firstButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        sencondButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        thridButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        titleView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_LIGHT));
        contentView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));

    }

    @Override
    public void initBuck() {
        textSize = 36;
        dialogBackgroundIV.setImageResource(R.drawable.ici28b_dialogs_bg);
//        dialogBgParent.setBackgroundColor(Color.parseColor("#541A1616"));
        contentView.setTextColor(Color.parseColor("#FFFFFFFF"));
        topSplit.setBackgroundColor(Color.parseColor("#4DB0BEE1"));

        firstButton.setBackgroundResource(R.drawable.ici28b_dialog_button_status);

        firstButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        sencondButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        thridButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        titleView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        contentView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

    }

    public static class Builder {
        private boolean hasTitle, hasImage, hasSencondButton, hasthridButton;
        private Context context;
        private ICIDialog dialogView;
        private int line;
        private Dialog dialog;
        private float dimAmount = 0.7f;
        private int prority = -2000;

        public Builder(Context context) {
            this.context = context;
            dialogView = new ICIDialog(context);
        }

        public Builder setTitle(String title) {
            hasTitle = true;
            dialogView.titleView.setText(title);
            dialogView.titleView.setVisibility(VISIBLE);
            return this;
        }

        public Builder setContent(String content, int line) {
            dialogView.contentView.setText(content);
            return this;
        }

        public Builder setContentGravity(int gravity) {
            dialogView.contentView.setGravity(gravity);
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            hasImage = true;
            dialogView.imageView.setImageDrawable(drawable);
            dialogView.imageView.setVisibility(VISIBLE);
            return this;
        }


        public Builder setFirstButton(String buttonName, OnDialogButtonClickLinster onClickListener, boolean isOrange) {
            dialogView.firsDialogtButtonClickListner = onClickListener;


            // for coverity
            Resources resources = context.getResources();
            if (resources == null) {
                return new Builder(context);
            }

            if (isOrange) {
                dialogView.firstButton.setTextColor(resources.getColor(R.color.ici28_dialog_button_orange, null));
            } else {
                dialogView.firstButton.setTextColor(resources.getColorStateList(R.color.ici28c_dialog_button_white_status, null));
            }
            dialogView.firstButton.setVisibility(VISIBLE);
            dialogView.firstButton.setText(buttonName);
            return this;
        }

        public Builder setSecondButton(String buttonName, OnDialogButtonClickLinster onClickListener) {
            dialogView.sencondDialogButtonClickListner = onClickListener;
            hasSencondButton = true;
            dialogView.sencondButton.setText(buttonName);
            dialogView.buttonSplit1.setVisibility(VISIBLE);
            dialogView.sencondButton.setVisibility(VISIBLE);
            return this;
        }

        public Builder setThridButton(String buttonName, OnDialogButtonClickLinster onClickListener) {
            dialogView.thridDialogButtonClickListner = onClickListener;
            dialogView.thridButton.setText(buttonName);
            dialogView.thridButton.setVisibility(VISIBLE);
            dialogView.buttonSplit2.setVisibility(VISIBLE);
            hasthridButton = true;
            return this;
        }

        public Builder setPrority(int prority) {
            this.prority = prority;
            return this;
        }

        @Deprecated
        public Builder setFirstButton(String buttonName, OnClickListener onClickListener, boolean isOrange) {
            return this;
        }

        @Deprecated
        public Builder setSecondButton(String buttonName, OnClickListener onClickListener) {
            return this;
        }

        @Deprecated
        public Builder setThridButton(String buttonName, OnClickListener onClickListener) {
            return this;
        }

        public Builder setDimAmount(float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        public Dialog build() {
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(dialogView.textSize);
            textPaint.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
            int textWidth = (int) textPaint.measureText(dialogView.contentView.getText().toString());
            int textMaxWidth = 990;
            if (hasImage) {
                textMaxWidth = 900;
            }
            String content = dialogView.contentView.getText().toString();
            StaticLayout staticLayout = StaticLayout.Builder.obtain(content, 0, content.length(), textPaint, textMaxWidth).build();
            line = staticLayout.getLineCount();
            if (line > 3) {
                line = 3;
            }


            if (!hasTitle) {
                LayoutParams params = (LayoutParams) dialogView.contentScrollView.getLayoutParams();
                params.topMargin = 56;
                dialogView.contentScrollView.setLayoutParams(params);
            } else {
                dialogView.titleView.setVisibility(VISIBLE);
                LayoutParams params = (LayoutParams) dialogView.contentScrollView.getLayoutParams();
                params.topMargin = 152;
                dialogView.contentScrollView.setLayoutParams(params);
            }


            if (!hasImage) {
                LayoutParams params = (LayoutParams) dialogView.contentScrollView.getLayoutParams();
                params.leftMargin = 75;
                params.rightMargin = 75;
                params.width = 990;
                dialogView.contentScrollView.setLayoutParams(params);
            } else {
                LayoutParams params = (LayoutParams) dialogView.contentScrollView.getLayoutParams();
                params.leftMargin = 221;
                params.rightMargin = 75;
                params.width = 900;
                dialogView.contentScrollView.setLayoutParams(params);


                LayoutParams ImageParams = (LayoutParams) dialogView.imageView.getLayoutParams();

                if (!hasTitle) {
                    switch (line) {
                        case 1:
                            ImageParams.leftMargin = 60;
                            ImageParams.topMargin = 16;
                            break;
                        case 2:
                            ImageParams.leftMargin = 60;
                            ImageParams.topMargin = 41;
                            break;
                        case 3:
                            ImageParams.leftMargin = 60;
                            ImageParams.topMargin = 66;
                            break;
                    }
                } else {
                    switch (line) {
                        case 1:
                            ImageParams.leftMargin = 60;
                            ImageParams.topMargin = 112;
                            break;
                        case 2:
                            ImageParams.leftMargin = 61;
                            ImageParams.topMargin = 140;
                            break;
                        case 3:
                            ImageParams.leftMargin = 61;
                            ImageParams.topMargin = 162;
                            break;
                    }
                }

                dialogView.imageView.setLayoutParams(ImageParams);
            }

            dialogView.firstButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogView.firsDialogtButtonClickListner != null) {
                        dialogView.firsDialogtButtonClickListner.onButtonClick(dialog, 1);
                    }
                }
            });
            if (hasSencondButton) {
                dialogView.buttonSplit1.setVisibility(VISIBLE);
                dialogView.sencondButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialogView.sencondDialogButtonClickListner != null) {
                            dialogView.sencondDialogButtonClickListner.onButtonClick(dialog, 2);
                        }
                    }
                });
                dialogView.sencondButton.setVisibility(VISIBLE);
            }
            if (hasthridButton) {
                dialogView.buttonSplit2.setVisibility(VISIBLE);
                dialogView.thridButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialogView.thridDialogButtonClickListner != null) {
                            dialogView.thridDialogButtonClickListner.onButtonClick(dialog, 3);
                        }
                    }
                });
                dialogView.thridButton.setVisibility(VISIBLE);
            }


            dialog = new Dialog(context, R.style.ici_dialog_style);
            Window window = dialog.getWindow();
            if (prority != -2000) {
                window.setType(prority);
            }
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            if (!hasTitle) {
                switch (line) {
                    case 1:
                        lp.height = 272;
                        break;
                    case 2:
                        lp.height = 322;
                        break;
                    case 3:
                        lp.height = 372;
                        break;
                }
            } else {
                switch (line) {
                    case 1:
                        lp.height = 368;
                        break;
                    case 2:
                        lp.height = 418;
                        break;
                    case 3:
                        lp.height = 468;
                        break;
                }
            }

            if (hasImage) {
                lp.width = 1195;
            } else {
                lp.width = 1140;
            }

            LayoutParams contentScorllParam = (LayoutParams) dialogView.contentScrollView.getLayoutParams();
            switch (line) {
                case 1:
                    contentScorllParam.height = 50;
                    break;
                case 2:
                    contentScorllParam.height = 100;
                    break;
                case 3:
                default:
                    contentScorllParam.height = 150;
                    break;
            }
            dialogView.contentScrollView.setLayoutParams(contentScorllParam);

            lp.height = lp.height + 10;
            lp.width = lp.width + 20;

            ViewGroup.LayoutParams parent = new ViewGroup.LayoutParams(lp.width, lp.height);
            dialog.setContentView(dialogView, parent);
            lp.dimAmount = dimAmount;
            window.setWindowAnimations(R.style.ici_doalog_anim);
//            window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            return dialog;
        }
    }

    public interface OnDialogButtonClickLinster {
        public void onButtonClick(Dialog dialog, int which);
    }
}
