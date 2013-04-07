package com.webresponsive.ihahabits.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

import com.webresponsive.ihahabits.config.Constants;

public abstract class DateHelper
{
    private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(Constants.COMPARE_DATE_FORMAT);

    /**
     * Format the date to a string date.
     */
    public static String formatDate(Date date)
    {
        if (date != null)
        {
            return DATE_FORMATTER.format(date);
        }
        else
        {
            return "";
        }
    }

    /**
     * Parse the date coming from the database.
     */
    public static Date parseDate(String dateString)
    {
        Date parsedDate = null;
        if (dateString != null)
        {
            try
            {
                parsedDate = DATE_FORMATTER.parse(dateString.trim());
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
        }

        return parsedDate;
    }

    public static Date getToday()
    {
        DateTime dateTime = new DateTime();
        return dateTime.toDate();
    }

    public static Date getWeekAgo()
    {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.minusDays(7);
        return dateTime.toDate();
    }

    public static Date getMonthAgo()
    {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.minusMonths(1);
        return dateTime.toDate();
    }

    public static Date get3MonthsAgo()
    {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.minusMonths(3);
        return dateTime.toDate();
    }

    public static String getTodayString()
    {
        Date today = DateHelper.getToday();

        return formatDate(today);
    }

    public static String dayBefore(String dateString)
    {
        Date date = parseDate(dateString);
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.minusDays(1);

        return formatDate(dateTime.toDate());
    }

    public static String dayAfter(String dateString)
    {
        Date date = parseDate(dateString);
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusDays(1);

        return formatDate(dateTime.toDate());
    }

    public static boolean isToday(String dateString)
    {
        DateTime todayDateTime = new DateTime();

        return dateString.equals(formatDate(todayDateTime.toDate()));
    }
}
