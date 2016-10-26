package com.nexusy.java.v8;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author lanhuidong
 * @since 2016-10-26
 */
public class CustomSpliteratorTest {

    public static void main(String[] args) {
        String sentence = "Do not for one repulse forgo the purpose that you resolved to effort";
        Spliterator<Character> spliterator = new CustomSpliterator(sentence);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println(countWords(stream));
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCount();
    }

    static class WordCounter {

        private final int count;
        private final boolean lastspace;

        public WordCounter(int count, boolean lastspace) {
            this.count = count;
            this.lastspace = lastspace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastspace ? this : new WordCounter(count, true);
            } else {
                return lastspace ? new WordCounter(count + 1, false) : this;
            }
        }

        public WordCounter combine(WordCounter counter) {
            return new WordCounter(count + counter.count, counter.lastspace);
        }

        public int getCount() {
            return count;
        }
    }

    static class CustomSpliterator implements Spliterator<Character> {

        private final String string;
        private int currentChar = 0;

        public CustomSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if (currentSize < 10) {
                return null;
            }

            for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    Spliterator<Character> spliterator = new CustomSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }

            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
}
