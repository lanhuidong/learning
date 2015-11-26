package com.nexusy.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author lan
 * @since 2015-10-08
 */
public class ByteBufDemo {

    public static void main(String[] args) {
        byte[] data = new byte[4];
        data[0] = 0;
        data[1] = 0;
        data[2] = 0;
        data[3] = -1;
        ByteBuf buf = Unpooled.copiedBuffer(data);
        System.out.println(buf.alloc());
        ByteBuf buf2 = buf.order(Unpooled.LITTLE_ENDIAN);
        System.out.println(buf2.unwrap());
    }
}
