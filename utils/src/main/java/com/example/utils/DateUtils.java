package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Класс со вспомогательными функциями для работы с датой.
 *
 * @author Aleksandr Brazhkin
 */
public class DateUtils {

    /**
     * Часовой пояс Москвы.
     * (Стандартный часовой пояс для приложения)
     */
    public static final TimeZone MOSCOW_TIME_ZONE = TimeZone.getTimeZone("GMT+3");
    /**
     * Российская локаль.
     * (Стандартная локаль для приложения)
     */
    public static final Locale RU_LOCALE = Locale.forLanguageTag("RU");

    public static Calendar newCalendar() {
        return Calendar.getInstance(MOSCOW_TIME_ZONE);
    }

    public static SimpleDateFormat newSimpleDateFormat(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, RU_LOCALE);
        simpleDateFormat.setTimeZone(MOSCOW_TIME_ZONE);
        return simpleDateFormat;
    }

    public static Date createTomorrow() {
        Calendar calendar = newCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date createToday() {
        Calendar calendar = newCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
