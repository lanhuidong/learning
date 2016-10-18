package com.nexusy.java.v8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * @author lanhuidong
 * @since 2016-10-18
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2016, 10, 18);
        int year = localDate.getYear();
        System.out.println("year: " + year);
        Month month = localDate.getMonth();
        System.out.println("month: " + month);
        int day = localDate.getDayOfMonth();
        System.out.println("day: " + day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println("day of week: " + dayOfWeek);
        int len = localDate.lengthOfMonth();
        System.out.println("month length: " + len);
        boolean leap = localDate.isLeapYear();
        System.out.println("Is leap year: " + leap);

        LocalDate today = LocalDate.now();
        System.out.println("today is: " + today);

        year = today.get(ChronoField.YEAR);
        System.out.println("year: " + year);

        LocalDate dateFromString = LocalDate.parse("2018-09-09");
        System.out.println(dateFromString);

    }

}
