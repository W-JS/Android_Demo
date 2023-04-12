// MessageManager.aidl
package com.wjs.android.aidl;

// Declare any non-default types here with import statements

interface ICallbackListener {
    oneway void onCallbackChanged(String folderPatch);
}