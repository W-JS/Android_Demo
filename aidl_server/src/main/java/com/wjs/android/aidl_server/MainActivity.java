package com.wjs.android.aidl_server;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.wjs.android.aidl.ICallbackListener;
import com.wjs.android.aidl.MessageProxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private Context mContext;
    private EditText et_msg;
    private Button btn_send;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        et_msg = (EditText) findViewById(R.id.et_msg);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        registerCallbackListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        unregisterCallbackListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String mag = et_msg.getText().toString().trim() + (++count);
                MessageProxy.getInstance(mContext).setCallbackChanged(mCallbackListener, "aidl_server", "btn_send", mag);
                break;
            default:
        }
    }

    private ICallbackListener.Stub mCallbackListener = new ICallbackListener.Stub() {
        @Override
        public void onCallbackChanged(String s) throws RemoteException {
            Log.d(TAG, "onCallbackChanged: " + s);
        }
    };

    private void registerCallbackListener() {
        Log.d(TAG, "registerCallbackListener: ");
        MessageProxy.getInstance(mContext).registerCallbackListener(mCallbackListener);
    }

    private void unregisterCallbackListener() {
        Log.d(TAG, "unregisterCallbackListener: ");
        MessageProxy.getInstance(mContext).unregisterCallbackListener(mCallbackListener);
    }
}