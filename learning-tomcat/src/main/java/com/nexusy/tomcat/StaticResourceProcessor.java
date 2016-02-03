package com.nexusy.tomcat;

import java.io.IOException;

/**
 * @author lan
 * @since 2016-02-03
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
