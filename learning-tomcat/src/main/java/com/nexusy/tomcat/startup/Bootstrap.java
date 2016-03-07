package com.nexusy.tomcat.startup;

import com.nexusy.tomcat.connector.http.HttpConnector;

/**
 * @author lanhuidong
 * @since 2016-02-07
 */
public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
