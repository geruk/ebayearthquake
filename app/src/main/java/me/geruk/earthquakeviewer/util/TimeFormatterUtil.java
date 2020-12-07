package me.geruk.earthquakeviewer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatterUtil {
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static DateTimeFormatter formatter;

    // TODO: parse exception
    public static LocalDateTime convertTime(String time) {
        if (formatter == null) {
            formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        }
        return LocalDateTime.parse(time, formatter);
    }
}
