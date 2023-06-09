
package com.android.widget_extra.wheelview;

import android.view.View;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * @hide
 */
public class WheelRecycle {
    // Cached items
    private List<View> items;

    // Cached empty items
    private List<View> emptyItems;

    // Wheel view
    private WheelView wheel;

    public WheelRecycle(WheelView wheel) {
        this.wheel = wheel;
    }

    public int recycleItems(LinearLayout layout, int firstItem, ItemsRange range) {
        int index = firstItem;
        for (int i = 0; i < layout.getChildCount(); ) {
            if (!range.contains(index)) {
                recycleView(layout.getChildAt(i), index);
                layout.removeViewAt(i);
                if (i == 0) { // first item
                    firstItem++;
                }
            } else {
                i++; // go to next item
            }
            index++;
        }
        return firstItem;
    }

    public View getItem() {
        return getCachedView(items);
    }

    public View getEmptyItem() {
        return getCachedView(emptyItems);
    }

    public void clearAll() {
        if (items != null) {
            items.clear();
        }
        if (emptyItems != null) {
            emptyItems.clear();
        }
    }

    private List<View> addView(View view, List<View> cache) {
        if (cache == null) {
            cache = new LinkedList<View>();
        }

        cache.add(view);
        return cache;
    }

    private void recycleView(View view, int index) {
        int count = wheel.getViewAdapter().getItemsCount();
        boolean canRecycle = (index < 0 || index >= count) && !wheel.isCyclic();
        if (canRecycle) {
            // empty view
            emptyItems = addView(view, emptyItems);
        } else {
            while (index < 0) {
                index = count + index;
            }
            index %= count;
            items = addView(view, items);
        }
    }

    private View getCachedView(List<View> cache) {
        if (cache != null && cache.size() > 0) {
            View view = cache.get(0);
            cache.remove(0);
            return view;
        }
        return null;
    }

}
