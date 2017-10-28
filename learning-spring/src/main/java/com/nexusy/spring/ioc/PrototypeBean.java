package com.nexusy.spring.ioc;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lanhuidong
 * @since 2017-10-28
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean {

    @PostConstruct
    public void init() {
        System.out.println("prototype bean init");
    }

    /**
     * prototype类型的bean的销毁方法不会被spring容器调用
     */
    @PreDestroy
    public void destroy() {
        System.out.println("prototype bean destroy");
    }

}
