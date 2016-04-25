package com.nexusy.spring.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lan
 * @since 2016-04-25
 */
public class CacheMain {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-cache.xml");
        FaceService service = ctx.getBean(FaceService.class);
        service.loadAllFaces();
        service.loadAllFaces();//该方法调用从缓存中获取返回值
        service.loadFaceById(1);
        service.loadFaceById(1);//该方法调用从缓存中获取返回值
        service.loadFaceById(2);
    }
}
