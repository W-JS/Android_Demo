package com.android.widget_extra.utils;

import android.view.View;

public class WidgetCoverityUtils {

    public static boolean isNull(Object... objects) {
        if (objects == null) {
            return true;
        }
        for (Object obj : objects) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static View findViewById(View parent, int id) {
        View v = parent.findViewById(id);
        if (v == null) {
            return parent;
        } else {
            return v;
        }
    }


}
