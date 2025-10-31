package com.test.springMongo.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public final static String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz";

    public static Instant stringToInstant(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.FRANCE);
        return LocalDateTime.parse(date, dateTimeFormatter).toInstant(ZoneOffset.UTC);
    }

    public static Instant getCurentInstantInZone() {
        Date date = new Date();
        ZonedDateTime utc = date.toInstant().atZone(ZoneOffset.UTC);
        ZonedDateTime paris = utc.withZoneSameInstant(ZoneId.of("Europe/Paris"));
        return paris.toInstant();
    }

    public static LocalDateTime instantToLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public static String instantToString(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toString();
    }

    public static LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    public static String getCurrentDateToString() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(pattern);
        return myDateObj.format(myFormatObj);
    }

    public static String getDateToString(LocalDateTime myDateObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(pattern);
        return myDateObj.format(myFormatObj);
    }

    public static LocalDateTime getDateToLocalDateTime(String myDateObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(myDateObj, myFormatObj);
    }
}
