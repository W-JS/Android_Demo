package com.android.widget_extra.utils;

import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

public class FontUtils {
    private static Typeface defaultFont;
    public static final String TYPE_GLOBAL = "GMSUIChevyCCSTGlobal.ttf";
    public static final String TYPE_THIN = "GMSUIChevyCCSTGlobal-Thin.ttf";
    public static final String TYPE_LIGHT = "GMSUIChevyCCSTGlobal-Light.ttf";

    public static final String TYPE_BUCK_THIN = "GMSUIBuickGlobal-Thin.ttf";
    public static final String TYPE_BUCK_LIGHT = "GMSUIBuickGlobal-Light.ttf";

    public static Map<String, Typeface> typefaceMap = new HashMap<>();

    public static Typeface getDefaultFont() {
//        if (defaultFont == null) {
//            File file = new File("/system/fonts/");
//            if (file.exists()) {
//                defaultFont = Typeface.createFromFile("/system/fonts/GMSUIChevyCCSTGlobal-Light.ttf");
//            } else {
//                defaultFont = Typeface.DEFAULT;
//            }
//        }
//        return defaultFont;
        return Typeface.DEFAULT;
    }

    public static Typeface getTypeFace(String fontName) {
//        Typeface typeface = typefaceMap.get(fontName);
//        if (typeface == null) {
//            typeface = Typeface.createFromFile("/system/fonts/" + fontName);
//            typefaceMap.put(fontName, typeface);
//        }
//        return typeface;
        return Typeface.DEFAULT;
    }
}
