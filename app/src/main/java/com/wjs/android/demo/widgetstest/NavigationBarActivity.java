package com.wjs.android.demo.widgetstest;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.widget_extra.navigationbar.ICINavigationBar;
import com.wjs.android.demo.R;

public class NavigationBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ICINavigationBar iciNavigationBar = findViewById(R.id.navagation1);
        iciNavigationBar.setTitle("Navaigation bar TextTitle");

        ICINavigationBar iciNavigationBar2 = findViewById(R.id.navagation2);
        iciNavigationBar2.setTitle("Navaigation bar with action");
        iciNavigationBar2.addImageButton(R.drawable.ici28c_icon_60_search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationBarActivity.this, "点击了search", Toast.LENGTH_SHORT).show();
            }
        });
        iciNavigationBar2.addImageButton(R.drawable.ici28c_icon_60_setting, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationBarActivity.this, "点击了setting", Toast.LENGTH_SHORT).show();
            }
        });

        //with  back
        ICINavigationBar iciNavigationBar3 = findViewById(R.id.navagation3);
        iciNavigationBar3.setTitle("Navaigation bar with  back");
        iciNavigationBar3.setOnBackclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationBarActivity.this, "点击了返回", Toast.LENGTH_SHORT).show();
            }
        });

        //with  back and action
        ICINavigationBar iciNavigationBar4 = findViewById(R.id.navagation4);
        iciNavigationBar4.setTitle("Navaigation bar with  back and action");
        iciNavigationBar4.setOnBackclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationBarActivity.this, "点击了返回", Toast.LENGTH_SHORT).show();

            }
        });

        iciNavigationBar4.addImageButton(R.drawable.ici28c_icon_60_search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationBarActivity.this, "点击了search", Toast.LENGTH_SHORT).show();

            }
        });

        iciNavigationBar4.addImageButton(R.drawable.ici28c_icon_60_setting, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationBarActivity.this, "点击了setting", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navigationbar;
    }
}
