package com.nexusy.spring.ioc.beanpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanPostProcessor接口允许在Bean初始化前后插入回调方法：
 * 1.BeanPostProcessor只能作用于同一个Container中的Bean，比如Web的ApplicationContext和它的父ApplicationContext
 * 2.当存在多个BeanPostProcessor时，可以通过实现Ordered接口来指定顺序
 * 3.当通过ConfigurableBeanFactory#addBeanPostProcessor方法添加时，这Ordered接口被忽略，添加的顺序即为执行顺序
 *
 * 该接口可用于创建AOP代理等
 *
 * @author lan
 * @since 2016-05-03
 */
public class BeanPostProcessorMain {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
    }
}
