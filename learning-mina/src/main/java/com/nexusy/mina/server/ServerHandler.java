package com.nexusy.mina.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

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
        String msg = message.toString();
        if ("quit".equals(msg.trim())) {
            session.closeOnFlush();
            return;
        }
        Date date = new Date();
        session.write(date.toString());
        System.out.println("message written...");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("Idle: " + session.getIdleCount(status));
    }
}
