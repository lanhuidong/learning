package com.nexusy.java.v8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lan
 * @since 2016-10-19
 */
public class DateTimeFormatterTest {

    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(today.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        LocalDate day = LocalDate.parse("2016-09-09", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(day);
    }
}
