package com.wjs.android.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.widget_extra.button.ICIRealButton;
import com.wjs.android.demo.model.ScreenInfo;
import com.wjs.android.demo.utils.DialogUtils;
import com.wjs.android.demo.utils.PropertiesUtils;
import com.wjs.android.demo.utils.ToastUtils;
import com.wjs.android.mylibrary.utils.DateTimeUtils;
import com.wjs.android.mylibrary2.utils.DateUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private Button mTestRealBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestRealBtn = findViewById(R.id.btn_real_test);
        mTestRealBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mTestRealBtn.setOnClickListener(this);

        String todayDateTime = DateTimeUtils.getTodayDateTime();
        Toast.makeText(MainActivity.this, "调用jar包方法测试时间：" + todayDateTime, Toast.LENGTH_SHORT).show();

        String todayDateTime1 = DateUtils.getTodayDateTime();
        Log.d(TAG, "onCreate: 调用aar包方法测试时间：" + todayDateTime1);
        Log.d(TAG, "releaseTime:" + BuildConfig.RELEASE_TIME);
        Log.d(TAG, "getGitCommitInfo:" + BuildConfig.GIT_COMMITINFO);
        Log.d(TAG, "getGitCommitInfo:" + PropertiesUtils.getGitCommitInfo());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_real_test:
                Log.d(TAG, "onClick: 测试");
                Log.d(TAG, "onClick: ScreenInfo: " + ScreenInfo.getScreenInfo(this));
                // 模拟器 ScreenInfo: ScreenInfo{size=8.54400374531753, sizeStr='8.54', heightPixels=720, widthPixels=1920, screenRealMetrics='1920 X 720', density=1.5, densityDpi=240, densityDpiStr='240 dpi', scaledDensity=1.5, xdpi=240.0, ydpi=240.0, density_default=160}
                // 真车机 ScreenInfo: ScreenInfo{size=12.816005617976296, sizeStr='12.82', heightPixels=720, widthPixels=1920, screenRealMetrics='1920 X 720', density=1.0, densityDpi=160, densityDpiStr='160 dpi', scaledDensity=1.0, xdpi=320.842, ydpi=320.842, density_default=160}
                ToastUtils.showTestToast(this);
                DialogUtils.getInstance(this).showTestDialog("这是一个测试Dialog");
                break;
            default:
        }
    }
}