package com.nexusy.spring.event;

import org.springframework.context.event.EventListener;

/**
 * 从Spring 4.2开始，监听器可以不用实现ApplicationListener接口
 *
 * @author lan
 * @since 2016-05-03
 */
public class BlackListNotifier {

    @EventListener
    public void onApplicationEvent(BlackListEvent event) {
        System.out.println(event);
    }
}
