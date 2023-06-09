/*
 *  Android Wheel Control.
 *  https://code.google.com/p/android-wheel/
 *
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.android.widget_extra.wheelview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

//import com.android.internal.R;
import com.android.widget_extra.wheelview.adapter.AbstractWheelAdapter;
import com.android.widget_extra.wheelview.adapter.WheelViewAdapter;
import com.wjs.android.mylibrary2.R;

import java.util.LinkedList;
import java.util.List;


/***
 * @hide
 */
public class WheelView extends View {

    /*/ Modified by wulianghuan 2014-11-25
    private int[] SHADOWS_COLORS = new int[] { 0xFF111111,
            0x00AAAAAA, 0x00AAAAAA };
    //*/
    private int[] SHADOWS_COLORS = new int[]{0xFFFFFFFF, 0x00FFFFFF, 0x00FFFFFF};

    private static final int ITEM_OFFSET_PERCENT = 0;

    /**
     * Left and right padding value
     */
    private static final int PADDING = 0;

    /**
     * Default count of visible items
     */
    private static final int DEF_VISIBLE_ITEMS = 3;

    // Wheel Values
    private int currentItem = 0;

    // Count of visible items
    private int visibleItems = DEF_VISIBLE_ITEMS;

    // Item height
    private int itemHeight = 0;

    // Center Line
    private Drawable centerDrawable;

    // Wheel drawables
    private int wheelBackground = R.drawable.ici_wheel_bg;
    private int wheelForeground = R.drawable.ici_wheel_val;

    // Shadows drawables
    private GradientDrawable topShadow;
    private GradientDrawable bottomShadow;

    // Draw Shadows
    private boolean drawShadows = true;

    // Scrolling
    private WheelScroller scroller;
    private boolean isScrollingPerformed;
    private int scrollingOffset;

    // Cyclic
    boolean isCyclic = true;

    // Items layout
    private LinearLayout itemsLayout;

    // The number of first item in layout
    private int firstItem;

    // View adapter
    private AbstractWheelAdapter viewAdapter;

    // Recycle
    private WheelRecycle recycle = new WheelRecycle(this);

    // Listeners
    private List<OnWheelChangedListener> changingListeners = new LinkedList<OnWheelChangedListener>();
    private List<OnWheelScrollListener> scrollingListeners = new LinkedList<OnWheelScrollListener>();
    private List<OnWheelClickedListener> clickingListeners = new LinkedList<OnWheelClickedListener>();

    String label = "";

    public WheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WheelView(Context context) {
        super(context);
        initData(context);
    }

    private void initData(Context context) {
        scroller = new WheelScroller(getContext(), scrollingListener);
    }

    // Scrolling listener
    WheelScroller.ScrollingListener scrollingListener = new WheelScroller.ScrollingListener() {
        @Override
        public void onStarted() {
            isScrollingPerformed = true;
            notifyScrollingListenersAboutStart();
        }

        @Override
        public void onScroll(int distance) {
            doScroll(distance);

            int height = getHeight();
            if (scrollingOffset > height) {
                scrollingOffset = height;
                scroller.stopScrolling();
            } else if (scrollingOffset < -height) {
                scrollingOffset = -height;
                scroller.stopScrolling();
            }
        }

        @Override
        public void onFinished() {
            if (isScrollingPerformed) {
                notifyScrollingListenersAboutEnd();
                isScrollingPerformed = false;
            }

            scrollingOffset = 0;
            invalidate();
        }

        @Override
        public void onJustify() {
            if (Math.abs(scrollingOffset) > WheelScroller.MIN_DELTA_FOR_SCROLLING) {
                scroller.scroll(scrollingOffset, 0);
            }
        }
    };

    public void setInterpolator(Interpolator interpolator) {
        scroller.setInterpolator(interpolator);
    }

    public int getVisibleItems() {
        return visibleItems;
    }

    public void setVisibleItems(int count) {
        visibleItems = count;
    }

    public WheelViewAdapter getViewAdapter() {
        return viewAdapter;
    }

    // Adapter listener
    private DataSetObserver dataObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            invalidateWheel(false);
        }

        @Override
        public void onInvalidated() {
            invalidateWheel(true);
        }
    };

    public void setViewAdapter(AbstractWheelAdapter viewAdapter) {
        if (this.viewAdapter != null) {
            this.viewAdapter.unregisterDataSetObserver(dataObserver);
        }
        this.viewAdapter = viewAdapter;
        if (this.viewAdapter != null) {
            this.viewAdapter.registerDataSetObserver(dataObserver);
            setCurrentItem(viewAdapter.getCurrentIndex());
        }

        invalidateWheel(true);
    }

    public void addChangingListener(OnWheelChangedListener listener) {
        changingListeners.add(listener);
    }

    public void removeChangingListener(OnWheelChangedListener listener) {
        changingListeners.remove(listener);
    }

    public void removeChangingListener(OnWheelScrollListener listener) {
        scrollingListeners.remove(listener);
    }

    protected void notifyChangingListeners(int oldValue, int newValue) {
        for (OnWheelChangedListener listener : changingListeners) {
            listener.onChanged(this, oldValue, newValue);
            if (viewAdapter != null) {
                viewAdapter.setCurrentIndex(newValue);
                viewAdapter.notifyDataChangedEvent();
            }
        }
    }

    public void addScrollingListener(OnWheelScrollListener listener) {
        scrollingListeners.add(listener);
    }

    public void removeScrollingListener(OnWheelScrollListener listener) {
        scrollingListeners.remove(listener);
    }

    public void clearScrollingListener() {
        clickingListeners.clear();
    }

    protected void notifyScrollingListenersAboutStart() {
        for (OnWheelScrollListener listener : scrollingListeners) {
            listener.onScrollingStarted(this);
        }
    }

    protected void notifyScrollingListenersAboutEnd() {
        for (OnWheelScrollListener listener : scrollingListeners) {
            listener.onScrollingFinished(this);
        }
    }

    public void addClickingListener(OnWheelClickedListener listener) {
        clickingListeners.add(listener);
    }

    public void removeClickingListener(OnWheelClickedListener listener) {
        clickingListeners.remove(listener);
    }

    public void clearClickListener() {
        clickingListeners.clear();
    }

    protected void notifyClickListenersAboutClick(int item) {
        for (OnWheelClickedListener listener : clickingListeners) {
            listener.onItemClicked(this, item);
        }
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(int index, boolean animated, boolean isFromScorll) {
        if (viewAdapter == null || viewAdapter.getItemsCount() == 0) {
            return; // throw?
        }

        int itemCount = viewAdapter.getItemsCount();
        if (index < 0 || index >= itemCount) {
            if (isCyclic) {
                while (index < 0) {
                    index += itemCount;
                }
                index %= itemCount;
            } else {
                return; // throw?
            }
        }
        if (index != currentItem) {
            if (animated) {
                int itemsToScroll = index - currentItem;
                if (isCyclic) {
                    int scroll = itemCount + Math.min(index, currentItem) - Math.max(index, currentItem);
                    if (scroll < Math.abs(itemsToScroll)) {
                        itemsToScroll = itemsToScroll < 0 ? scroll : -scroll;
                    }
                }
                scroll(itemsToScroll, 0);
            } else {
                scrollingOffset = 0;

                int old = currentItem;
                currentItem = index;
                Log.i("WheelView", "setindex=" + index);
                if (isViewVisiable && isFromScorll) {
                    Log.i("WheelView", "soundTime=" + System.currentTimeMillis());
                    playSoundEffect(SoundEffectConstants.CLICK);
                }
                notifyChangingListeners(old, currentItem);

                invalidate();
            }
        }
    }

    private boolean isViewVisiable = true;

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility != VISIBLE) {
            isViewVisiable = false;
            scroller.stopScrolling();
            Log.i("WheelView", "visiableChange");
        } else {
            isViewVisiable = true;
        }

    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    public void setCurrentItem(int index) {
        setCurrentItem(index, false, false);
    }

    public boolean isCyclic() {
        return isCyclic;
    }

    public void setCyclic(boolean isCyclic) {
        this.isCyclic = isCyclic;
        invalidateWheel(false);
    }

    public boolean drawShadows() {
        return drawShadows;
    }

    public void setDrawShadows(boolean drawShadows) {
        this.drawShadows = drawShadows;
    }

    public void setShadowColor(int start, int middle, int end) {
        SHADOWS_COLORS = new int[]{start, middle, end};
    }

    public void setWheelBackground(int resource) {
        wheelBackground = resource;
        setBackgroundResource(wheelBackground);
    }

    public void setWheelForeground(int resource) {
        wheelForeground = resource;
        if (getContext().getResources() != null) {
            centerDrawable = getContext().getResources().getDrawable(wheelForeground);
        }
    }

    public synchronized void invalidateWheel(boolean clearCaches) {
        if (clearCaches) {
            recycle.clearAll();
            if (itemsLayout != null) {
                itemsLayout.removeAllViews();
            }
            scrollingOffset = 0;
        } else if (itemsLayout != null) {
            // cache all items
            recycle.recycleItems(itemsLayout, firstItem, new ItemsRange());
        }

        invalidate();
    }

    private void initResourcesIfNecessary() {
        if (centerDrawable == null && getContext().getResources() != null) {
            centerDrawable = getContext().getResources().getDrawable(wheelForeground);
        }

        if (topShadow == null) {
            topShadow = new GradientDrawable(Orientation.TOP_BOTTOM, SHADOWS_COLORS);
        }

        if (bottomShadow == null) {
            bottomShadow = new GradientDrawable(Orientation.BOTTOM_TOP, SHADOWS_COLORS);
        }

        //setBackgroundResource(wheelBackground);
    }

    private int getDesiredHeight(LinearLayout layout) {
        if (layout != null && layout.getChildAt(0) != null) {
            itemHeight = layout.getChildAt(0).getMeasuredHeight();
        }
        Log.i("WheelView", "itemHeight=" + itemHeight);

        int desired = itemHeight * visibleItems - itemHeight * ITEM_OFFSET_PERCENT / 50;

        return Math.max(desired, getSuggestedMinimumHeight());
    }

    private int getItemHeight() {
        if (itemHeight != 0) {
            return itemHeight;
        }

        if (itemsLayout != null && itemsLayout.getChildAt(0) != null) {
            itemHeight = itemsLayout.getChildAt(0).getHeight();
            return itemHeight;
        }

        return getHeight() / visibleItems;
    }

    private int calculateLayoutWidth(int widthSize, int mode) {
        initResourcesIfNecessary();

        // TODO: make it static
        itemsLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        itemsLayout.measure(MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int width = itemsLayout.getMeasuredWidth();
        if (mode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width += 2 * PADDING;

            // Check against our minimum width
            width = Math.max(width, getSuggestedMinimumWidth());

            if (mode == MeasureSpec.AT_MOST && widthSize < width) {
                width = widthSize;
            }
        }

        itemsLayout.measure(MeasureSpec.makeMeasureSpec(width - 2 * PADDING, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        return width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        buildViewForMeasuring();

        int width = calculateLayoutWidth(widthSize, widthMode);

        int height;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getDesiredHeight(itemsLayout);

            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layout(r - l, b - t);
    }

    private void layout(int width, int height) {
        int itemsWidth = width - 2 * PADDING;
        itemsLayout.layout(0, 0, itemsWidth, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (viewAdapter != null && viewAdapter.getItemsCount() > 0) {
            updateView();

            drawItems(canvas);
            //drawCenterRect(canvas);
        }

        //	if (drawShadows) drawShadows(canvas);
    }

    private void drawShadows(Canvas canvas) {
		/*/ Modified by wulianghuan 2014-11-25
		int height = (int)(1.5 * getItemHeight());
		//*/
        int height = (int) (3 * getItemHeight());
        //*/
        //topShadow.setBounds(0, 0, getWidth(), height);
        topShadow.setBounds(0, 0, getWidth(), getHeight());
        topShadow.draw(canvas);

        //bottomShadow.setBounds(0, getHeight() - height, getWidth(), getHeight());
        bottomShadow.setBounds(0, 0, getWidth(), getHeight());
        bottomShadow.draw(canvas);
    }

    private void drawItems(Canvas canvas) {
        canvas.save();
        int top = (currentItem - firstItem) * getItemHeight() + (getItemHeight() - getHeight()) / 2;
        canvas.translate(PADDING, -top + scrollingOffset);
        itemsLayout.draw(canvas);
        canvas.restore();
    }

//	private void drawCenterRect(Canvas canvas) {
//		int center = getHeight() / 2;
//		int offset = (int) (getItemHeight() / 2 * 1.2f);
////		int offset = 60;
//		/*/ Remarked by wulianghuan 2014-11-27  使用自己的画线，而不是描边
//		Rect rect = new Rect(left, top, right, bottom)
//		centerDrawable.setBounds(bounds)
//		centerDrawable.setBounds(0, center - offset, getWidth(), center + offset);
//		centerDrawable.draw(canvas);
//		//*/
//		Paint paint = new Paint();
//		paint.setColor(getResources().getColor(R.color.ici_province_line_border));
//		// 设置线宽
////		paint.setStrokeWidth((float) 3);
//		paint.setStrokeWidth((float) 2);
//		// 绘制上边直线
//		canvas.drawLine(0, center - offset, getWidth(), center - offset, paint);
//		// 绘制下边直线
//		canvas.drawLine(0, center + offset, getWidth(), center + offset, paint);
//		//*/
//	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("Whee lView", "soundTime" + System.currentTimeMillis());
//				playSoundEffect(SoundEffectConstants.CLICK);
                break;
            case MotionEvent.ACTION_MOVE:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (!isScrollingPerformed) {
                    int distance = (int) event.getY() - getHeight() / 2;
                    if (distance > 0) {
                        distance += getItemHeight() / 2;
                    } else {
                        distance -= getItemHeight() / 2;
                    }
                    int items = distance / getItemHeight();
                    if (items != 0 && isValidItemIndex(currentItem + items)) {
                        notifyClickListenersAboutClick(currentItem + items);
                    }
                }
                break;
        }

        return scroller.onTouchEvent(event);
    }

    private void doScroll(int delta) {
        scrollingOffset += delta;

        int itemHeight = getItemHeight();
        int count = scrollingOffset / itemHeight;

        int pos = currentItem - count;
        int itemCount = viewAdapter.getItemsCount();

        int fixPos = scrollingOffset % itemHeight;
        if (Math.abs(fixPos) <= itemHeight / 2) {
            fixPos = 0;
        }
        if (isCyclic && itemCount > 0) {
            if (fixPos > 0) {
                pos--;
                count++;
            } else if (fixPos < 0) {
                pos++;
                count--;
            }
            // fix position by rotating
            while (pos < 0) {
                pos += itemCount;
            }
            pos %= itemCount;
        } else {
            //
            if (pos < 0) {
                count = currentItem;
                pos = 0;
            } else if (pos >= itemCount) {
                count = currentItem - itemCount + 1;
                pos = itemCount - 1;
            } else if (pos > 0 && fixPos > 0) {
                pos--;
                count++;
            } else if (pos < itemCount - 1 && fixPos < 0) {
                pos++;
                count--;
            }
        }

        int offset = scrollingOffset;
        if (pos != currentItem) {
            setCurrentItem(pos, false, true);
        } else {
            invalidate();
        }

        // update offset
        scrollingOffset = offset - count * itemHeight;
        if (scrollingOffset > getHeight()) {
            scrollingOffset = scrollingOffset % getHeight() + getHeight();
        }
    }

    public void scroll(int itemsToScroll, int time) {
        int distance = itemsToScroll * getItemHeight() - scrollingOffset;
        scroller.scroll(distance, time);
    }

    private ItemsRange getItemsRange() {
        if (getItemHeight() == 0) {
            return null;
        }

        int first = currentItem;
        int count = 1;

        while (count * getItemHeight() < getHeight()) {
            first--;
            count += 2; // top + bottom items
        }

        if (scrollingOffset != 0) {
            if (scrollingOffset > 0) {
                first--;
            }
            count++;

            // process empty items above the first or below the second
            int emptyItems = scrollingOffset / getItemHeight();
            first -= emptyItems;
            count += Math.asin(emptyItems);
        }
        return new ItemsRange(first, count);
    }

    private boolean rebuildItems() {
        boolean updated = false;
        ItemsRange range = getItemsRange();

        //for coverity
        if (range == null) {
            return false;
        }


        if (itemsLayout != null) {
            int first = recycle.recycleItems(itemsLayout, firstItem, range);
            updated = firstItem != first;
            firstItem = first;
        } else {
            createItemsLayout();
            updated = true;
        }

        if (!updated) {
            updated = firstItem != range.getFirst() || itemsLayout.getChildCount() != range.getCount();
        }

        if (firstItem > range.getFirst() && firstItem <= range.getLast()) {
            for (int i = firstItem - 1; i >= range.getFirst(); i--) {
                if (!addViewItem(i, true)) {
                    break;
                }
                firstItem = i;
            }
        } else {
            firstItem = range.getFirst();
        }

        int first = firstItem;
        for (int i = itemsLayout.getChildCount(); i < range.getCount(); i++) {
            if (!addViewItem(firstItem + i, false) && itemsLayout.getChildCount() == 0) {
                first++;
            }
        }
        firstItem = first;

        return updated;
    }

    private void updateView() {
        if (rebuildItems()) {
            calculateLayoutWidth(getWidth(), MeasureSpec.EXACTLY);
            layout(getWidth(), getHeight());
        }
    }

    private void createItemsLayout() {
        if (itemsLayout == null) {
            itemsLayout = new LinearLayout(getContext());
            itemsLayout.setOrientation(LinearLayout.VERTICAL);
        }
    }

    private void buildViewForMeasuring() {
        // clear all items
        if (itemsLayout != null) {
            recycle.recycleItems(itemsLayout, firstItem, new ItemsRange());
        } else {
            createItemsLayout();
        }

        // add views
        int addItems = visibleItems / 2;
        for (int i = currentItem + addItems; i >= currentItem - addItems; i--) {
            if (addViewItem(i, true)) {
                firstItem = i;
            }
        }
    }

    private boolean addViewItem(int index, boolean first) {
        View view = getItemView(index);
        if (view != null) {
            if (first) {
                itemsLayout.addView(view, 0);
            } else {
                itemsLayout.addView(view);
            }

            return true;
        }

        return false;
    }

    private boolean isValidItemIndex(int index) {
        return viewAdapter != null && viewAdapter.getItemsCount() > 0 &&
                (isCyclic || index >= 0 && index < viewAdapter.getItemsCount());
    }

    private View getItemView(int index) {
        if (viewAdapter == null || viewAdapter.getItemsCount() == 0) {
            return null;
        }
        int count = viewAdapter.getItemsCount();
        if (!isValidItemIndex(index)) {
            return viewAdapter.getEmptyItem(recycle.getEmptyItem(), itemsLayout);
        } else {
            while (index < 0) {
                index = count + index;
            }
        }

        index %= count;
        return viewAdapter.getItem(index, recycle.getItem(), itemsLayout);
    }

    public void stopScrolling() {
        scroller.stopScrolling();
    }
}
