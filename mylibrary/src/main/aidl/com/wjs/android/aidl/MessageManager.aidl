// MessageManager.aidl
package com.wjs.android.aidl;

import com.wjs.android.aidl.ICallbackListener;

// Declare any non-default types here with import statements

interface MessageManager {
    void registerCallbackListener(ICallbackListener listener);
    void unregisterCallbackListener(ICallbackListener listener);
    void setCallbackChanged(String name, String val, String path);
}