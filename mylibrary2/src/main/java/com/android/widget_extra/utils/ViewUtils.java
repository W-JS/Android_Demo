package com.android.widget_extra.utils;

import java.util.concurrent.atomic.AtomicInteger;

/*
 *@hide
 */
public class ViewUtils {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * @hide 生成ID
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
