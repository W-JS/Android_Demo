package com.wjs.android.demo.widgetstest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.widget_extra.toast.ICIToast;
import com.wjs.android.demo.R;

public class ToastActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn1 = findViewById(R.id.sample_toast1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICIToast.makeToastOnlyText(ToastActivity.this, "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", 2).show();
            }
        });

        findViewById(R.id.sample_toast2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICIToast.makeToastWithIcon(ToastActivity.this, getResources().getDrawable(R.drawable.ici_toast_favorites), "收藏达到上限", 1).show();
            }
        });

        findViewById(R.id.sample_toast3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICIToast.makeToastWithAction(ToastActivity.this, "收藏内容达到了上限", "确定", 1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
            }
        });

        findViewById(R.id.sample_toast4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICIToast.makeDialogToastOnlyText(ToastActivity.this, "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", 5000).show();

            }
        });

        findViewById(R.id.sample_toast5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICIToast.makeDialogToastWithIcon(ToastActivity.this, getResources().getDrawable(R.drawable.ici_toast_favorites), "收藏达到上限", 5000).show();
            }
        });

        findViewById(R.id.sample_toast6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICIToast.makeDialogToastWithAction(ToastActivity.this, "收藏达到上限", 5000, "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toast;
    }

    @Override
    public void onClick(View v) {
    }
}
