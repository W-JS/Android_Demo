package com.wjs.android.demo.widgetstest;

import android.os.Bundle;

import com.android.widget_extra.tab.ICI28TabView;
import com.wjs.android.demo.R;

import java.util.Arrays;

public class TabViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //三个选项
        ICI28TabView iciTabView = findViewById(R.id.ici28_tabview);
        iciTabView.setItems(Arrays.asList(new String[]{"选项一", "选项二", "选项三"}));
        iciTabView.setOnTabClickListner(new ICI28TabView.OnTabClickListner() {
            @Override
            public void onTabClick(int i) {
            }
        });

        //四个选项
        ICI28TabView iciTabView2 = findViewById(R.id.ici28_tabview2);
        iciTabView2.setItems(Arrays.asList(new String[]{"选项一", "选项二", "选项三", "选项四"}));

        ICI28TabView iciTabView3 = findViewById(R.id.ici28_tabview3);
        iciTabView3.setItems(Arrays.asList(new String[]{"选项一", "选项二", "选项三", "选项四", "选项五"}));

        ICI28TabView iciTabView4 = findViewById(R.id.ici28_tabview4);
        iciTabView4.setItems(Arrays.asList(new String[]{"我有两行 我有两行", "选项二", "选项三", "选项四", "选项五", "选项六"}));

        ICI28TabView iciTabView5 = findViewById(R.id.ici28_tabview5);
        iciTabView5.setItems(Arrays.asList(new String[]{"选项一", "选项二"}));
        iciTabView5.setHasBack(true);

        ICI28TabView iciTabView6 = findViewById(R.id.ici28_tabview6);
        iciTabView6.setItems(Arrays.asList(new String[]{"选项一", "选项二", "选项三"}));
        iciTabView6.setHasBack(true);
        iciTabView6.setHasRightButton(true);

        ICI28TabView iciTabView7 = findViewById(R.id.ici28_tabview7);
        iciTabView7.setItems(Arrays.asList(new String[]{"选项一", "选项二", "选项三", "选项四"}));
        iciTabView7.setHasBack(true);
        iciTabView7.setHasRightButton(true);

        ICI28TabView iciTabView8 = findViewById(R.id.ici28_tabview8);
        iciTabView8.setItems(Arrays.asList(new String[]{"选项一", "选项二", "选项三", "选项四", "选项五"}));
        iciTabView8.setHasBack(true);
        iciTabView8.setHasRightButton(true);

        ICI28TabView iciTabView9 = findViewById(R.id.ici28_tabview9);
        iciTabView9.setItems(Arrays.asList(new String[]{"选项一", "选项二"}));
        iciTabView9.setHasBack(true);
        iciTabView9.setHasRightButton(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tabview;
    }
}
