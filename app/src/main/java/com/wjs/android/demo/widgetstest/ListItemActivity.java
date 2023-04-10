
package com.wjs.android.demo.widgetstest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.widget_extra.list.ICI28ListView;
import com.android.widget_extra.list.ICIListSliderView;
import com.android.widget_extra.progress.ICISlider;
import com.android.widget_extra.selection.ICISwitchButton;
import com.wjs.android.demo.R;

public class ListItemActivity extends BaseActivity {

    //v2021-08-24
    // 默认添加底部分割线条 setHasBottomLine(boolean)关闭
    //添加addNotiImageView()  方法 可在Maintext后添加Image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ICI28ListView ici28ListView = findViewById(R.id.ici_list_listview);
        //所有add 方法会返回对应控件，可自定义一些属性
        TextView mainText = ici28ListView.addMainTextView();
        mainText.setText("Single Normal");

        ICI28ListView ici28ListView2 = findViewById(R.id.ici_list_listview2);
        ici28ListView2.addMainTextView().setText("Single Long");

        ICI28ListView ici28ListView3 = findViewById(R.id.ici_list_listview3);
        ici28ListView3.addMainTextView().setText("Single Short Checked");
        ici28ListView3.setChecked(true);
//        ici28ListView3.setChecked(false);

        ICI28ListView ici28ListView4 = findViewById(R.id.ici_list_listview4);
        ici28ListView4.addMainTextView().setText("Single Short Disable");
        ici28ListView4.setEnabled(false);

        ICI28ListView ici28ListView5 = findViewById(R.id.ici_list_listview5);
        ici28ListView5.addMainTextView().setText("Single Short With switch");
        ICISwitchButton iciSwitchButton5 = ici28ListView5.addRightSwitchButton();

        ICI28ListView ici28ListView6 = findViewById(R.id.ici_list_listview6);
        ici28ListView6.addMainTextView().setText("Single Short With RadioButton");
        ici28ListView6.addRightRadioButton();

        ICI28ListView ici28ListView7 = findViewById(R.id.ici_list_listview7);
        ici28ListView7.addMainTextView().setText("Single Short With Next");
        ici28ListView7.addRightNextImageView();
        ici28ListView7.add2ndTextView().setText("I am 2nd Line");
        ici28ListView7.add3rdTextView().setText("I am 3rd Line");

        ICI28ListView ici28ListView8 = findViewById(R.id.ici_list_listview8);
        ici28ListView8.addMainTextView().setText("Single Short With Image");
        ici28ListView8.addRightImageView(R.drawable.ici28c_icon_60_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListItemActivity.this, "点击了edit", Toast.LENGTH_SHORT).show();
            }
        });

        ici28ListView8.addRightImageView(R.drawable.ici28c_icon_60_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListItemActivity.this, "点击了setting", Toast.LENGTH_SHORT).show();
            }
        });

        ICI28ListView ici28ListView9 = findViewById(R.id.ici_list_listview9);
        ici28ListView9.addMainTextView().setText("Single Short Witch Action");
        ici28ListView9
                .addActionImageView(R.drawable.ici28c_icon_60_edit, getResources()
                        .getColor(R.color.ici_list_action2));
        ici28ListView9.addActionImageView(R.drawable.ici28c_icon_60_delete, getResources().getColor(R.color.ici_list_action1));

        ICI28ListView ici28ListView10 = findViewById(R.id.ici_list_listview10);
        ici28ListView10.addMainTextView().setText("Single Short With Left");
        ici28ListView10.addLeftImageView(R.drawable.ici28c_icon_60_contacts);

        ICI28ListView ici28ListView11 = findViewById(R.id.ici_list_listview11);
        ici28ListView11.addMainTextView().setText("Single Short With Checkbox");
        ici28ListView11.addRightCheckBox();

        final ICIListSliderView listSliderView = findViewById(R.id.ici_list_listview12);
        listSliderView.getIciSlider().setAllItem(0, 100);

        listSliderView.getIciSlider().setOnSliderProgressChangeListner(new ICISlider.OnSliderProgressChangeListner() {
            @Override
            public void onSliderProgressChange(int newIndex) {
                listSliderView.getRightTextView().setText(newIndex + "");
            }
        });
        listSliderView.getLeftTextView().setText("正文文字");
        listSliderView.getIciSlider().setIndex(40);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_listitem;
    }
}
