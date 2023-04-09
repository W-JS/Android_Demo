package com.android.widget_extra.utils;

public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true表示字符串为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
