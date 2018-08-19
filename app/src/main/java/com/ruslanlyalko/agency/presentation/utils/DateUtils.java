package com.ruslanlyalko.agency.presentation.utils;

import android.content.Context;

import com.ruslanlyalko.agency.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruslan Lyalko
 * on 17.08.2018.
 */
public class DateUtils {

    private static final String FORMAT_DATE = "EE, d MMM";
    private static final String FORMAT_DATE_FULL = "EEEE d MMM";
    private static final String FORMAT_TIME = "HH:mm";

    public static Date getDate(final Date date, final int year, final int month, final int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static String toStringDate(final Date date) {
        return new SimpleDateFormat(FORMAT_DATE, Locale.getDefault()).format(date);
    }

    public static String toStringTime(final Date date) {
        return new SimpleDateFormat(FORMAT_TIME, Locale.getDefault()).format(date);
    }

    public static Date getDate(final Date date, final int hours, final int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static String toStringDateTime(Context context, final Date date) {
        String day;
        if (isToday(date))
            day = context.getString(R.string.today);
        else if (isYesterday(date))
            day = context.getString(R.string.yesterday);
        else if (isTomorrow(date))
            day = context.getString(R.string.tomorrow);
        else day = new SimpleDateFormat(FORMAT_DATE_FULL, Locale.getDefault()).format(date);
        return String.format(Locale.US, "%s\n%s", day, toStringTime(date));
    }

    private static boolean isTomorrow(final Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, +1);
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == tomorrow.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == tomorrow.get(Calendar.DAY_OF_MONTH);
    }

    private static boolean isYesterday(final Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == yesterday.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == yesterday.get(Calendar.DAY_OF_MONTH);
    }

    private static boolean isToday(final Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH);
    }
}
