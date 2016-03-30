package com.nexusy.mina.client.pool;

import com.nexusy.mina.client.ClientHandler;
import com.nexusy.mina.codec.MyCodecFactory;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Date;

/**
 * @author lan
 * @since 2016-03-30
 */
public class ConnectorPoolFactory extends BasePooledObjectFactory<IoSession> {

    @Override
    public IoSession create() throws Exception {
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyCodecFactory()));
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 50000);
        connector.setConnectTimeoutMillis(50 * 1000);
        connector.setHandler(new ClientHandler());
        ConnectFuture future = connector.connect(new InetSocketAddress(7777));
        future.awaitUninterruptibly();
        if (future.isDone() && !future.isConnected()) {
            connector.dispose();
            return null;
        } else {
            return future.getSession();
        }
    }

    @Override
    public PooledObject<IoSession> wrap(IoSession obj) {
        return new DefaultPooledObject<>(obj);
    }

    @Override
    public boolean validateObject(PooledObject<IoSession> p) {
        IoSession session = p.getObject();
        System.out.println("validate:" + session.isClosing());
        return !session.isClosing();
    }

    @Override
    public void destroyObject(PooledObject<IoSession> p) throws Exception {
        IoSession session = p.getObject();
        session.getService().dispose();
        System.out.println("dispose connector:" + new Date());
    }
}
