package com.android.widget_extra.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.android.internal.R;
import com.android.widget_extra.utils.FontUtils;
import com.android.widget_extra.wheelview.OnWheelChangedListener;
import com.android.widget_extra.wheelview.OnWheelClickedListener;
import com.android.widget_extra.wheelview.OnWheelScrollListener;
import com.android.widget_extra.wheelview.WheelView;
import com.android.widget_extra.wheelview.adapter.AbstractWheelTextAdapter1;
import com.android.widget_extra.wheelview.adapter.CalendarTextAdapter;
import com.wjs.android.demo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ICIDateAndTimePicker extends RelativeLayout implements OnWheelScrollListener {
    private static final String TAG = "ICITimePicker";
    /**
     * 年
     */
    private static String[] YEARS = new String[]
            {"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036",
                    "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"};
    /**
     * 月
     */
    private static final String[] MONTHS = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12"};

    private static final String[] MONTHS_EN = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    /**
     * 日
     */
    private static final String[] DAYS = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                    "31"};

    /**
     * 日
     */
    private static final String[] DAYS2 = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};

    /**
     * 日
     */
    private static final String[] DAYS3 = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29"};
    /**
     * 日
     */
    private static final String[] DAYS4 = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28"};

    /**
     * 时
     */
    private static final String[] HOURS = new String[]
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12"};

    /**
     * 时
     */
    private static final String[] HOURS_24 = new String[]
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23"};

    /**
     * 分
     */
    private static final String[] MINUTES = new String[]
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                    "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
                    "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
                    "53", "54", "55", "56", "57", "58", "59"};
    /**
     * 上午下午
     */
    private static final String[] AM = new String[]
            {"上午", "下午"};
    private static final String[] AM_EN = new String[]{"AM", "PM"};


    public interface OnTimePickListener {
        void onSelected(String year, String month, String day);
    }

    public interface OnAllTimePickListener {
        void onSelected(String year, String month, String day, String hour, String minute, String am);
    }

    private View llRight;
    private TextView yearTv, mongthTv, dayTv, colonTv;
    private WheelView yearView;
    private WheelView monthView;
    private WheelView dayView;
    private WheelView hourView;
    private WheelView minuteView;
    private WheelView amView;
    private CalendarTextAdapter mYearAdapter;
    private CalendarTextAdapter mMonthAdapter;
    private CalendarTextAdapter mDaydapter;
    private CalendarTextAdapter mHourAdapter;
    private CalendarTextAdapter mMinuteAdapter;
    private CalendarTextAdapter2 mAmdapter;
    private int maxTextSize = 40;
    private int minTextSize = 36;
    protected String year;
    protected String month = "0";
    protected String day;
    protected String hour;
    protected String minute;
    protected String am;
    protected boolean hour24;
    protected boolean isEnglish;
    protected boolean showYear, showMonth, showDay, showHours, showMin;
    private Context mContext;
    protected OnAllTimePickListener onAllTimePickListener;

    public ICIDateAndTimePicker(Context context) {
        this(context, null);
    }

    public ICIDateAndTimePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ICIDateAndTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }


    public void setOnAllTimePickListener(OnAllTimePickListener onAllTimePickListener) {
        this.onAllTimePickListener = onAllTimePickListener;
    }


    private void initView() {
        //for covrity
        if (getResources() == null
                || getResources().getConfiguration() == null
                || getResources().getConfiguration().getLocales() == null
                || getResources().getConfiguration().getLocales().get(0) == null
                || getResources().getConfiguration().getLocales().get(0).getLanguage() == null) {
            return;
        }


        String language = getResources().getConfiguration().getLocales().get(0).getLanguage();
        if (language.equals("en")) {
            isEnglish = true;
        }
        View child;
        if (isEnglish) {
            child = LayoutInflater.from(getContext()).inflate(R.layout.ici_time_picker_en, this,
                    false);
        } else {
            child = LayoutInflater.from(getContext()).inflate(R.layout.ici_time_picker, this,
                    false);
        }

        addView(child, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        yearTv = child.findViewById(R.id.text_year);
        mongthTv = child.findViewById(R.id.text_month);
        dayTv = child.findViewById(R.id.text_day);
        colonTv = child.findViewById(R.id.text_colon);
        yearView = child.findViewById(R.id.wheel_view_year);
        monthView = child.findViewById(R.id.wheel_view_month);
        dayView = child.findViewById(R.id.wheel_view_day);
        hourView = child.findViewById(R.id.wheel_view_hour);
        minuteView = child.findViewById(R.id.wheel_view_minute);
        amView = child.findViewById(R.id.wheel_view_am);
        llRight = child.findViewById(R.id.wheelview_ll_right);
        initTypeFace();
        initListener();
        yearView.addScrollingListener(this);
    }

    private void initTypeFace() {
        yearTv.setTypeface(FontUtils.getDefaultFont());
        mongthTv.setTypeface(FontUtils.getDefaultFont());
        dayTv.setTypeface(FontUtils.getDefaultFont());
        colonTv.setTypeface(FontUtils.getDefaultFont());
    }

    public void setMaxyear(int startYear, int endYear) {
        List<String> yearList = new ArrayList<>();
        for (int i = startYear; i <= endYear; i++) {
            yearList.add(i + "");
        }
        YEARS = yearList.toArray(new String[]{});

    }

    public void initDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        simpleDateFormat.applyPattern("yyyy");
        year = simpleDateFormat.format(time);
        simpleDateFormat.applyPattern("M");
        month = simpleDateFormat.format(time);
        simpleDateFormat.applyPattern("d");
        day = simpleDateFormat.format(time);
        if (hour24) {
            simpleDateFormat.applyPattern("H");
        } else {
            simpleDateFormat.applyPattern("h");
        }
        hour = simpleDateFormat.format(time);
        simpleDateFormat.applyPattern("mm");
        minute = simpleDateFormat.format(time);


//        monthView.setCurrentItem(Arrays.asList(MONTHS).indexOf(month));
//        yearView.setCurrentItem(Arrays.asList(YEARS).indexOf(year));
//        initDayView(Arrays.asList(MONTHS).indexOf(month));
        if (hour24) {
//                hourView.setCurrentItem(Arrays.asList(HOURS_24).indexOf(hour));
        } else {
//                hourView.setCurrentItem(Arrays.asList(HOURS).indexOf(hour));
            if (!hour24) {
                try {
                    simpleDateFormat.applyPattern("H");

                    if (Integer.parseInt(simpleDateFormat.format(time)) >= 12) {
                        if (isEnglish) {
                            am = "PM";
                        } else {
                            am = "下午";
                        }

                    } else {
                        if (isEnglish) {
                            am = "AM";
                        } else {
                            am = "上午";
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
//                amView.setCurrentItem(Arrays.asList(AM).indexOf(am));
        }
//            minuteView.setCurrentItem(Arrays.asList(MINUTES).indexOf(minute));

        initData(mContext);
    }


    private void changeMonth(int month) {
        switch (month + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                List<String> dayStrings = Arrays.asList(DAYS);
                int index = dayStrings.indexOf(day);
                mDaydapter = new CalendarTextAdapter(mContext, dayStrings, index, 24, 46);
                dayView.setVisibleItems(3);
                dayView.setViewAdapter(mDaydapter);
                dayView.setCurrentItem(index);
                dayView.addClickingListener(new OnWheelClickedListener() {
                    @Override
                    public void onItemClicked(WheelView wheel, int itemIndex) {
                        dayView.setCurrentItem(itemIndex, false, true);
                        onSelected();
                    }
                });

                break;
            case 4:
            case 6:
            case 9:
            case 11:
                dayStrings = Arrays.asList(DAYS2);
                index = dayStrings.indexOf(day);
                if (index == -1) {
                    index = dayStrings.size() - 1;
                }
                mDaydapter = new CalendarTextAdapter(mContext, dayStrings, index, 24, 46);
                dayView.setVisibleItems(3);
                dayView.setViewAdapter(mDaydapter);
                dayView.setCurrentItem(index);
                dayView.addClickingListener(new OnWheelClickedListener() {
                    @Override
                    public void onItemClicked(WheelView wheel, int itemIndex) {
                        dayView.setCurrentItem(itemIndex, false, true);
                        onSelected();
                    }
                });
                break;
            case 2:
                int yearNum = Integer.parseInt(year);
                // 判断闰年
                boolean checkYear = yearNum % 4 == 0 || (yearNum % 100 == 0 && yearNum % 400 == 0);
                if (checkYear) {
                    dayStrings = Arrays.asList(DAYS3);
                    index = dayStrings.indexOf(day);
                    if (index == -1) {
                        index = dayStrings.size() - 1;
                    }
                    mDaydapter = new CalendarTextAdapter(mContext, dayStrings, index, 24, 46);
                    dayView.setVisibleItems(3);
                    dayView.setViewAdapter(mDaydapter);
                    dayView.setCurrentItem(index);
                } else {
                    dayStrings = Arrays.asList(DAYS4);
                    index = dayStrings.indexOf(day);
                    if (index == -1) {
                        index = dayStrings.size() - 1;
                    }
                    mDaydapter = new CalendarTextAdapter(mContext, dayStrings, index, 24, 46);
                    dayView.setVisibleItems(3);
                    dayView.setViewAdapter(mDaydapter);
                    dayView.setCurrentItem(index);
                }
                dayView.addClickingListener(new OnWheelClickedListener() {
                    @Override
                    public void onItemClicked(WheelView wheel, int itemIndex) {
                        dayView.setCurrentItem(itemIndex, false, true);
                        onSelected();
                    }
                });
                break;
        }
    }

    protected void setShowItem(boolean showYear, boolean showMonth, boolean showDay, boolean showHours, boolean showMin) {
        if (!showYear) {
            yearView.setVisibility(GONE);
            yearTv.setVisibility(GONE);
        } else {
            yearView.setVisibility(VISIBLE);
            yearTv.setVisibility(VISIBLE);
        }
        if (!showMonth) {
            mongthTv.setVisibility(GONE);
            monthView.setVisibility(GONE);
        } else {
            mongthTv.setVisibility(VISIBLE);
            monthView.setVisibility(VISIBLE);
        }
        if (!showDay) {
            dayTv.setVisibility(GONE);
            dayView.setVisibility(GONE);
        } else {
            dayTv.setVisibility(VISIBLE);
            dayView.setVisibility(VISIBLE);
        }
        if (!showHours) {
            hourView.setVisibility(GONE);
            colonTv.setVisibility(GONE);
            amView.setVisibility(GONE);
        } else {
            hourView.setVisibility(VISIBLE);
        }
        if (!showMin) {
            minuteView.setVisibility(GONE);
        } else {
            minuteView.setVisibility(VISIBLE);
        }
    }

    private void changeYear(int month) {
        if ((month + 1) == 2) {
            List<String> dayStrings;
            int index;
            int yearNum = Integer.parseInt(year);
            // 判断闰年
            boolean checkYear = yearNum % 4 == 0 || (yearNum % 100 == 0 && yearNum % 400 == 0);
            if (checkYear) {
                dayStrings = Arrays.asList(DAYS3);
                index = dayStrings.indexOf(day);
                if (index == -1) {
                    index = dayStrings.size() - 1;
                }
                mDaydapter = new CalendarTextAdapter(mContext, dayStrings, index, 24, 46);
                dayView.setVisibleItems(3);
                dayView.setViewAdapter(mDaydapter);
                dayView.setCurrentItem(index);
            } else {
                dayStrings = Arrays.asList(DAYS4);
                index = dayStrings.indexOf(day);
                if (index == -1) {
                    index = dayStrings.size() - 1;
                }
                mDaydapter = new CalendarTextAdapter(mContext, dayStrings, index, 24, 46);
                dayView.setVisibleItems(3);
                dayView.setViewAdapter(mDaydapter);
                dayView.setCurrentItem(index);
            }
        }
    }

    private String originHour;

    private void initListener() {

        yearView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                year = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                changeYear(monthView.getCurrentItem());
            }
        });


        monthView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                month = (wheel.getCurrentItem() + 1) + "";
                changeMonth(wheel.getCurrentItem());
            }
        });

        dayView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                day = (String) mDaydapter.getItemText(wheel.getCurrentItem());
            }
        });

        hourView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                originHour = hour;
                hour = (String) mHourAdapter.getItemText(wheel.getCurrentItem());
                if (Integer.parseInt(originHour) == 11 && !hour24 && Integer.parseInt(hour) == 12) {
                    amView.setCurrentItem(amView.getCurrentItem() == 0 ? 1 : 0);
                }
                if (Integer.parseInt(originHour) == 12 && !hour24 && Integer.parseInt(hour) == 11) {
                    amView.setCurrentItem(amView.getCurrentItem() == 0 ? 1 : 0);
                }

            }
        });
        minuteView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                minute = (String) mMinuteAdapter.getItemText(wheel.getCurrentItem());
            }
        });
        amView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                am = (String) mAmdapter.getItemText(wheel.getCurrentItem());
            }
        });

        yearView.addScrollingListener(this);
        monthView.addScrollingListener(this);
        dayView.addScrollingListener(this);
        hourView.addScrollingListener(this);
        minuteView.addScrollingListener(this);
        amView.addScrollingListener(this);

    }

    private void onSelected() {
        if (onAllTimePickListener != null) {
            if (hour24) {
                onAllTimePickListener.onSelected(year, month, day, hour, minute, "");
            } else {
                //上午并且为12点则为早上0点
                if (Integer.parseInt(hour) == 12 && amView.getCurrentItem() == 0) {
                    onAllTimePickListener.onSelected(year, (monthView.getCurrentItem() + 1) + "", day, "0", minute, "");
                } else if (Integer.parseInt(hour) == 12 && amView.getCurrentItem() == 1
                ) {
                    onAllTimePickListener.onSelected(year, (monthView.getCurrentItem() + 1) + "", day, "12", minute, "");

                } else {
                    onAllTimePickListener.onSelected(year, (monthView.getCurrentItem() + 1) + "", day, String.valueOf(Integer.parseInt(hour) + 12 * amView.getCurrentItem()), minute, "");
                }
            }
        }
    }

    public static int pixelToSp(Context context, float pixelValue) {

        //for coverity
        if (context.getResources() == null
                || context.getResources().getDisplayMetrics() == null) {
            return 0;
        }

        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        int sp = (int) (pixelValue / scaledDensity + 0.5f);
        return sp;
    }

    private void initData(Context context) {
        yearView.clearClickListener();
        monthView.clearClickListener();
        dayView.clearClickListener();
        hourView.clearClickListener();
        minuteView.clearClickListener();
        amView.clearClickListener();

        yearView.clearScrollingListener();
        monthView.clearScrollingListener();
        dayView.clearScrollingListener();
        hourView.clearScrollingListener();
        minuteView.clearScrollingListener();
        amView.clearScrollingListener();

        maxTextSize = pixelToSp(context, maxTextSize);
        minTextSize = pixelToSp(context, minTextSize);
        List<String> strings = Arrays.asList(YEARS);
        mYearAdapter = new CalendarTextAdapter(context, strings, strings.indexOf(year), 23, 53);
        yearView.setVisibleItems(3);
        yearView.setViewAdapter(mYearAdapter);
        yearView.addClickingListener(new OnWheelClickedListener() {
            @Override
            public void onItemClicked(WheelView wheel, int itemIndex) {
                yearView.setCurrentItem(itemIndex, false, true);
                onSelected();
            }
        });
        //wvYear.setCurrentItem(setYear(currentYear));
        if (isEnglish) {
            strings = Arrays.asList(MONTHS_EN);
        } else {
            strings = Arrays.asList(MONTHS);
        }
        mMonthAdapter = new CalendarTextAdapter(context, strings, Integer.parseInt(month) - 1, 24, 61);
        monthView.setVisibleItems(3);
        monthView.setViewAdapter(mMonthAdapter);
        monthView.addClickingListener(new OnWheelClickedListener() {
            @Override
            public void onItemClicked(WheelView wheel, int itemIndex) {
                monthView.setCurrentItem(itemIndex, false, true);
                onSelected();
            }
        });
        changeMonth(Integer.parseInt(month) - 1);


        if (hour24) {
            strings = Arrays.asList(HOURS_24);
            mHourAdapter = new CalendarTextAdapter(context, Arrays.asList(HOURS_24), strings.indexOf(hour), 0, 0);
        } else {
            strings = Arrays.asList(HOURS);
            mHourAdapter = new CalendarTextAdapter(context, Arrays.asList(HOURS), strings.indexOf(hour), 0, 0);
        }
        hourView.setVisibleItems(3);
        hourView.setViewAdapter(mHourAdapter);
        hourView.addClickingListener(new OnWheelClickedListener() {
            @Override
            public void onItemClicked(WheelView wheel, int itemIndex) {
                hourView.setCurrentItem(itemIndex, false, true);
                onSelected();
            }
        });
        strings = Arrays.asList(MINUTES);
        mMinuteAdapter = new CalendarTextAdapter(context, Arrays.asList(MINUTES), strings.indexOf(minute), 0, 0);
        minuteView.setVisibleItems(3);
        minuteView.setViewAdapter(mMinuteAdapter);
        minuteView.addClickingListener(new OnWheelClickedListener() {
            @Override
            public void onItemClicked(WheelView wheel, int itemIndex) {
                minuteView.setCurrentItem(itemIndex, false, true);
                onSelected();
            }
        });
        if (isEnglish) {
            strings = Arrays.asList(AM_EN);
        } else {
            strings = Arrays.asList(AM);
        }
        mAmdapter = new CalendarTextAdapter2(context, strings, strings.indexOf(am), 0, 0);
        amView.setCyclic(false);
        amView.setVisibleItems(3);
        amView.setViewAdapter(mAmdapter);
        amView.addClickingListener(new OnWheelClickedListener() {
            @Override
            public void onItemClicked(WheelView wheel, int itemIndex) {
                amView.setCurrentItem(itemIndex, false, true);
                onSelected();
            }
        });
    }

    public void setHour24(boolean hour24) {
        this.hour24 = hour24;
        if (hour24) {
            amView.setVisibility(GONE);
        } else {
            amView.setVisibility(VISIBLE);
        }
    }


    public class CalendarTextAdapter2 extends AbstractWheelTextAdapter1 {
        List<String> list;

        public CalendarTextAdapter2(Context context, List<String> list, int currentItem, int leftPadding, int rightPadding) {
            super(context, R.layout.ici_wheelview_am, NO_RESOURCE, currentItem, leftPadding, rightPadding);
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

    @Override
    public void onScrollingStarted(WheelView wheel) {

    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        onSelected();
    }


    public interface OnViewScrollListner {


    }
}