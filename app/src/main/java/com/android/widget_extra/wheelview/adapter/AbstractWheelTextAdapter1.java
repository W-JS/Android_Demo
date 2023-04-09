/*
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
package com.android.widget_extra.wheelview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.widget_extra.common.ICICommonView;
import com.android.widget_extra.common.ICICommonViewManager;
import com.android.widget_extra.utils.FontUtils;

import java.util.ArrayList;

/**
 * @hide
 */
public abstract class AbstractWheelTextAdapter1 extends AbstractWheelAdapter implements ICICommonView {

    /**
     * Text view resource. Used as a default view for adapter.
     */
    public static final int TEXT_VIEW_ITEM_RESOURCE = -1;

    /**
     * No resource constant.
     */
    protected static final int NO_RESOURCE = 0;

    /**
     * Default text color
     */
    public static final int DEFAULT_TEXT_COLOR = 0xFF101010;

    /**
     * Default text color
     */
    public static final int LABEL_COLOR = 0xFF700070;

    /**
     * Default text size
     */
    public static final int DEFAULT_TEXT_SIZE = 24;

    // Text settings
    private int textColor = DEFAULT_TEXT_COLOR;
    private int textSize = DEFAULT_TEXT_SIZE;

    // Current context
    protected Context context;
    // Layout inflater
    protected LayoutInflater inflater;

    // Items resources
    protected int itemResourceId;
    protected int itemTextResourceId;

    // Empty items resources
    protected int emptyItemResourceId;


    private int leftPaiding = 14;
    private int rightPading = 12;
    private ArrayList<View> arrayList = new ArrayList<View>();
    private String selectTextColor, normalTextColor;
    private Typeface defaultTypeface, selectTypeface;

    protected AbstractWheelTextAdapter1(Context context, int itemResource, int itemTextResource, int currentIndex,
                                        int leftPaiding, int rightPading) {
        this(context, itemResource, itemTextResource, currentIndex, leftPaiding, rightPading, 0);
    }

    protected AbstractWheelTextAdapter1(Context context, int itemResource, int itemTextResource, int currentIndex,
                                        int leftPaiding, int rightPading, int midsize) {
        this.context = context;
        itemResourceId = itemResource;
        itemTextResourceId = itemTextResource;
        this.currentIndex = currentIndex;
        this.leftPaiding = leftPaiding;
        this.rightPading = rightPading;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ICICommonViewManager.viewInit(this, context);
    }

    @Override
    public void initCommon() {


    }


    @Override
    public void initBuck() {
        selectTextColor = "#FFE5B586";
        normalTextColor = "#CCFFFFFF";
        defaultTypeface = FontUtils.getTypeFace(FontUtils.TYPE_BUCK_THIN);
        selectTypeface = FontUtils.getTypeFace(FontUtils.TYPE_BUCK_LIGHT);
    }


    @Override
    public void initCher() {
        selectTextColor = "#FFE29103";
        normalTextColor = "#66ffffff";
        defaultTypeface = FontUtils.getTypeFace(FontUtils.TYPE_LIGHT);
        selectTypeface = FontUtils.getTypeFace(FontUtils.TYPE_LIGHT);
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setItemTextResource(int itemTextResourceId) {
        this.itemTextResourceId = itemTextResourceId;
    }


    protected abstract CharSequence getItemText(int index);

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        if (index >= 0 && index < getItemsCount()) {
            if (convertView == null) {
                convertView = getView(itemResourceId, parent);
            }
            boolean isEnglish = false;

            //for coverity
            if (context.getResources() == null
                    || context.getResources().getConfiguration() == null ||
                    context.getResources().getConfiguration().getLocales() == null ||
                    context.getResources().getConfiguration().getLocales().get(0) == null ||
                    context.getResources().getConfiguration().getLocales().get(0).getLanguage() == null
            ) {
                return null;
            }

            String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
            if (language.equals("en")) {
                isEnglish = true;
            }
            convertView.setContentDescription("ll-wheelview" + index);
            TextView textView = getTextView(convertView, itemTextResourceId);
            if (!arrayList.contains(textView)) {
                arrayList.add(textView);
            }
            if (textView != null) {
                CharSequence text = getItemText(index);
                if (text == null) {
                    text = "";
                }
                textView.setText(text);
                if (index == currentIndex) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 42);
                    textView.setTextColor(Color.parseColor(selectTextColor));
                    LinearLayout.LayoutParams textViewLayoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                    textViewLayoutParams.height = 86;
                    textView.setLayoutParams(textViewLayoutParams);
                    textView.setTypeface(selectTypeface);
                } else {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 36);
                    textView.setTextColor(Color.parseColor(normalTextColor));
                    textView.setTypeface(defaultTypeface);
                    LinearLayout.LayoutParams textViewLayoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                    textViewLayoutParams.height = 86;
                    textView.setLayoutParams(textViewLayoutParams);
                }
                if (!isEnglish) {
                    textView.setPadding(leftPaiding, 0, rightPading, 0);
                }
                if (itemResourceId == TEXT_VIEW_ITEM_RESOURCE) {
                    configureTextView(textView);
                }
            }
            return convertView;
        }
        return null;
    }

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = getView(emptyItemResourceId, parent);
        }
        if (emptyItemResourceId == TEXT_VIEW_ITEM_RESOURCE && convertView instanceof TextView) {
            configureTextView((TextView) convertView);
        }

        return convertView;
    }

    protected void configureTextView(TextView view) {
        view.setTextColor(textColor);
        view.setGravity(Gravity.CENTER);
        view.setTextSize(textSize);
        view.setLines(1);
        view.setTypeface(FontUtils.getDefaultFont(), Typeface.BOLD);
    }

    private TextView getTextView(View view, int textResource) {
        TextView text = null;
        try {
            if (textResource == NO_RESOURCE && view instanceof TextView) {
                text = (TextView) view;
            } else if (textResource != NO_RESOURCE) {
                text = (TextView) view.findViewById(textResource);
            }
        } catch (ClassCastException e) {
            Log.e("AbstractWheelAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e);
        }

        return text;
    }

    private View getView(int resource, ViewGroup parent) {
        switch (resource) {
            case NO_RESOURCE:
                return null;
            case TEXT_VIEW_ITEM_RESOURCE:
                return new TextView(context);
            default:
                return inflater.inflate(resource, parent, false);
        }
    }
}
