package com.nexusy.java.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author lanhuidong
 * @since 2016-10-11
 */
public class StreamMain {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World");
        List<String[]> c = words.stream().map(word -> word.split("")).distinct().collect(toList());
        System.out.println(c);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.stream().reduce(Integer::min).ifPresent(System.out::println);
    }
}
