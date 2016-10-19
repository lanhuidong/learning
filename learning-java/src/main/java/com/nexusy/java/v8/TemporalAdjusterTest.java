package com.nexusy.java.v8;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * @author lan
 * @since 2016-10-19
 */
public class TemporalAdjusterTest {

    public static void main(String[] args){
        LocalDate today = LocalDate.now();
        LocalDate sunday = today.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(sunday);
        LocalDate workingDay = today.with(new NextWorkingDay());
        System.out.println(workingDay);
    }

}
