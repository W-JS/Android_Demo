package com.wjs.android.demo.widgetstest;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.android.widget_extra.dialog.ICICustomDialog;
import com.android.widget_extra.dialog.ICIDialog;
import com.wjs.android.demo.R;

public class DialogActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button0 = findViewById(R.id.ici_dialog0);
        button0.setText("切换壁纸Dialog");
        boolean isZn = true;
        String content = "当前未使用默认主题，切换壁纸时将为您同步切换到默认主题。";
        String title = "继续切换壁纸？";
        String remind = "不再提醒";
        String button1Text = "继续";
        String button2Text = "取消";
        if (!isZn) {
            content = "Default theme is not \"Active\", and it will be applied at the same time if switch the wallpaper.";
            title = "Continue Switching Wallpaper？";
            remind = "Do not show again";
            button1Text = "Continue";
            button2Text = "Cancel";
        }
        String[] arr = {content, title, remind, button1Text, button2Text};
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newShowDialog(arr[0]
                        , arr[1]
                        , arr[2]
                        , arr[3]
                        , arr[4]);
            }
        });

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

    private void newShowDialog(String content, String title, String remind, String button1, String button2) {
        ICICustomDialog.Builder builder = new ICICustomDialog.Builder(this);
        if (content != null && !"".equals(content)) {
            builder.setContent(content);
        }
        if (title != null && !"".equals(title)) {
            builder.setTitle(title);
        }
        if (remind != null && !"".equals(remind)) {
            builder.setRemind(remind);
        }

        builder.setCheckBox(new ICICustomDialog.OnDialogButtonClickListener() {
            @Override
            public void onButtonClick(Dialog dialog, int which) {
                String msg = "";
                if (which == 3) {
                    msg = "已选中";
                } else if (which == 4) {
                    msg = "未选中";
                }
                Log.d("DialogActivity", "onButtonClick: 复选框 " + msg);
            }
        });
        if (button1 != null && !"".equals(button1)) {
            builder.setFirstButton(button1, new ICICustomDialog.OnDialogButtonClickListener() {
                @Override
                public void onButtonClick(Dialog dialog, int which) {
                    Log.d("DialogActivity", "onButtonClick: 继续");
                    dialog.dismiss();
                }
            }, true);
        }
        if (button2 != null && !"".equals(button2)) {
            builder.setSecondButton(button2, new ICICustomDialog.OnDialogButtonClickListener() {
                @Override
                public void onButtonClick(Dialog dialog, int which) {
                    Log.d("DialogActivity", "onButtonClick: 取消");
                    dialog.dismiss();
                }
            });
        }

        //设置优先级  不设置默认为普通dialog
//        builder.setPrority(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
        builder.build().show();
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
