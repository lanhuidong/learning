package com.nexusy.mina.server;

import com.nexusy.mina.pack.Data;
import com.nexusy.mina.serializer.Serializer;
import com.nexusy.mina.serializer.SerializerFactory;
import com.nexusy.mina.vo.ValueObject;
import org.apache.mina.core.session.IoSession;

/**
 * @author lan
 * @since 2016-03-28
 */
public class Worker implements Runnable {

    private IoSession session;
    private Data pack;

    public Worker(IoSession session, Data pack) {
        this.session = session;
        this.pack = pack;
    }

    @Override
    public void run() {
        Serializer<ValueObject> serializer = SerializerFactory.createSerializer(pack.getContentType());
        ValueObject object = serializer.deserialize(pack, ValueObject.class);
        System.out.println(Thread.currentThread().getName() + " sleep " + object.getaLong() + "ms");
        try {
            Thread.sleep(object.getaLong());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        session.write(pack);
    }
}
