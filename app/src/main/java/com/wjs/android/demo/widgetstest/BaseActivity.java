package com.wjs.android.demo.widgetstest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.widget_extra.navigationbar.ICINavigationBar;
import com.wjs.android.demo.R;

public abstract class BaseActivity extends Activity {
    private ICINavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        navigationBar = findViewById(R.id.navi);
        if (navigationBar != null) {
            navigationBar.setTitle(this.getLocalClassName());
            navigationBar.setOnBackclick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected abstract int getLayoutId();

}
