package com.android.widget_extra.list;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.widget_extra.selection.ICICheckBox;
import com.android.widget_extra.selection.ICIRadioButton;
import com.android.widget_extra.selection.ICISwitchButton;

import java.util.ArrayList;
import java.util.List;

public class ICI28ListChild {
    private ImageView leftImageView;
    private TextView mainTextView;
    private TextView textView2nd;
    private TextView textView3rd;
    private ICISwitchButton rightSwitchButton;
    private ICICheckBox rightCheckbox;
    private ICIRadioButton rightRadioButton;
    private ImageView rightMoreImageView;
    private List<ImageView> rightImageViews;
    private List<ImageView> actionViews;
    private LinearLayout mainTextGroup;
    private ImageView notiImageView;

    public ICI28ListChild() {
        rightImageViews = new ArrayList<>();
        actionViews = new ArrayList<>();
    }

    public ImageView getLeftImageView() {
        return leftImageView;
    }

    public void setLeftImageView(ImageView leftImageView) {
        this.leftImageView = leftImageView;
    }

    public TextView getMainTextView() {
        return mainTextView;
    }

    public void setMainTextView(TextView mainTextView) {
        this.mainTextView = mainTextView;
    }

    public TextView getTextView2nd() {
        return textView2nd;
    }

    public void setTextView2nd(TextView textView2nd) {
        this.textView2nd = textView2nd;
    }

    public TextView getTextView3rd() {
        return textView3rd;
    }

    public void setTextView3rd(TextView textView3rd) {
        this.textView3rd = textView3rd;
    }

    public ICISwitchButton getRightSwitchButton() {
        return rightSwitchButton;
    }

    public void setRightSwitchButton(ICISwitchButton rightSwitchButton) {
        this.rightSwitchButton = rightSwitchButton;
    }

    public ICICheckBox getRightCheckbox() {
        return rightCheckbox;
    }

    public void setRightCheckbox(ICICheckBox rightCheckbox) {
        this.rightCheckbox = rightCheckbox;
    }

    public ICIRadioButton getRightRadioButton() {
        return rightRadioButton;
    }

    public void setRightRadioButton(ICIRadioButton rightRadioButton) {
        this.rightRadioButton = rightRadioButton;
    }

    public ImageView getRightMoreImageView() {
        return rightMoreImageView;
    }

    public void setRightMoreImageView(ImageView rightMoreImageView) {
        this.rightMoreImageView = rightMoreImageView;
    }

    public List<ImageView> getRightImageViews() {
        return rightImageViews;
    }

    public void setRightImageViews(List<ImageView> rightImageViews) {
        this.rightImageViews = rightImageViews;
    }

    public List<ImageView> getActionViews() {
        return actionViews;
    }

    public void setActionViews(List<ImageView> actionViews) {
        this.actionViews = actionViews;
    }

    public LinearLayout getMainTextGroup() {
        return mainTextGroup;
    }

    public void setMainTextGroup(LinearLayout mainTextGroup) {
        this.mainTextGroup = mainTextGroup;
    }

    public ImageView getNotiImageView() {
        return notiImageView;
    }

    public void setNotiImageView(ImageView notiImageView) {
        this.notiImageView = notiImageView;
    }
}
