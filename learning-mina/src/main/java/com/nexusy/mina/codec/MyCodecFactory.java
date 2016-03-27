package com.nexusy.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class MyCodecFactory implements ProtocolCodecFactory {

    private ProtocolEncoder encoder = new MyEncoder();
    private ProtocolDecoder decoder = new MyDecoder();

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }

}
