package com.wjs.android.demo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.widget_extra.button.ICIRealButton;
import com.wjs.android.demo.model.ScreenInfo;
import com.wjs.android.demo.model.TestEvent;
import com.wjs.android.demo.utils.DialogUtils;
import com.wjs.android.demo.utils.PropertiesUtils;
import com.wjs.android.demo.utils.ToastUtils;
import com.wjs.android.demo.widgetstest.WidgetsTestActivity;
import com.wjs.android.mylibrary.utils.DateTimeUtils;
import com.wjs.android.mylibrary2.utils.DateUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private Button mTestDialogBtn;
    private Button mWidgetsTestBtn;
    private Button mSaveLogBtn;
    private Button mEventBusBtn;
    private Button mCustomViewBtn;

    public int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestDialogBtn = findViewById(R.id.btn_dialog_test);
        mTestDialogBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mTestDialogBtn.setOnClickListener(this);

        mWidgetsTestBtn = findViewById(R.id.btn_widgetstest);
        mWidgetsTestBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mWidgetsTestBtn.setOnClickListener(this);

        mSaveLogBtn = findViewById(R.id.btn_save_log);
        mSaveLogBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mSaveLogBtn.setOnClickListener(this);

        mEventBusBtn = findViewById(R.id.btn_event_bus);
        mEventBusBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mEventBusBtn.setOnClickListener(this);

        mCustomViewBtn = findViewById(R.id.btn_custom_view);
        mCustomViewBtn.setBackgroundColor(ICIRealButton.COLOR_GREEN);
        mCustomViewBtn.setOnClickListener(this);

        String todayDateTime = DateTimeUtils.getTodayDateTime();
        Log.d(TAG, "onCreate: 调用jar包方法测试时间：" + todayDateTime);
        String todayDateTime1 = DateUtils.getTodayDateTime();
        Log.d(TAG, "onCreate: 调用aar包方法测试时间：" + todayDateTime1);
        Log.d(TAG, "onCreate: releaseTime:" + BuildConfig.RELEASE_TIME);
        Log.d(TAG, "onCreate: getGitCommitInfo:" + BuildConfig.GIT_COMMITINFO);
        Log.d(TAG, "onCreate: getGitCommitInfo:" + PropertiesUtils.getGitCommitInfo());
        getPackageNameAppName();

        Log.d(TAG, "onCreate: ScreenInfo: " + ScreenInfo.getScreenInfo(this));
        // 模拟器 ScreenInfo: ScreenInfo{size=8.54400374531753, sizeStr='8.54', heightPixels=720, widthPixels=1920, screenRealMetrics='1920 X 720', density=1.5, densityDpi=240, densityDpiStr='240 dpi', scaledDensity=1.5, xdpi=240.0, ydpi=240.0, density_default=160}
        // 真车机 ScreenInfo: ScreenInfo{size=12.816005617976296, sizeStr='12.82', heightPixels=720, widthPixels=1920, screenRealMetrics='1920 X 720', density=1.0, densityDpi=160, densityDpiStr='160 dpi', scaledDensity=1.0, xdpi=320.842, ydpi=320.842, density_default=160}

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //创建文件夹
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        // TODO 创建文件夹和文件
                    }
                    break;
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_test:
                Log.d(TAG, "onClick: 测试");
                DialogUtils.getInstance(this).showTestDialog();
                break;
            case R.id.btn_widgetstest:
                Log.d(TAG, "onClick: 跳转");
                Intent intent = new Intent(this, WidgetsTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_save_log:
                Log.d(TAG, "onClick: 保存日志");
                saveLogcat();
                break;
            case R.id.btn_event_bus:
                Log.d(TAG, "onClick: Event Bus");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        do {
                            TestEvent testEvent = new TestEvent(time);
                            Log.d(TAG, "run: testEvent: " + testEvent);
                            EventBus.getDefault().post(testEvent);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            time += 10;
                        } while (time <= 100);
                        time = 0;
                    }
                }).start();
                break;
            case R.id.btn_custom_view:
                Log.d(TAG, "onClick: Custom View");
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
                break;
            default:
                Log.d(TAG, "onClick: Toast");
                ToastUtils.showTestToast(this);
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

    private void saveLogcat() {
        // https://www.jianshu.com/p/9e7961221862
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "saveLogcat: start");
                InputStream is = null;
                FileOutputStream fos = null;
//                String[] command = new String[]{"logcat", "-b", "all", "*:V"};
                String[] command = new String[]{"logcat"};
                try {
                    Process exec = Runtime.getRuntime().exec(command);
                    is = exec.getInputStream();
//                    String rootPath = "/sdcard";
                    String rootPath = Environment.getExternalStorageDirectory().getPath();
                    String path = rootPath + "/Logs/Logcat/";
                    File dir = new File(path);
                    if (!dir.exists()) {
                        Log.d(TAG, "run: mkdirs: " + dir.mkdirs() + "," + dir.getAbsolutePath());
                    }
                    path = path + "logcat.log";
                    File file = new File(path);
                    if (!file.exists()) {
                        Log.d(TAG, "run: createNewFile: " + file.createNewFile() + "," + file.getAbsolutePath());
                    }
                    //新建一个路径信息
                    fos = new FileOutputStream(path);
                    Log.d(TAG, "run: logPath: " + path);
                    int len = 0;
                    byte[] buf = new byte[1024];
                    while (-1 != (len = is.read(buf))) {
                        fos.write(buf, 0, len);
                        fos.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != fos) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            Log.e(TAG, "run: ", e);
                        }
                    }
                    if (null != is) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            Log.e(TAG, "run: ", e);
                        }
                    }
                }
                Log.d(TAG, "saveLogcat: end");
            }
        }).start();
    }
}