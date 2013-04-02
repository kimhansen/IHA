package com.webresponsive.ihahabits.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    /**
     * Parse the date coming from the database.
     */
    public static Date parseHabitDate(String dateString)
    {
        Date parsedDate = null;
        if (dateString != null)
        {
            // FIXME
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

    /**
     * Create a calendar with todays date and compare the date, month and year
     * with the given date.
     */
    public static boolean isToday(Date date)
    {
        Calendar today = Calendar.getInstance();
        return (date.getDate() == today.get(Calendar.DATE)) && (date.getMonth() == today.get(Calendar.MONTH))
                && ((date.getYear() + 1900) == today.get(Calendar.YEAR));
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
}
