package com.wjs.android.aidl_server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.wjs.android.aidl.ICallbackListener;
import com.wjs.android.aidl.MessageManager;

/**
 * @Description https://blog.csdn.net/m0_37602827/article/details/107948002
 * @Date 2023/4/10 13:27:55
 * @Author jinshengw
 */
public class MyService extends Service {

    private final static String TAG = MyService.class.getSimpleName();

    private String mMsg;
    private int count;
    RemoteCallbackList<ICallbackListener> mICallbackListenerRemoteCallbackList;

    MessageManager.Stub mBind = new MessageManager.Stub() {
        @Override
        public void registerCallbackListener(ICallbackListener iCallbackListener) throws RemoteException {
            Log.d(TAG, "registerCallbackListener: " + iCallbackListener);
            mICallbackListenerRemoteCallbackList.register(iCallbackListener);
        }

        @Override
        public void unregisterCallbackListener(ICallbackListener iCallbackListener) throws RemoteException {
            Log.d(TAG, "unregisterCallbackListener: " + iCallbackListener);
            mICallbackListenerRemoteCallbackList.unregister(iCallbackListener);
        }

        @Override
        public void setCallbackChanged(String s, String s1, String s2) throws RemoteException {
            Log.d(TAG, "setCurrent: name: " + s + ",val: " + s1 + ",path: " + s2);
            onCallbackChanged(s2);
        }
    };

    /**
     * 当前主题变化监听，通知其它已注册该监听的应用改变状态
     *
     * @param path
     */
    private void onCallbackChanged(String path) {
        if (path == null || "".equals(path)) {
            Log.d(TAG, "onCallbackChanged: path error");
            return;
        }
        if (mICallbackListenerRemoteCallbackList != null) {
            synchronized (mICallbackListenerRemoteCallbackList) {
                int count = mICallbackListenerRemoteCallbackList.beginBroadcast();
                Log.d(TAG, "onCallbackChanged: count:" + count + ",path: " + path);
                for (int i = 0; i < count; i++) {
                    ICallbackListener iCallbackListener = mICallbackListenerRemoteCallbackList.getBroadcastItem(i);
                    if (iCallbackListener != null) {
                        IBinder iBinder = iCallbackListener.asBinder();
                        if (iBinder != null) {
                            boolean binderAlive = iBinder.isBinderAlive();
                            boolean pingBinder = iBinder.pingBinder();
                            if (binderAlive && pingBinder) {
                                try {
                                    Log.d(TAG, "onCallbackChanged: iCallbackListener: " + iCallbackListener);
                                    iCallbackListener.onCallbackChanged(path);
                                } catch (Exception e) {
                                    Log.e(TAG, "onCallbackChanged: ", e);
                                }
                            } else {
                                Log.d(TAG, "onCallbackChanged: iCallbackListener: " + iCallbackListener + ",binderAlive: " + binderAlive + ",pingBinder: " + pingBinder);
                            }
                        }
                    }
                }
                mICallbackListenerRemoteCallbackList.finishBroadcast();
            }
        } else {
            Log.d(TAG, "onCallbackChanged: mICallbackListenerRemoteCallbackList == null");
        }
    }

    public MyService() {
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.d(TAG, "attachBaseContext: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        mICallbackListenerRemoteCallbackList = new RemoteCallbackList<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: intent: " + intent + ",mBind: " + mBind);
        return mBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: intent: " + intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}