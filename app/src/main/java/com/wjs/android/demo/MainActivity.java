package com.wjs.android.demo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import com.wjs.android.demo.widgetstest.WidgetsTestActivity;
import com.wjs.android.mylibrary.utils.DateTimeUtils;
import com.wjs.android.mylibrary2.utils.DateUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private Button mTestRealBtn;
    private Button mWidgetsTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestRealBtn = findViewById(R.id.btn_real_test);
        mTestRealBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mTestRealBtn.setOnClickListener(this);

        mWidgetsTestBtn = findViewById(R.id.btn_widgetstest);
        mWidgetsTestBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mWidgetsTestBtn.setOnClickListener(this);

        String todayDateTime = DateTimeUtils.getTodayDateTime();
        Toast.makeText(MainActivity.this, "调用jar包方法测试时间：" + todayDateTime, Toast.LENGTH_SHORT).show();

        String todayDateTime1 = DateUtils.getTodayDateTime();
        Log.d(TAG, "onCreate: 调用aar包方法测试时间：" + todayDateTime1);
        Log.d(TAG, "releaseTime:" + BuildConfig.RELEASE_TIME);
        Log.d(TAG, "getGitCommitInfo:" + BuildConfig.GIT_COMMITINFO);
        Log.d(TAG, "getGitCommitInfo:" + PropertiesUtils.getGitCommitInfo());
        getPackageNameAppName();
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
            case R.id.btn_widgetstest:
                Log.d(TAG, "onClick: 跳转");
                Intent intent = new Intent(this, WidgetsTestActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    private void getPackageNameAppName() {
        // 1、adb root
        // 2、adb shell
        // 3.1、pm list packages //查看所有包名
        // 3.2、pm list packages -f //查看所有apk绝对路径以及包名
        // 3.3、pm list packages -f com.baidu //查看包含com.baidu的apk绝对路径以及包名
        PackageManager pm = getPackageManager();
        List<PackageInfo> list = pm.getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);
        if (list != null) {
            Log.d(TAG, "getPackageNameAppName: 应用的总个数:" + list.size());
            for (PackageInfo packageInfo : list) {
                // AndroidManifest 中的 package
                String packageName = packageInfo.packageName;
                // AndroidManifest 中的 label
                String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                Log.d(TAG, "getPackageNameAppName: ----------------------------------------------------------------------------------------------------");
                Log.d(TAG, "getPackageNameAppName: packageName: " + packageName);
                Log.d(TAG, "getPackageNameAppName:     appName: " + appName);
            }
        }
    }
}