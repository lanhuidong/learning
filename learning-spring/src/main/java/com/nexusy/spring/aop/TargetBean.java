package com.nexusy.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author lanhuidong
 * @since 2017-11-02
 */
@Component
public class TargetBean {

    public void run(int i) {
        System.out.println("TargetBean.run(" + i + ")");
    }

}
