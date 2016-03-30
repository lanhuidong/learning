package com.nexusy.mina.server;

import com.nexusy.mina.pack.Data;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author lanhuidong
 * @since 2016-03-12
 */
public class ServerHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        Data jsonPack = (Data) message;
        new Thread(new Worker(session, jsonPack)).start();
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("Idle: " + session.getIdleCount(status));
    }
}
