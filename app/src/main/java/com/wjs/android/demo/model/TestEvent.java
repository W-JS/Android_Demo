package com.wjs.android.demo.model;

/**
 * @Description
 * @Date 2023/4/24 11:50:19
 * @Author jinshengw
 */
public class TestEvent {
    private int mMsg;

    public TestEvent(int msg) {
        mMsg = msg;
    }

    public int getMsg() {
        return mMsg;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "mMsg=" + mMsg +
                '}';
    }
}
