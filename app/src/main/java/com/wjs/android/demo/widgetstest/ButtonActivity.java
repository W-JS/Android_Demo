package com.wjs.android.demo.widgetstest;

import android.os.Bundle;

import com.android.widget_extra.button.ICIFlatButton;
import com.android.widget_extra.button.ICIIconButton;
import com.android.widget_extra.button.ICIRealButton;
import com.wjs.android.demo.R;

public class ButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //红色
        ICIRealButton iciRealButton = findViewById(R.id.ici_realbutton);
        iciRealButton.setText("ICIRealButton_RED");
        iciRealButton.setBackgroundColor(ICIRealButton.COLOR_RED);

        //绿色
        ICIRealButton iciRealButton2 = findViewById(R.id.ici_realbutton2);
        iciRealButton2.setText("ICIRealButton_GREEN");
        iciRealButton2.setBackgroundColor(ICIRealButton.COLOR_GREEN);

        //蓝色
        ICIRealButton iciRealButton4 = findViewById(R.id.ici_realbutton4);
        iciRealButton4.setText("ICIRealButton_BLUE");
        iciRealButton4.setBackgroundColor(ICIRealButton.COLOR_BLUE);

        ICIFlatButton flatButton = findViewById(R.id.flatbutton_orange);
        flatButton.setColor(ICIFlatButton.COLOR_ORANGE);
        flatButton.setText("FLAT BUTTON(ORANGE)");

        ICIIconButton iciIconButton = findViewById(R.id.iconbutton);
        iciIconButton.setImageResource(R.drawable.ici_navibar_setting);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_button;
    }

}
