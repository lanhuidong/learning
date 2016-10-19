package com.nexusy.java.v8;

import java.time.*;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * @author lan
 * @since 2016-10-19
 */
public class DurationTest {

    public static void main(String[] args) {
        LocalDateTime birthDay = LocalDateTime.of(2016, 9, 9, 1, 42);
        LocalDateTime today = LocalDateTime.now();
        Duration duration = Duration.between(birthDay, today);
        System.out.println(duration.toHours());

        try {
            LocalDate localDate1 = LocalDate.MIN;
            LocalDate localDate2 = LocalDate.MAX;
            Period period = Period.between(localDate1, localDate2);
            System.out.println(period.getYears());
            Duration.between(localDate1, localDate2);
        } catch (UnsupportedTemporalTypeException e) {
            System.out.println("Duration not support LocalDate!");
        }

        try {
            Instant instant = Instant.ofEpochSecond(3);
            Duration.between(instant, today);
        } catch (DateTimeException e) {
            System.out.println("Duration can't mixed Instant and LocalDateTime.");
        }

        LocalTime localTime = LocalTime.now();
        Duration d1 = Duration.between(localTime, birthDay);
        System.out.println(d1.toHours());

    }
}
