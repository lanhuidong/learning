package com.nexusy.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * default-autowire-candidates和autowire-candidate只会影响基于类型的自动装配
 *
 * @author lanhuidong
 * @since 2017-10-28
 */
public class AutowireTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("autowire-test.xml");
        Foo foo = ctx.getBean(Foo.class);
        System.out.println(foo.getBar());
    }

}
