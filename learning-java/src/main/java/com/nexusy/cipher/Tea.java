package com.nexusy.cipher;

/**
 * 参考资料：<a href="https://en.wikipedia.org/wiki/Tiny_Encryption_Algorithm">Tiny Encryption Algorithm</a>
 * @author lanhuidong
 * @since 2017-05-22
 */
public class Tea {

    private final static long DELTA = 0x9e3779b9L;

    private static long getUnsignedInt(long data) {
        return data & 0x0FFFFFFFFL;
    }

    public static void encrypt(long[] v, long[] k) {
        long v0 = v[0], v1 = v[1];
        long sum = 0L;
        long k0 = k[0], k1 = k[1], k2 = k[2], k3 = k[3];
        for (int i = 0; i < 32; i++) {
            sum += DELTA;
            long t1 = ((v1 << 4) + k0) ^ (v1 + sum) ^ ((v1 >>> 5) + k1);
            v0 = getUnsignedInt(v0 + t1);
            long t2 = ((v0 << 4) + k2) ^ (v0 + sum) ^ ((v0 >>> 5) + k3);
            v1 = getUnsignedInt(v1 + t2);
        }
        v[0] = v0;
        v[1] = v1;
    }

    public static void decrypt(long[] v, long[] k) {
        long v0 = v[0], v1 = v[1];
        long sum = 0xC6EF3720L;
        long k0 = k[0], k1 = k[1], k2 = k[2], k3 = k[3];
        for (int i = 0; i < 32; i++) {
            long t1 = ((v0 << 4) + k2) ^ (v0 + sum) ^ ((v0 >>> 5) + k3);
            v1 = getUnsignedInt(v1 - t1);
            long t2 = ((v1 << 4) + k0) ^ (v1 + sum) ^ ((v1 >>> 5) + k1);
            v0 = getUnsignedInt(v0 - t2);
            sum -= DELTA;
        }
        v[0] = v0;
        v[1] = v1;
    }

    public static void main(String[] args) {
        long[] v = new long[2];
        v[0] = 0x20 & 0x0FFFFFFFFL;
        v[1] = 0x10 & 0x0FFFFFFFFL;
        long[] k = new long[4];
        k[0] = 0x4 & 0x0FFFFFFFFL;
        k[1] = 0x3 & 0x0FFFFFFFFL;
        k[2] = 0x2 & 0x0FFFFFFFFL;
        k[3] = 0x1 & 0x0FFFFFFFFL;
        System.out.print(Long.toHexString(getUnsignedInt(v[0])));
        System.out.println(Long.toHexString(getUnsignedInt(v[1])));
        encrypt(v, k);
        System.out.printf("%8X", v[0]);
        System.out.printf("%8X\n", v[1]);
        decrypt(v, k);
        System.out.print(Long.toHexString(getUnsignedInt(v[0])));
        System.out.println(Long.toHexString(getUnsignedInt(v[1])));
        System.out.print(Long.toHexString(k[0]));
        System.out.print(Long.toHexString(k[1]));
        System.out.print(Long.toHexString(k[2]));
        System.out.println(Long.toHexString(k[3]));
    }
}
