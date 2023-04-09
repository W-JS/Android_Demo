package com.android.widget_extra.wheelview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


//import com.android.internal.R;

import com.wjs.android.mylibrary2.R;

import java.util.List;

/**
 * @hide
 */
public class CalendarTextAdapter extends AbstractWheelTextAdapter1 {
    List<String> list;

    public CalendarTextAdapter(Context context, List<String> list, int currentItem, int leftPaing, int rightPadiing) {
        super(context, R.layout.ici_wheelview_year, NO_RESOURCE, currentItem, leftPaing, rightPadiing);
        this.list = list;
        setItemTextResource(R.id.tempValue);
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        View view = super.getItem(index, cachedView, parent);

        return view;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public CharSequence getItemText(int index) {
        return list.get(index) + "";
    }
}