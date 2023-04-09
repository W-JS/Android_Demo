package com.wjs.android.demo.model;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * https://blog.csdn.net/su749520/article/details/79179668
 * https://github.com/sufadi/AndroidCpuTools
 */
public class ScreenInfo {

    // 英寸
    public double size;

    public String sizeStr;

    // 高
    public int heightPixels;

    // 宽
    public int widthPixels;

    public String screenRealMetrics;

    // 显示器的逻辑密度
    public float density;

    // 屏幕密度为点每英寸
    public int densityDpi;

    public String densityDpiStr;

    // 显示在显示器的字体的定标因子
    public float scaledDensity;

    // 每在 X 维屏幕英寸的确切物理像素
    public float xdpi;

    // 每在 Y 维屏幕英寸的确切物理像素
    public float ydpi;

    //在屏幕中显示的参考密度
    public int density_default;

    @Override
    public String toString() {
        return "ScreenInfo{" +
                "size=" + size +
                ", sizeStr='" + sizeStr + '\'' +
                ", heightPixels=" + heightPixels +
                ", widthPixels=" + widthPixels +
                ", screenRealMetrics='" + screenRealMetrics + '\'' +
                ", density=" + density +
                ", densityDpi=" + densityDpi +
                ", densityDpiStr='" + densityDpiStr + '\'' +
                ", scaledDensity=" + scaledDensity +
                ", xdpi=" + xdpi +
                ", ydpi=" + ydpi +
                ", density_default=" + density_default +
                '}';
    }

    /**
     * 屏幕分辨率
     *
     * @param mContext
     * @return
     */
    public static ScreenInfo getScreenInfo(Context mContext) {
        ScreenInfo result = new ScreenInfo();
        int widthPixels;
        int heightPixels;

        WindowManager w = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        result.widthPixels = widthPixels;
        result.heightPixels = heightPixels;
        result.screenRealMetrics = widthPixels + " X " + heightPixels;
        result.density = metrics.density;
        result.density_default = metrics.DENSITY_DEFAULT;
        result.densityDpi = metrics.densityDpi;
        result.densityDpiStr = metrics.densityDpi + " dpi";
        result.scaledDensity = metrics.scaledDensity;
        result.xdpi = metrics.xdpi;
        result.ydpi = metrics.ydpi;
        result.size = (Math.sqrt(Math.pow(widthPixels, 2) + Math.pow(heightPixels, 2)) / metrics.densityDpi);
        result.sizeStr = String.format("%.2f", result.size);

        return result;
    }
}