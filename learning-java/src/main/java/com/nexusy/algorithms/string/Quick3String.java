package com.nexusy.algorithms.string;

/**
 * @author lanhuidong
 * @since 2019-04-04
 */
public class Quick3String {

    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int gt = hi;
        int v = charAt(a[lo], d); //选取数组中第一个字符串的第一个字符
        int i = lo + 1;
        while (i <= gt) { // 处理数组中除第一个字符串之外的其他字符串
            int t = charAt(a[i], d);
            if (t < v) {
                exch(a, lt++, i++);
            } else if (t > v) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1, d); // 小于的部分排序
        if (v >= 0) {
            sort(a, lt, gt, d + 1); // 等于的部分排序
        }
        sort(a, gt + 1, hi, d); // 大于的部分排序
    }

    private static void exch(String[] a, int i, int j) {
        String tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        String[] a = {"edu.princeton.cs", "com.apple", "edu.princeton.cs", "com.cnn", "com.google", "edu.uva.cs",
            "edu.princeton.cs", "edu.princeton.cs.www", "edu.uva.cs", "edu.uva.cs", "edu.uva.cs", "com.adobe",
            "edu.princeton.ee"};
        sort(a);
        for (String s : a) {
            System.out.println(s);
        }
    }

}
