package com.nexusy.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lan
 * @since 2015-09-30
 */
public class NioTestServer {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(16);
    private Channel channel;

    public void run(int port) throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerTestHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        ChannelFuture f = b.bind(port).sync();
        f.channel().closeFuture().sync();
        channel = f.channel();
        channel.closeFuture().sync();
    }

    public void destory() {
        if (channel != null) {
            channel.close();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        final NioTestServer server = new NioTestServer();
        server.run(7777);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.destory();
            }
        });
    }
}
