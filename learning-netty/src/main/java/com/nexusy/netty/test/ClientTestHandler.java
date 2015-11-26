package com.nexusy.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * @author lan
 * @since 2015-09-30
 */
public class ClientTestHandler extends ChannelInboundHandlerAdapter {

    private static int capacity = 5 * 1024;
    private static byte[] data = new byte[capacity];
    private int readBytes = 0;

    static {
        for (int i = 0; i < capacity; i++) {
            data[i] = (byte) i;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf buf = ctx.alloc().buffer(capacity);
        buf.writeBytes(data);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        readBytes += buf.readableBytes();
        buf.release();
        if (readBytes == capacity) {
        System.out.println(readBytes);
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}