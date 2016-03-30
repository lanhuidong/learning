package com.nexusy.mina.client;

import com.nexusy.mina.client.pool.ConnectorPool;
import com.nexusy.mina.client.pool.ConnectorPoolFactory;
import com.nexusy.mina.json.JsonMapper;
import com.nexusy.mina.pack.Data;
import com.nexusy.mina.vo.ValueObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
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
            Data jsonData = new Data();
            jsonData.setContentType(0);
            jsonData.setOpCode(1);
            ValueObject object = ValueObject.makeObject();
            byte[] content = JsonMapper.writeValueAsBytes(object);
            jsonData.setContent(content);
            try {
                IoSession session = ConnectorPool.getInstance().borrowObject();
                session.setAttribute(OBJECT, object);
                session.write(jsonData);
                System.out.println(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
