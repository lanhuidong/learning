package com.nexusy.java.v8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author lanhuidong
 * @since 2016-10-26
 */
public class CustomCollectorTest {

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> evens = Stream
                .generate(random::nextInt)
                .filter(i -> i % 2 == 0)
                .limit(10)
                .collect(new CustomCollector<>());
        evens.forEach(System.out::println);
    }

    static class CustomCollector<T> implements Collector<T, List<T>, List<T>> {

        @Override
        public Supplier<List<T>> supplier() {
            System.out.println("call supplier");
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            System.out.println("call accumulator");
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            System.out.println("call combiner");
            return (left, right) -> {
                left.addAll(right);
                return left;
            };
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            System.out.println("call finisher");
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }
}
