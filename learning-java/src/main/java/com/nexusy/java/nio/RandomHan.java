package com.nexusy.java.nio;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * GB2312高位范围0xB0-0xF7，低位范围0xA1-0xFE
 *
 * @author lan
 * @since 2016-05-16
 */
public class RandomHan {

    private static final int lowRange = 0xFE - 0xA1 + 1;
    private static final int highRange = 0xF7 - 0xB0 + 1;
    private static Random random = new Random();

    public static byte getLowByte() {
        return (byte) (0xA1 + random.nextInt(lowRange));
    }

    public static byte getHighByte() {
        return (byte) (0xB0 + random.nextInt(highRange));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        for (int i = 0; i < 1000; i++) {
            byte[] data = new byte[2];
            data[0] = getHighByte();
            data[1] = getLowByte();
            System.out.print(new String(data, "GB2312"));
            if (i % 2 == 1) {
                System.out.println();
            }
        }
    }
}
