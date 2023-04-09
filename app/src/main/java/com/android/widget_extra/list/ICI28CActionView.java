package com.android.widget_extra.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wjs.android.demo.R;

//import com.android.internal.R;


public class ICI28CActionView extends View {
    public int ACTION_OVER = 40;
    //删除垃圾桶动效状态
    private static final int ANIMA_STATUS_DEFAULT = 1;
    private static final int ANIMA_STATUE_OPENED = 2;
    private static final int ANIMA_STATUE_OPEN_ANIM = 3;
    private static final int ANIMA_STATUE_CLOSE_ANIM = 4;
    private static final int ANIMA_STATUE_CLOSING = 5;

    private int width, height;
    private static final int ACTION_WIDTH = 176;
    private int visiWidth = 0;
    private Bitmap editBitmap, deleteBitmap;
    private Paint paint;
    private int animStatus = ANIMA_STATUS_DEFAULT;
    private OnListActionClick onListActionClick;
    public int[] deleteAnima = new int[]{R.drawable.ici28c_list_anim_delete000,
            R.drawable.ici28c_list_anim_delete001,
            R.drawable.ici28c_list_anim_delete002,
            R.drawable.ici28c_list_anim_delete003,
    };
    public int[] deleteOpen = new int[]{
            R.drawable.ici28c_list_anim_delete_open000,
            R.drawable.ici28c_list_anim_delete_open001,
            R.drawable.ici28c_list_anim_delete_open002,
            R.drawable.ici28c_list_anim_delete_open003
    };

    public int[] deleteClose = new int[]{
            R.drawable.ici28c_list_anim_delete_close000,
            R.drawable.ici28c_list_anim_delete_close001,
            R.drawable.ici28c_list_anim_delete_close002,
            R.drawable.ici28c_list_anim_delete_close002
    };

    public ICI28CActionView(Context context) {
        super(context);
        init();
    }

    public ICI28CActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ICI28CActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void init() {
        editBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ici28c_icon_60_edit);
        deleteBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ici28c_icon_60_delete);
        paint = new Paint();
    }

    public void setVisiWidth(int x) {
        if (handler == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    handler = new Handler(Looper.myLooper());
                    Looper.loop();
                }
            }).start();
        }
        visiWidth = x;
        if (x == ACTION_OVER + ACTION_WIDTH * 2 && animStatus == ANIMA_STATUS_DEFAULT) {
            targetStatus = ANIMA_STATUE_OPEN_ANIM;
            startAnima();
        }
        postInvalidate();
    }

    boolean isDeleteOpen = false;


    public void onPressRealse() {
        if (isDeleteOpen) {
            targetStatus = ANIMA_STATUS_DEFAULT;
            startAnima();
        }
    }

    private int clickType = 0;
    public static final int CLICK_EDIT = 1;
    public static final int CLICK_DELETE = 2;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isDeleteOpen) {
                    return true;
                }
                int x = (int) event.getX();
                if (x < 176 + ACTION_OVER / 2) {
                    clickType = CLICK_EDIT;
                } else {
                    clickType = CLICK_DELETE;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (onListActionClick == null) {
                    return true;
                }
                if (clickType == CLICK_EDIT) {
                    onListActionClick.onEditClick();
                } else if (clickType == CLICK_DELETE) {
                    onListActionClick.onDeleteClick();
                }
                break;
        }
        return true;
    }

    public void setOnListActionClick(OnListActionClick onListActionClick) {
        this.onListActionClick = onListActionClick;
    }

    private int targetStatus = ANIMA_STATUS_DEFAULT;
    private int deleteWidth;
    private Handler handler = new Handler(Looper.getMainLooper());

    public void startAnima() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (targetStatus == animStatus) {
                    return;
                }

                int newStatus = animStatus + 1;
                if (newStatus > ANIMA_STATUE_CLOSING) {
                    newStatus = newStatus - ANIMA_STATUE_CLOSING;
                }
                switch (newStatus) {
                    case ANIMA_STATUS_DEFAULT:
                        //回复原始模样
                        animStatus = ANIMA_STATUS_DEFAULT;
                        break;
                    case ANIMA_STATUE_OPENED:
                        isDeleteOpen = true;
                        //开始展開刪除控件
                        deleteWidth = ACTION_WIDTH + ACTION_OVER / 2;
                        int animOffset = 0;
                        for (int i = 0; i < 10; i++) {
                            deleteWidth = deleteWidth + 20;
                            deleteWidth = Math.min(deleteWidth, ACTION_WIDTH * 2 + ACTION_OVER);
                            if (i == 0 || i == 3 || i == 6 || i == 9) {
                                deleteAnimaBitmap = BitmapFactory.decodeResource(getResources(), deleteAnima[animOffset]);
                                animOffset++;
                            }
                            postInvalidate();
                            try {
                                Thread.sleep(24);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        animStatus = ANIMA_STATUE_OPENED;
                        break;
                    case ANIMA_STATUE_OPEN_ANIM:
                        animStatus = ANIMA_STATUE_OPEN_ANIM;
                        break;
                    case ANIMA_STATUE_CLOSE_ANIM:
                        //开始复原展开的控件

                        if (isDeleteOpen) {
//                                int animCloseOffset = 0;
//                                for (int i =0;i<10;i++)
//                                {
//                                    deleteWidth = deleteWidth-20;
//                                    deleteWidth = Math.max(deleteWidth,ACTION_WIDTH+ACTION_OVER/2);
//                                    if (i==0||i == 3||i ==6||i==9)
//                                    {
//                                        deleteAnimaBitmap = BitmapFactory.decodeResource(getResources(),deleteOpen[animCloseOffset]);
//                                        animCloseOffset++;
//                                    }
//                                    postInvalidate();
//                                    try {
//                                        Thread.sleep(24);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
                        }
                        isDeleteOpen = false;
                        animStatus = ANIMA_STATUE_CLOSE_ANIM;
                        break;
                    case ANIMA_STATUE_CLOSING:
                        //关闭动画
                        animStatus = ANIMA_STATUE_CLOSING;
                        break;

                }

                if (newStatus != targetStatus) {
                    startAnima();
                }

            }
        });


    }

    public Bitmap deleteAnimaBitmap;


    public interface OnListActionClick {
        void onEditClick();

        void onDeleteClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int itemWidth = 0;

        if (visiWidth / 2 < ACTION_WIDTH * 2) {
            itemWidth = visiWidth / 2;
        } else if (visiWidth >= ACTION_WIDTH * 2) {
            itemWidth = ACTION_WIDTH;
        }


        //draw edit
        paint.setAntiAlias(true);
        paint.setColor(0xFF43464F);
        canvas.drawRect(0, 0, itemWidth, height, paint);
        canvas.drawBitmap(editBitmap, 48, 35, paint);

        //draw delete
        paint.setAntiAlias(true);
        paint.setColor(0xFF913232);
        canvas.drawRect(itemWidth, 0, itemWidth * 2, height, paint);
        canvas.drawBitmap(deleteBitmap, 48 + itemWidth, 35, paint);


        //draw delete Anima
        if (isDeleteOpen) {
            //先将动画布局铺满整个页面
            int start = ACTION_WIDTH * 2 - deleteWidth;
            int end = visiWidth;
            int center = (end - start) / 2 + start;
            paint.setAntiAlias(true);
            paint.setColor(0xFF913232);
//                canvas.drawRect(start,0,end,height,paint);
            if (deleteAnimaBitmap != null) {
                canvas.drawRect(start, 0, end, height, paint);
                canvas.drawBitmap(deleteAnimaBitmap, center, 35, paint);
            } else {
                canvas.drawRect(start, 0, end, height, paint);
                canvas.drawBitmap(deleteBitmap, center, 35, paint);
            }
        }
    }
}
