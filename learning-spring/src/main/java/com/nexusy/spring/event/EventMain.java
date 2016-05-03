package com.nexusy.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lan
 * @since 2016-05-03
 */
public class EventMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-event.xml");
        EmailService emailService = ctx.getBean(EmailService.class);
        emailService.sendEmail();
    }
}
