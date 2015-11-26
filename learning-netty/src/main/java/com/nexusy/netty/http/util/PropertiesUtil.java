package com.nexusy.netty.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lanhuidong
 * @since 2015-08-11
 */
public class PropertiesUtil {

    private static Properties props = new Properties();

    static {
        try (InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("server.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getProperty(String key, int defaultValue) {
        String prop = props.getProperty(key);
        int value = defaultValue;
        if (prop != null) {
            try {
                value = Integer.valueOf(prop);
            } catch (NumberFormatException ignored) {
            }
        }
        return value;
    }
}
