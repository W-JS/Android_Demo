package com.wjs.android.demo.widgetstest;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.android.widget_extra.dialog.ICIDialog;
import com.wjs.android.demo.R;

public class DialogActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button1 = findViewById(R.id.ici_dialog1);
        button1.setText("Signle line");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫二维码安装CarLife，使用USB连接手机和车载系统。"
                        , 0
                        , ""
                        , "我知道了"
                        , ""
                        , "");
            }
        });

        Button button2 = findViewById(R.id.ici_dialog2);
        button2.setText("Signle line With icon");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫二维码安装CarLife，并使用USB连接手。"
                        , R.drawable.ici28c_icon_60_warning
                        , ""
                        , "确定"
                        , "取消"
                        , "");
            }
        });

        Button button3 = findViewById(R.id.ici_dialog3);
        button3.setText("Signle line Only Title");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫二维码安装CarLife，使用USB连接手机和车载系统。"
                        , 0
                        , "系统升级发生错误"
                        , "我知道了"
                        , ""
                        , "");
            }
        });

        Button button4 = findViewById(R.id.ici_dialog4);
        button4.setText("Signle line With Title and Icon");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫二维码安装CarLife，使用USB连接手机。"
                        , R.drawable.ici28c_icon_60_warning
                        , "系统升级发生错误"
                        , "确定"
                        , "取消"
                        , "");
            }
        });

        Button button5 = findViewById(R.id.ici_dialog5);
        button5.setText("Two line");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装CarLife，并使用USB连接手机和车载系统。如遇问题，请检查网络连接是否在可用范围内。"
                        , 0
                        , ""
                        , "确定"
                        , "取消"
                        , "");
            }
        });

        Button button6 = findViewById(R.id.ici_dialog6);
        button6.setText("Two line With icon");
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装 CarLife，并使用USB连接手机和车载系统。如遇问题，请检查网络连接是否在可用范围内。"
                        , R.drawable.ici28c_icon_60_warning
                        , ""
                        , "确定"
                        , "取消"
                        , "");
            }
        });

        Button button7 = findViewById(R.id.ici_dialog7);
        button7.setText("Two line Only Title");
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装 CarLife，并使用USB连接手机和车载系统。如遇问题，请检查网络连接是否在可用范围内。"
                        , 0
                        , "系统升级发生错误"
                        , "确定"
                        , "取消"
                        , "");
            }
        });

        Button button8 = findViewById(R.id.ici_dialog8);
        button8.setText("Signle line With Title and Icon");
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装CarLife，使用USB连接手机和车载系统。请检查网络连接是否在可用范围内。"
                        , R.drawable.ici28c_icon_60_warning
                        , "系统升级发生错误"
                        , "确定"
                        , "取消"
                        , "");
            }
        });


        Button button9 = findViewById(R.id.ici_dialog9);
        button9.setText("Muti line");
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装CarLife，并使用USB连接手机和车载系统。如遇到更多问题，请检查网络连接是否在可用范围内，或者电话联系上海别克，400-800-6666。"
                        , 0
                        , ""
                        , "确定"
                        , "取消"
                        , "");
            }
        });

        Button button10 = findViewById(R.id.ici_dialog10);
        button10.setText("Muti line With icon");
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装CarLife，使用USB连接手机和车载系统。检查网络连接是否在可用范围内，或者电话联系上海别克中心，400-800-6666。"
                        , R.drawable.ici28c_icon_60_warning
                        , ""
                        , "查看详情"
                        , "确定"
                        , "取消");
            }
        });

        Button button11 = findViewById(R.id.ici_dialog11);
        button11.setText("Two line Only Title");
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装CarLife，并使用USB连接手机和车载系统。请检查网络连接是否在可用范围内，或者电话联系上海别克中心，400-800-6666。"
                        , 0
                        , "系统升级发生错误"
                        , "确定"
                        , "取消"
                        , "");
            }
        });

        Button button12 = findViewById(R.id.ici_dialog12);
        button12.setText("Signle line With Title and Icon");
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("请扫描二维码下载安装CarLife，并使用USB连接手机和车载系统。请检查网络连接是否在可用范围内，或者电话联系上海别克中心，400-800-6666。"
                        , R.drawable.ici28c_icon_60_warning
                        , "系统升级发生错误"
                        , "查看详情"
                        , "确定"
                        , "取消");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    public void onClick(View v) {
    }

    private void showSystemDialog() {
        Dialog iciDialog = new ICIDialog.Builder(this)
                .setTitle("我是标题")
                .setContent("我是内容", 1)
                .setFirstButton("第一个按钮", new ICIDialog.OnDialogButtonClickLinster() {
                    @Override
                    public void onButtonClick(Dialog dialog, int which) {
                        dialog.dismiss();
                    }
                }, true)
                .setSecondButton("第二个按钮", new ICIDialog.OnDialogButtonClickLinster() {
                    @Override
                    public void onButtonClick(Dialog dialog, int which) {

                    }
                })
                .setThridButton("第三个按钮", new ICIDialog.OnDialogButtonClickLinster() {
                    @Override
                    public void onButtonClick(Dialog dialog, int which) {

                    }
                })
                //设置优先级  不设置默认为普通dialog
                .setPrority(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
                .build();
        iciDialog.show();
    }

    private void showDialog(String content, int imageResId, String title, String button1, String button2, String button3) {
        ICIDialog.Builder builder = new ICIDialog.Builder(this);
        builder.setContent(content, 1);
        if (title != null && !title.equals("")) {
            builder.setTitle("我是标题");
        }
        if (imageResId != 0) {
            builder.setIcon(getResources().getDrawable(imageResId, null));
        }

        if (button1 != null && !button1.equals("")) {
            builder.setFirstButton(button1, new ICIDialog.OnDialogButtonClickLinster() {
                @Override
                public void onButtonClick(Dialog dialog, int which) {
                    dialog.dismiss();
                }
            }, true);
        }
        if (button2 != null && !button2.equals("")) {
            builder.setSecondButton(button2, new ICIDialog.OnDialogButtonClickLinster() {
                @Override
                public void onButtonClick(Dialog dialog, int which) {

                }
            });
        }
        if (button3 != null && !button3.equals("")) {
            builder.setThridButton(button3, new ICIDialog.OnDialogButtonClickLinster() {
                @Override
                public void onButtonClick(Dialog dialog, int which) {

                }
            });
        }

        //设置优先级  不设置默认为普通dialog
//        builder.setPrority(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
        builder.build().show();
    }

}
