package com.nexusy.java.v8;

import java.time.Instant;

/**
 * Instant代表时间线上的一个点，有“秒”和“纳秒”两个数字组成
 *
 * @author lan
 * @since 2016-10-19
 */
public class InstantTest {

    public static void main(String[] args) {
        Instant instant1 = Instant.ofEpochSecond(1);
        Instant instant2 = Instant.ofEpochSecond(1, 1_000_000_000);
        System.out.println(instant1);
        System.out.println(instant2);
    }
}
