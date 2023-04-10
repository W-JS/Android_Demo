package com.wjs.android.aidl_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

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

    MessageManager.Stub mBind = new MessageManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public void sendMsg(String msg) throws RemoteException {
            Log.d(TAG, "sendMsg: 客户端已发送的消息: " + msg);
            mMsg = msg;
            count++;
        }

        @Override
        public String getMsg() throws RemoteException {
            String msg = mMsg + ",count:" + count;
            Log.d(TAG, "getMsg: 客户端待接收的消息: " + msg);
            return msg;
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: " + mBind);
        return mBind;
    }
}