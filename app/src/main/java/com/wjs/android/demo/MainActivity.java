package com.wjs.android.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.widget_extra.button.ICIRealButton;
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
                ToastUtils.showTestToast(this);
                DialogUtils.getInstance(this).showTestDialog("这是一个测试Dialog");
                break;
            default:
        }
    }
}