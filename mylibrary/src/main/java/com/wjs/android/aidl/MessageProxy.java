package com.wjs.android.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @Description
 * @Date 2023/4/12 11:32:22
 * @Author jinshengw
 */
public class MessageProxy {

    private final static String TAG = MessageProxy.class.getSimpleName();

    private static Context mContext;
    private static MessageProxy instance;
    private MessageManager mMessageManager;
    /**
     * 轮询时间间隔 1 秒轮询一次
     */
    private final int mPollingTimeInterval = 1_000;

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    private MessageProxy() {
    }

    public static MessageProxy getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            synchronized (MessageManager.class) {
                if (instance == null) {
                    instance = new MessageProxy();
                }
            }
        }
        if (instance.mMessageManager == null) {
            instance.attemptToBindService();
        }
        return instance;
    }

    /**
     * 注册回调变化监听
     *
     * @param callbackListener
     */
    public void registerCallbackListener(final ICallbackListener.Stub callbackListener) {
        boolean isEmpty = mMessageManager == null;
        if (isEmpty) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: 轮询 registerCallbackListener: " + callbackListener);
                    registerCallbackListener(callbackListener);
                }
            }, mPollingTimeInterval);
        }

        if (!isEmpty) {
            try {
                Log.d(TAG, "registerCallbackListener: " + callbackListener);
                mMessageManager.registerCallbackListener(callbackListener);
            } catch (Exception e) {
                Log.e(TAG, "registerCallbackListener: ", e);
            }
        }
    }

    /**
     * 注销回调变化监听
     *
     * @param callbackListener
     */
    public void unregisterCallbackListener(final ICallbackListener.Stub callbackListener) {
        boolean isEmpty = mMessageManager == null;
        if (isEmpty) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: 轮询 unregisterCallbackListener: " + callbackListener);
                    unregisterCallbackListener(callbackListener);
                }
            }, mPollingTimeInterval);
        }

        if (!isEmpty) {
            try {
                Log.d(TAG, "unregisterCallbackListener: " + callbackListener);
                mMessageManager.unregisterCallbackListener(callbackListener);
            } catch (Exception e) {
                Log.e(TAG, "unregisterCallbackListener: ", e);
            }
        }
    }

    /**
     * 设置回调变化
     *
     * @param name 功能名：theme or wallpaper
     * @param val  id值
     * @param path 文件夹路径
     */
    public void setCallbackChanged(final ICallbackListener.Stub callbackListener, String name, String val, String path) {
        boolean isEmpty = mMessageManager == null;
        if (isEmpty) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: 轮询 setCallbackChanged: " + callbackListener);
                    setCallbackChanged(callbackListener, name, val, path);
                }
            }, mPollingTimeInterval);
        }

        if (!isEmpty) {
            try {
                Log.d(TAG, "setCallbackChanged: name: " + name + ",val: " + val + ",path: " + path);
                mMessageManager.setCallbackChanged(name, val, path);
            } catch (Exception e) {
                Log.e(TAG, "setCallbackChanged: ", e);
            }
        }

    }

    /**
     * 尝试绑定服务
     */
    private void attemptToBindService() {
        if (mContext != null) {
            String actionName = "com.xxx.aidl";
            String packageName = "com.wjs.android.aidl_server";
            Log.d(TAG, "attemptToBindService: actionName: " + actionName + ",packageName: " + packageName);
            Intent intent = new Intent();
            intent.setAction(actionName);
            intent.setPackage(packageName);
            try {
                mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            } catch (Exception e) {
                Log.e(TAG, "attemptToBindService: ", e);
            }
        } else {
            Log.d(TAG, "attemptToBindService: mContext == null");
        }
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
}
