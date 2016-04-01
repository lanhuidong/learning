package com.nexusy.mina.client;

import com.nexusy.mina.client.pool.ConnectorPool;
import com.nexusy.mina.pack.Data;
import com.nexusy.mina.serializer.Serializer;
import com.nexusy.mina.serializer.SerializerFactory;
import com.nexusy.mina.vo.ValueObject;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class MinaClient {

    public static final AttributeKey OBJECT = new AttributeKey(MinaClient.class, "STATE");

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            ValueObject object = ValueObject.makeObject();
            Serializer<ValueObject> serializer = SerializerFactory.createSerializer(1);
            Data data = serializer.serialize(object, ValueObject.class);
            data.setOpCode(1);
            try {
                IoSession session = ConnectorPool.getInstance().borrowObject();
                session.setAttribute(OBJECT, object);
                session.write(data);
                System.out.println(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
