package com.wjs.android.aidl_client;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wjs.android.aidl.ICallbackListener;
import com.wjs.android.aidl.MessageProxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        mContext = this;
        init();
    }

    public void init() {
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