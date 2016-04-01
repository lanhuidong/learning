package com.nexusy.mina.client;

import com.nexusy.mina.client.pool.ConnectorPool;
import com.nexusy.mina.pack.Data;
import com.nexusy.mina.serializer.Serializer;
import com.nexusy.mina.serializer.SerializerFactory;
import com.nexusy.mina.vo.ValueObject;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class ClientHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        Data jsonPack = (Data) message;
        Serializer<ValueObject> serializer = SerializerFactory.createSerializer(jsonPack.getContentType());
        ValueObject object1 = serializer.deserialize(jsonPack, ValueObject.class);
        ValueObject object2 = (ValueObject) session.getAttribute(MinaClient.OBJECT);
        if (object1.getaLong() != object2.getaLong()) {
            System.err.println("object1:" + object1.getaLong() + ", object2:" + object2.getaLong());
        }
        System.out.println(new Date().toString() + ":" + object1.getaLong());
        ConnectorPool.getInstance().returnObject(session);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        CloseFuture future = session.closeNow();
        future.awaitUninterruptibly();
        ConnectorPool.getInstance().returnObject(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        CloseFuture future = session.closeOnFlush();
        future.awaitUninterruptibly();
        ConnectorPool.getInstance().returnObject(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {

    }
}
