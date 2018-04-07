package com.nexusy.util;

import java.util.HashMap;

/**
 * @author lanhuidong
 * @since 2017-11-08
 */
public class HashMapTest {

    static class IntWrap {

        private int i;

        public IntWrap(int i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IntWrap intWrap = (IntWrap) o;

            return i == intWrap.i;
        }

        @Override
        public int hashCode() {
            int code = i;
            while (code > 11) {
                code = code - 12;
            }
            return code;
        }
    }

    public static void main(String[] args) {
        HashMap<IntWrap, IntWrap> map = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            IntWrap wrap = new IntWrap(i);
            map.put(wrap, wrap);
        }
    }

}
