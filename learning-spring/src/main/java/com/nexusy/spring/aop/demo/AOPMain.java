package com.nexusy.spring.aop.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lan
 * @since 2016-04-28
 */
public class AOPMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
        FaceSDK faceSDK = ctx.getBean(FaceSDK.class);
        for (int i = 0; i < 100; i++) {
            faceSDK.detectFace();//只有spring IOC container管理的对象才能切入
        }
        FaceSDK.getFeture();
        FaceSDK faceSDK1 = new FaceSDK();
        faceSDK1.detectFace();
    }
}
