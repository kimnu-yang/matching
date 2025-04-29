package com.matching.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatUtils {
    private FormatUtils() {}

    // String to LocalDate
    public static LocalDate strToDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, dateFormatter);
    }

    public static LocalDate strToDate(String date, String format) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, dateFormatter);
    }

    // String to LocalDatetime
    public static LocalDateTime strToDateTime(String datetime) {
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return LocalDateTime.parse(datetime, datetimeFormatter);
    }

    public static LocalDateTime strToDateTime(String datetime, String format) {
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(datetime, datetimeFormatter);
    }
}
