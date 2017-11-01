package com.nexusy.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lanhuidong
 * @since 2017-10-28
 */
public class PropertyEditorTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("property-editor-test.xml");
        Server server = ctx.getBean(Server.class);
        System.out.println(server.getSocketAddress());
    }

}
