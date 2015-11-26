package com.nexusy.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lan
 * @since 2015-09-30
 */
public class NioTestClient {

    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        int threadNum = Integer.parseInt(args[2]);
        int total = Integer.parseInt(args[3]);
        EventLoopGroup workerGroup = new NioEventLoopGroup(threadNum);

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientTestHandler());
                }
            });
            List<ChannelFuture> futures = new ArrayList<>(total);
            long startTime = System.nanoTime();
            for (int i = 0; i < total; i++) {
                ChannelFuture f = b.connect(host, port).sync();
                futures.add(f);
            }
            for (ChannelFuture f : futures) {
                f.channel().closeFuture().sync();
            }
            long endTime = System.nanoTime();
            System.out.println((endTime - startTime) + "ns");
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}