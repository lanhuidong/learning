package com.nexusy.spring.task;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 通过注解启用异步执行和调度,如果只需异步执行,那么添加@EnableAsync即可,反之亦然<br/><br/>
 *
 * @author lanhuidong
 * @see org.springframework.scheduling.annotation.SchedulingConfigurer
 * @see org.springframework.scheduling.annotation.AsyncConfigurer
 * @since 2016-01-16
 */
@Configuration
@ComponentScan(basePackages = "com.nexusy.spring.task")
@EnableAsync
@EnableScheduling
public class TaskAnnotationConfig {
}
