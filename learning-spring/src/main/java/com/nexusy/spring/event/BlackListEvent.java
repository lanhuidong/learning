package com.nexusy.spring.event;

/**
 * 从Spring 4.2开始， 事件可以不用继承ApplicationEvent
 *
 * @author lan
 * @since 2016-05-03
 */
public class BlackListEvent {

    private String address;

    public BlackListEvent(Object source, String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ip: " + address + " trigger a event";
    }
}
