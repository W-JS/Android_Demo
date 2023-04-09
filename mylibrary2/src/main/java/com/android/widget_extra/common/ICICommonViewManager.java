package com.android.widget_extra.common;

import android.content.Context;

import com.android.widget_extra.utils.ICITypeUtils;

public class ICICommonViewManager {


    public static void viewInit(ICICommonView commonView, Context context) {
        commonView.initCommon();
        switch (ICITypeUtils.getCarType(context)) {
            case ICITypeUtils.TYPE_CHER:
                commonView.initCher();
                break;
            case ICITypeUtils.TYPE_BUCK:
                commonView.initBuck();
                break;
        }

    }


}
