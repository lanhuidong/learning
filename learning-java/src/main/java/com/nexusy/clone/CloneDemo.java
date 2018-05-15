package com.nexusy.clone;

/**
 * 《Effiective Java 3rd》Item 13提到clone方法最吸引人的地方是复制数组
 *
 * @author lanhuidong
 * @since 2018-05-15
 */
public class CloneDemo {

    private static final int array[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            arrayCopy();
            cloneCopy();
        }
        for (int i = 0; i < 1000000; i++) {
            cloneCopy();
            arrayCopy();
        }

        long t0 = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            arrayCopy();
        }

        long t1 = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            cloneCopy();
        }
        long t2 = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            arrayCopy();
        }
        long t3 = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            cloneCopy();
        }
        long t4 = System.nanoTime();

        System.out.println(t1 - t0);
        System.out.println(t2 - t1);
        System.out.println(t3 - t2);
        System.out.println(t4 - t3);
    }

    private static int[] arrayCopy() {
        int[] tmp = new int[10];
        System.arraycopy(array, 0, tmp, 0, 10);
        return tmp;
    }

    private static int[] cloneCopy() {
        return array.clone();
    }

}
