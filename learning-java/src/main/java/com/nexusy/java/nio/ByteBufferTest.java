package com.nexusy.java.nio;

import java.nio.ByteBuffer;

/**
 * @author lan
 * @since 2016-05-16
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(28);
//        System.out.println(buffer.get());
        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');
        buffer.put((byte) 'd');
        buffer.put((byte) 'e');
        buffer.rewind();
        System.out.println(buffer.get());
    }
}
