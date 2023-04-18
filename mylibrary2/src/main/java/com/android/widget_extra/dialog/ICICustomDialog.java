package com.android.widget_extra.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.StaticLayout;
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

import com.android.widget_extra.scroll.ICIVerticalScrollView;
import com.android.widget_extra.selection.ICICheckBox;
import com.android.widget_extra.utils.FontUtils;
import com.wjs.android.mylibrary2.R;

public class ICICustomDialog extends RelativeLayout {
    private OnDialogButtonClickListener checkBoxClickListener, firstButtonClickListener, secondButtonClickListener;
    private TextView titleView;
    private TextView contentView;
    private ICICheckBox checkBox;
    private TextView remindView;
    private Button firstButton, secondButton;
    private ImageView dialogBackgroundIV;
    private View checkboxView, dialogBgParent, topSplit;
    private ICIVerticalScrollView contentScrollView;
    private int textSize;

    private ICICustomDialog(Context context) {
        super(context);
        init();
    }

    private ICICustomDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private ICICustomDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private ICICustomDialog(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.ici_custom_dialog, ICICustomDialog.this);
        contentScrollView = findViewById(R.id.ici_dialog_scroll);
        titleView = findViewById(R.id.ici_dialog_title);
        contentView = findViewById(R.id.ici_dialog_content);
        checkboxView = findViewById(R.id.ici_dialog_ll_checkbox);
        checkBox = findViewById(R.id.ici_dialog_checkbox);
        remindView = findViewById(R.id.ici_dialog_remind);
        firstButton = findViewById(R.id.ici_dialog_btn1);
        secondButton = findViewById(R.id.ici_dialog_btn2);
        dialogBgParent = findViewById(R.id.ici28_dialogvisiable);
        dialogBackgroundIV = findViewById(R.id.ici_dialog_bg);
        topSplit = findViewById(R.id.ici_dialog_topsplite);

        textSize = 36;
        dialogBackgroundIV.setImageResource(R.drawable.ici28b_dialogs_bg);
        dialogBgParent.setBackgroundColor(Color.parseColor("#541A1616"));
        contentView.setTextColor(Color.parseColor("#FFFFFFFF"));
        remindView.setTextColor(Color.parseColor("#FFFFFFFF"));
        topSplit.setBackgroundColor(Color.parseColor("#4DB0BEE1"));

        firstButton.setBackgroundResource(R.drawable.ici28b_dialog_button_status);
        firstButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        secondButton.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        titleView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        contentView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        remindView.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT));
        remindView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public static class Builder {
        private Context context;
        private ICICustomDialog dialogView;
        private int line;
        private Dialog dialog;
        private float dimAmount = 0.7f;

        public Builder(Context context) {
            this.context = context;
            dialogView = new ICICustomDialog(context);
        }

        public Builder setTitle(String title) {
            dialogView.titleView.setText(title);
            return this;
        }

        public Builder setContent(String content) {
            dialogView.contentView.setText(content);
            return this;
        }

        public Builder setRemind(String remind) {
            dialogView.remindView.setText(remind);
            return this;
        }

        public Builder setFirstButton(String buttonName, OnDialogButtonClickListener onClickListener, boolean isOrange) {
            dialogView.firstButtonClickListener = onClickListener;

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
            dialogView.firstButton.setText(buttonName);
            return this;
        }

        public Builder setSecondButton(String buttonName, OnDialogButtonClickListener onClickListener) {
            dialogView.secondButtonClickListener = onClickListener;
            dialogView.secondButton.setText(buttonName);
            return this;
        }

        public Builder setCheckBox(OnDialogButtonClickListener onClickListener) {
            dialogView.checkBoxClickListener = onClickListener;
            return this;
        }

        public Dialog build() {
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(dialogView.textSize);
            textPaint.setTypeface(FontUtils.getTypeFace(FontUtils.TYPE_THIN));
            int textMaxWidth = 990;
            String content = dialogView.contentView.getText().toString();
            StaticLayout staticLayout = StaticLayout.Builder.obtain(content, 0, content.length(), textPaint, textMaxWidth).build();
            line = staticLayout.getLineCount();
            if (line > 3) {
                line = 3;
            }


            LayoutParams params = (LayoutParams) dialogView.contentScrollView.getLayoutParams();
            params.topMargin = 152;
            params.leftMargin = 75;
            params.rightMargin = 75;
            params.width = 990;
            dialogView.contentScrollView.setLayoutParams(params);

            dialogView.checkboxView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogView.checkBoxClickListener != null) {
                        dialogView.checkBox.setChecked(!dialogView.checkBox.isChecked());
                        dialogView.checkBoxClickListener.onButtonClick(dialog, dialogView.checkBox.isChecked() ? 3 : 4);
                    }
                }
            });

            dialogView.firstButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogView.firstButtonClickListener != null) {
                        dialogView.firstButtonClickListener.onButtonClick(dialog, 1);
                    }
                }
            });
            dialogView.secondButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogView.secondButtonClickListener != null) {
                        dialogView.secondButtonClickListener.onButtonClick(dialog, 2);
                    }
                }
            });

            dialog = new Dialog(context, R.style.ici_dialog_style);
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
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
                default:
            }

            lp.width = 1140;

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
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            return dialog;
        }
    }

    public interface OnDialogButtonClickListener {
        void onButtonClick(Dialog dialog, int which);
    }
}
