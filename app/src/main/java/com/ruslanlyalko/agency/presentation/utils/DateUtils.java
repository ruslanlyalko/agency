package com.ruslanlyalko.agency.presentation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruslan Lyalko
 * on 17.08.2018.
 */
public class DateUtils {

    private static final String FORMAT_DATE = "MMM d";
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
        return new SimpleDateFormat(FORMAT_DATE, Locale.US).format(date);
    }

    public static String toStringTime(final Date date) {
        return new SimpleDateFormat(FORMAT_TIME, Locale.US).format(date);
    }

    public static Date getDate(final Date date, final int hours, final int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
