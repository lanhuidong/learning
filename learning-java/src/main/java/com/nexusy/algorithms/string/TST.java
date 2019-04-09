package com.nexusy.algorithms.string;

/**
 * @author lanhuidong
 * @since 2019-04-09
 */
public class TST<V> {

    private Node root;

    private class Node {
        private char c;
        private Node left;
        private Node mid;
        private Node right;
        private V val;
    }

    public V get(String key) {
        Node x = get(root, key, 0);
        return x == null ? null : x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    public void put(String key, V val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, V val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) { //还没有到最后一个字符
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;
        }
        return x;
    }

    public static void main(String[] args) {
        TST<String> tst = new TST<>();
        tst.put("she", "xx1");
        tst.put("shu", "xx2");
        tst.put("shx", "xx3");
        tst.put("shh", "xx4");
        System.out.println(tst.get("she"));
        System.out.println(tst.get("shu"));
        System.out.println(tst.get("shx"));
        System.out.println(tst.get("shh"));
    }
}
