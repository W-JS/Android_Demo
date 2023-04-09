package com.android.widget_extra.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Scroller;

import com.wjs.android.mylibrary2.R;

//import com.android.internal.R;

public class ICIProgressLinearIndeterminate extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static int SPEED = 10;
    private SurfaceHolder mSurfaceHolder;
    private static final int ITEM_WIDTH = 27, ITEM_GAP = 10;
    private int width, height;
    private Paint mPaint, mLinearPaint;
    private int offset;
    private Matrix translateMatrix;
    private LinearGradient linearGradient;
    private Rect rect;
    private int rangleGap = 0;
    private Path path = new Path();
    private Scroller scroller;
    private Path nextPath = new Path();

    public ICIProgressLinearIndeterminate(Context context) {
        super(context);
        init();
    }

    public ICIProgressLinearIndeterminate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICIProgressLinearIndeterminate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ICIProgressLinearIndeterminate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void init() {
//        setLayerType(View.LAYER_TYPE_HARDWARE,null);
        mSurfaceHolder = getHolder();
        //注册回调方法
        mSurfaceHolder.addCallback(this);
        scroller = new Scroller(getContext(), new LinearInterpolator());
        mLinearPaint = new Paint();
        mLinearPaint.setAntiAlias(true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.ici_progress_linear_start));
//        linearGradient = new LinearGradient(0,0,ITEM_WIDTH+ITEM_GAP,height,new int[]{getResources().getColor(R.color.ici_progress_linear_start),getResources().getColor(R.color.ici_progress_linear_mid),getResources().getColor(R.color.ici_progress_linear_end)},new float[]{0,0.99f,1}, Shader.TileMode.REPEAT);
//        linearGradient.setLocalMatrix(new Matrix());
//        translateMatrix = new Matrix();
//        translateMatrix.setTranslate(offset,0);
//        linearGradient.setLocalMatrix(translateMatrix);
        mPaint.setShader(linearGradient);
        mLinearPaint.setColor(Color.parseColor("#FF454D60"));

        rect = new Rect();
    }

    protected void drawLine(Canvas canvas) {
        //画背景
        canvas.drawRect(new Rect(0, 0, width, height), mLinearPaint);

        //渐变
//        translateMatrix.setTranslate(offset,0);
//        linearGradient.setLocalMatrix(translateMatrix);
        mPaint.setShader(linearGradient);

        path.reset();
        for (int i = 0; (i * (27 + ITEM_GAP)) < width; i++) {
            if (i == 0) {
                if (offset != 0 && offset <= ITEM_WIDTH + ITEM_GAP) {
                    int start = 0;
                    int end = offset - ITEM_GAP;
                    rect.left = start;
                    rect.top = 0;
                    rect.right = end;
                    rect.bottom = width;
//                    canvas.drawRect(rect,mPaint);
                    path.moveTo(start + rangleGap, 0);
                    path.lineTo(end, 0);
                    path.lineTo(end - rangleGap, width);
                    path.lineTo(start, width);
                    path.close();

//                    canvas.drawPath(path,mPaint);
                }

            }
            int start = offset + ITEM_GAP * i + ITEM_WIDTH * i;
            int end = start + ITEM_WIDTH;
            rect.left = start;
            rect.top = 0;
            rect.right = end;
            rect.bottom = width;
//            canvas.drawRect(rect,mPaint);
//            path.reset();
            path.moveTo(start, 0);
            path.lineTo(end, 0);
            path.lineTo(end, width);
            path.lineTo(start, width);
            path.close();
//            canvas.drawPath(path,mPaint);
        }
        canvas.drawPath(path, mPaint);
        if (offset + SPEED > ITEM_WIDTH + ITEM_GAP) {
            offset = 0;
        } else {
            offset = offset + SPEED;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
    }

    private void drawScroll(Canvas canvas) {
        //画背景
        canvas.drawRect(new Rect(-5 * (ITEM_GAP + ITEM_WIDTH), 0, width, height), mLinearPaint);
        path.reset();
        for (int i = -5; (i * (27 + ITEM_GAP)) < width; i++) {
            int start = ITEM_GAP * i + ITEM_WIDTH * i;
            int end = start + ITEM_WIDTH;
            rect.left = start;
            rect.top = 0;
            rect.right = end;
            rect.bottom = width;
//            canvas.drawRect(rect,mPaint);
//            path.reset();
            path.moveTo(start, 0);
            path.lineTo(end, 0);
            path.lineTo(end, width);
            path.lineTo(start, width);
            path.close();
        }
        canvas.drawPath(path, mPaint);
    }

    public void startAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, ITEM_GAP + ITEM_WIDTH, 0, 0);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(200); //动画持续时间
        translateAnimation.setFillAfter(false); //动画之后，停留在最后面
        translateAnimation.setRepeatMode(Animation.RESTART);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        setAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
        }
    }

    public void startScorll() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true)
//                {
//                    scroller.startScroll(0,0,-5*(ITEM_GAP+ITEM_WIDTH),0,3000);
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }


    public void start() {
        final android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true)
//                {
//                    if (offset-SPEED>0-(ITEM_GAP+ITEM_WIDTH))
//                    {
//                        offset = offset-SPEED;
//                    }else {
//                        offset = 0;
//                    }
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            scrollTo(offset,0);
//                        }
//                    });
//                    try {
//                        Thread.sleep(16);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        new Thread(this).start();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private long lastSyncTime = 0L;

    @Override
    public void run() {
        while (true) {
            lastSyncTime = System.currentTimeMillis();
            Canvas mCanvas = getHolder().lockCanvas();

            //for coverity
            if (mCanvas == null) {
                continue;
            }


            try {
                drawLine(mCanvas);
            } catch (Exception e) {

            } finally {
                if (mCanvas != null) {
                    //释放canvas对象并提交画布
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }
            try {
                long sleepTime = 50 - (System.currentTimeMillis() - lastSyncTime);
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                } else {
                    Log.i("ICIProgressLinearIndeterminate", "掉帧");
                    continue;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
