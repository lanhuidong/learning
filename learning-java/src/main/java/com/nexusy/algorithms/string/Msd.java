package com.nexusy.algorithms.string;

/**
 * @author lanhuidong
 * @since 2019-04-02
 */
public class Msd {

    private static final int radix = 256;
    private static final int m = 15;
    private static String[] aux;

    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }

    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n - 1, 0);
    }

    private static void insertionSort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                String tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (lo + m >= hi) { //小数组采用插入排序，避免初始化count数组的开销
            insertionSort(a, lo, hi, d);
            return;
        }
        int[] count = new int[radix + 2];
        for (int i = lo; i <= hi; i++) { //计算频率
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < radix + 1; r++) { //转换成索引
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) { //在辅助数组中排序
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) { //拷贝回原数组
            a[i] = aux[i - lo];
        }
        for (int r = 0; r < radix; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    public static void main(String[] args) {
        String[] a = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        sort(a);
        for (String s : a) {
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }
}
