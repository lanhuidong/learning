package com.nexusy.java.v8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author lan
 * @since 2016-10-19
 */
public class ZoneIdTest {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneBeijing = ZoneId.of("Asia/Shanghai");
        ZonedDateTime beijingTime = localDateTime.atZone(zoneBeijing);
        System.out.println(beijingTime);
        System.out.println(beijingTime.withZoneSameLocal(ZoneId.of("America/Los_Angeles")));
    }
}
