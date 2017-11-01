package com.nexusy.spring.ioc;

import java.beans.PropertyEditorSupport;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * 将字符串host:port转换成SocketAddress对象
 *
 * @author lanhuidong
 * @since 2017-11-01
 */
public class SocketAddressEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] array = text.split(":");
        if (array.length == 2) {
            try {

                Integer port = Integer.valueOf(array[1]);
                SocketAddress socketAddress = new InetSocketAddress(array[0], port);
                setValue(socketAddress);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid socket address value [" + text + "]");
            }
        } else {
            throw new IllegalArgumentException("Invalid socket address value [" + text + "]");
        }
    }

}
