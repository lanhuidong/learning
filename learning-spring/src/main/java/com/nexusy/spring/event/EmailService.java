package com.nexusy.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author lan
 * @since 2016-05-03
 */
public class EmailService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendEmail() {
        BlackListEvent event = new BlackListEvent(this, "10.12.13.110");
        publisher.publishEvent(event);
    }
}
