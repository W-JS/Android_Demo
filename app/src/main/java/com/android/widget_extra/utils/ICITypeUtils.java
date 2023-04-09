package com.android.widget_extra.utils;

import android.annotation.SuppressLint;
import android.content.Context;

//import com.yfvet.android.fgw.FgwManager;

public class ICITypeUtils {
    private static final int TYPE_9BQC = 1;
    private static final int TYPE_9BQB = 2;
    private static final int TYPE_9BYB = 3;
    private static final int TYPE_JBUC = 4;
    private static final int TYPE_JCSB = 5;
    private static final int TYPE_358 = 6;
    private static final int TYPE_358L = 7;
    private static final int TYPE_358L_HEV = 8;

    public static final int P_BRAN_VEHICLEBRAND = 0x08000B;
    public static final int TYPE_UNKONOW = 0;
    public static final int TYPE_CHER = 1;
    public static final int TYPE_BUCK = 2;
    public static int type = TYPE_UNKONOW;


    public static int customerType = TYPE_UNKONOW;

    public static void setCustomerType(int type) {
        customerType = type;
    }

    public static int getCarType(Context context) {
//        if (1 == 1)
//        {
//            type = TYPE_BUCK;
//        }
        //        1:9BQC   4:JBUC
//        2:9BQB   5:JCSB
//        3:9BYB   6:358
        if (customerType != TYPE_UNKONOW) {
            return customerType;
        }

        if (type == TYPE_UNKONOW) {


//             int localType = TYPE_UNKONOW;
//            String typeStr = System.getProperty("vendor.framework.commonui_cartype");
//            if (localType== TYPE_UNKONOW)
//            {
//            int  localType  = getCalibrationValue(context.getApplicationContext(), P_BRAN_VEHICLEBRAND);

//            }
            switch (TYPE_JBUC) {
                //0-别克、1-凯迪拉克、2-雪佛兰
                case TYPE_9BQC:
                case TYPE_JBUC:
                    //雪佛兰
                    type = TYPE_CHER;

                    break;
                case TYPE_9BQB:
                case TYPE_9BYB:
                case TYPE_JCSB:
                case TYPE_358:
                case TYPE_358L:
                case TYPE_358L_HEV:
                    //别克
                    type = TYPE_BUCK;
                    break;
                default:
                    type = TYPE_CHER;
                    break;
            }
        }
        return type;
    }

//    public static int getCalibrationValue(Context context, int itemId) {
//        int[] arr = null;
//        int calibrationValue = 0;
//        String calibraionStr = "";
//        @SuppressLint("WrongConstant") FgwManager mFgwManager = (FgwManager) context.getSystemService("fgw");
//        mFgwManager.initCalibration();
//        arr = mFgwManager.readCalibrationItem(itemId);
//        if (arr != null && arr.length > 0) {
//            if (arr.length <= 4) {
//                for (int i = 0; i < arr.length; i++) {
//                    calibrationValue <<= 8;
//                    calibrationValue += arr[i];
//                }
//                return calibrationValue;
//            } else {
//                for (int i = 0; i < arr.length; i++) {
//                    calibraionStr += Integer.toHexString(arr[i]);
//                }
//                try {
//                    calibrationValue = Integer.parseInt(calibraionStr);
//                    return calibrationValue;
//                } catch (NumberFormatException | NullPointerException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return -1;
//    }


}
