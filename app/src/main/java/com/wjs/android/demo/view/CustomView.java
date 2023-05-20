package com.wjs.android.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.wjs.android.demo.R;

/**
 * @Description 自定义View的实现
 * @Date 2023/5/17 10:06:20
 * @Author jinshengw
 * 参考: https://blog.csdn.net/linmiansheng/article/details/17766157
 */
public class CustomView extends View {

    private final static String TAG = CustomView.class.getSimpleName();

    /**
     * 要绘制的位图
     */
    private Bitmap mBitmap;
    /**
     * 背景颜色
     */
    private int mBgColor;
    /**
     * 旋转的角度
     */
    private float mDegree;
    /**
     * 用于转换坐标的3x3矩阵。
     */
    private Matrix mMatrix;
    /**
     * 自定义View的 宽 和 高
     */
    private int mWidth = 240, mHeight = 240;
    /**
     * 旋转的中心点
     */
    private int mPivotX, mPivotY;
    /**
     * 图片的偏移量
     */
    private int mTranslateX, mTranslateY;

    public CustomView(Context context) {
        this(context, null);
    }

    /**
     * 从 xml 中解析的 View, 必须要有这个构造函数，包含有 AttributeSet 参数
     *
     * @param context
     * @param attrs
     */
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "CustomView: ");
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        Log.d(TAG, "init: ");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        Drawable drawable = typedArray.getDrawable(R.styleable.CustomView_drawable);
        mDegree = typedArray.getFloat(R.styleable.CustomView_degree, 0);
        mBgColor = typedArray.getColor(R.styleable.CustomView_bgcolor, Color.YELLOW);
        typedArray.recycle();

        // 将其中的drawable 转化为bitmap, 将其缩小到其对角线比当前View的宽跟高都要小
        mBitmap = zoomBitmap(drawableToBitmap(drawable), mWidth, mHeight);

        // 旋转轴，位图的中心点
        mPivotX = mBitmap.getWidth() / 2;
        mPivotY = mBitmap.getHeight() / 2;

        // 平移，将位图平移到此视图的中心
        mTranslateX = mWidth / 2 - mPivotX;
        mTranslateY = mHeight / 2 - mPivotY;

        mMatrix = new Matrix();
    }

    /**
     * 测量当前视图，将其设置为固定的宽度和高度。如果它的大小由其父级决定，则可以使用 widthMeasureSpec 和 heightMeasureSpec
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure: widthMeasureSpec: " + widthMeasureSpec + ",heightMeasureSpec: " + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: setMeasuredDimension: mWidth: " + mWidth + ",mHeight: " + mHeight);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout: changed: " + changed + ",left: " + left + ",top: " + top + ",right: " + right + ",bottom: " + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 绘制当前视图
     */
    @Override
    public void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        super.onDraw(canvas);
        mMatrix.reset();
        // step1: 旋转位图
        mMatrix.postRotate(mDegree, mPivotX, mPivotY);
        // step2: 将位图移动到中心
        mMatrix.postTranslate(mTranslateX, mTranslateY);
        // step3: 通过 mBgColor 绘制 View 背景颜色
        canvas.drawColor(mBgColor);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }

    /**
     * 将 Drawable 转换为 Bitmap
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        Log.d(TAG, "drawableToBitmap: ");
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 缩放位图以确保它始终显示在视图中
     */
    private Bitmap zoomBitmap(Bitmap oldBitmap, int reqWidth, int reqHeight) {
        Log.d(TAG, "zoomBitmap: ");
        Matrix pMatrix = new Matrix();
        int oldWidth = oldBitmap.getWidth();
        int oldHeight = oldBitmap.getHeight();

        // 根据 勾股定理 求出 斜边
        double hypotenuse = Math.sqrt(oldWidth * oldWidth + oldHeight * oldHeight);
        float scaleX = (float) (reqWidth / hypotenuse);
        float scaleY = (float) (reqHeight / hypotenuse);

        float scale = Math.min(scaleX, scaleY);
        pMatrix.postScale(scale, scale);
        return Bitmap.createBitmap(oldBitmap, 0, 0, oldWidth, oldHeight, pMatrix, true);
    }

}
