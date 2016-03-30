package com.nexusy.mina.codec;

import com.nexusy.mina.pack.Data;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class MyDecoder extends CumulativeProtocolDecoder {

    private static final AttributeKey DECODER_STATE_KEY = new AttributeKey(MyDecoder.class, "STATE");

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        Data pack = (Data) session.getAttribute(DECODER_STATE_KEY);
        if (pack == null) {
            pack = new Data();
            session.setAttribute(DECODER_STATE_KEY, pack);
        }
        if (pack.getContentType() == null) {
            int remaining = in.remaining();
            if (remaining < 8) {
                return false;
            }
            int contentType = in.getInt();
            int opCode = in.getInt();
            pack.setContentType(contentType);
            pack.setOpCode(opCode);
        }
        if (in.prefixedDataAvailable(4)) {
            int length = in.getInt();
            pack.setLength(length);
            byte[] content = new byte[length];
            in.get(content);
            pack.setContent(content);
            out.write(pack);
            session.removeAttribute(DECODER_STATE_KEY);
            return true;
        }
        return false;
    }

}
