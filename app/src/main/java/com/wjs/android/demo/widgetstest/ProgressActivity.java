package com.wjs.android.demo.widgetstest;

import android.os.Bundle;

import com.android.widget_extra.progress.ICI28Progress;
import com.android.widget_extra.progress.ICIProgressCircular;
import com.android.widget_extra.progress.ICISlider;
import com.wjs.android.demo.R;

public class ProgressActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ICIProgressCircular iciProgressCircular = findViewById(R.id.progress_circular);
        iciProgressCircular.start();

        ICIProgressCircular iciProgressCircular2 = findViewById(R.id.progress_circular2);
        iciProgressCircular2.start();

        ICIProgressCircular iciProgressCircular3 = findViewById(R.id.progress_circular3);
        iciProgressCircular3.start();

        ICI28Progress ici28Progress = findViewById(R.id.ici_progress_line);
        ici28Progress.setPrecent(80);

        ICISlider iciSlider = findViewById(R.id.ici_silider);
        //左边进度条  右边进度  一般左边为0特殊需要向左滑动
        iciSlider.setAllItem(-1000, 1000);
        iciSlider.setIndex(100);
        //宽度8px
        iciSlider.setLineHeight(8);
        //这里可以修改进度条图片
        iciSlider.getLineImageView();
        iciSlider.setOnSliderProgressChangeListner(new ICISlider.OnSliderProgressChangeListner() {
            @Override
            public void onSliderProgressChange(int i) {
            }
        });
        iciSlider.setOnSliderImageLayoutChange(new ICISlider.OnSliderImageLayoutChange() {
            @Override
            public void onLayoutChange(int i) {
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_progress;
    }
}
