package com.nexusy.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lanhuidong
 * @since 2017-11-02
 */
public class AopTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AopConfig.class);
        TargetBean targetBean = ctx.getBean(TargetBean.class);
        targetBean.run();
    }

}
