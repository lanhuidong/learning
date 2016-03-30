package com.nexusy.mina.client;

import com.nexusy.mina.json.JsonMapper;
import com.nexusy.mina.pack.Data;
import com.nexusy.mina.vo.ValueObject;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class ClientHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        Data jsonPack = (Data) message;
        ValueObject object1 = JsonMapper.readValue(jsonPack.getContent(), ValueObject.class);
        ValueObject object2 = (ValueObject) session.getAttribute(MinaClient.OBJECT);
        if (object1.getaLong() != object2.getaLong()) {
            System.err.println("object1:" + object1.getaLong() + ", object2:" + object2.getaLong());
        }
        System.out.println(object1.getaLong());
        session.closeOnFlush();
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.closeNow();
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.closeOnFlush();
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        session.getService().dispose();
    }
}
