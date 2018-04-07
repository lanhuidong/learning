package com.nexusy.java.v8;

import java.util.stream.Stream;

/**
 * @author lanhuidong
 * @since 2018-03-27
 */
public class FibonacciStream {

    public static void main(String[] args) {
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(20)
                .map(arr -> arr[0])
                .forEach(System.out::println);
    }

}
