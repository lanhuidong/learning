package com.nexusy.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lanhuidong
 * @since 2017-11-02
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.nexusy.spring.aop"})
public class AopConfig {

}
