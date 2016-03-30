package com.nexusy.mina.client;

import com.nexusy.mina.codec.MyCodecFactory;
import com.nexusy.mina.json.JsonMapper;
import com.nexusy.mina.pack.Data;
import com.nexusy.mina.vo.ValueObject;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class MinaClient {

    public static final AttributeKey OBJECT = new AttributeKey(MinaClient.class, "STATE");

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            IoConnector connector = new NioSocketConnector();
            connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyCodecFactory()));
            connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 50000);
            connector.setConnectTimeoutMillis(50 * 1000);
            connector.setHandler(new ClientHandler());
            ConnectFuture future = connector.connect(new InetSocketAddress(7777));
            future.awaitUninterruptibly();
            if (future.isDone() && !future.isConnected()) {
                connector.dispose();
            } else {
                IoSession session = future.getSession();
                Data jsonData = new Data();
                jsonData.setContentType(0);
                jsonData.setOpCode(1);
                ValueObject object = ValueObject.makeObject();
                byte[] content = JsonMapper.writeValueAsBytes(object);
                jsonData.setContent(content);
                session.setAttribute(OBJECT, object);
                session.write(jsonData);
            }
        }
    }
}
