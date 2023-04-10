package com.wjs.android.aidl_client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wjs.android.aidl.MessageManager;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private MessageManager mMessageManager;
    private EditText et_msg;
    private Button btn_send;
    private TextView tv_msg;
    private Button btn_recv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        et_msg = (EditText) findViewById(R.id.et_msg);
        btn_send = (Button) findViewById(R.id.btn_send);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        btn_recv = (Button) findViewById(R.id.btn_recv);
        btn_send.setOnClickListener(mClick);
        btn_recv.setOnClickListener(mClick);
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_send) {
                String msg = et_msg.getText().toString();
                sendMsg(msg);
            } else if (v.getId() == R.id.btn_recv) {
                String msg = getMsg();
                tv_msg.setText(msg);
            }
        }
    };

    private void sendMsg(String msg) {
        if (mMessageManager == null) {
            attemptToBindService();
        }
        try {
            Log.d(TAG, "sendMsg: " + msg);
            mMessageManager.sendMsg(msg);
        } catch (RemoteException e) {
            Log.e(TAG, "sendMsg: ", e);
        }
    }

    private String getMsg() {
        if (mMessageManager == null) {
            attemptToBindService();
        }
        try {
            String msg = mMessageManager.getMsg();
            Log.d(TAG, "getMsg: " + msg);
            return msg;
        } catch (RemoteException e) {
            Log.e(TAG, "getMsg: ", e);
        }
        return "";
    }

    private void attemptToBindService() {
        Log.d(TAG, "attemptToBindService: ");
        Intent intent = new Intent();
        intent.setAction("com.xxx.aidl");
        intent.setPackage("com.wjs.android.aidl_server");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: name: " + name + ",service: " + service);
            mMessageManager = MessageManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: name: " + name);
            mMessageManager = null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        if (mMessageManager == null) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        if (mMessageManager != null) {
            unbindService(mServiceConnection);
        }
    }
}