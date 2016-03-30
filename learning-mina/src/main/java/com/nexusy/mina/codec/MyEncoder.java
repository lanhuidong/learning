package com.nexusy.mina.codec;

import com.nexusy.mina.pack.Data;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class MyEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        Data pack = (Data) message;
        byte[] data = pack.getContent();
        int length = data.length;
        IoBuffer buffer = IoBuffer.allocate(12 + length);
        buffer.putInt(pack.getContentType());
        buffer.putInt(pack.getOpCode());
        buffer.putInt(length);
        buffer.put(data);
        buffer.flip();
        out.write(buffer);
    }
}
