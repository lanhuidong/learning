package com.nexusy.cglib;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.ImmutableBean;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author lanhuidong
 * @since 2016-04-23
 */
public class SampleBeanTest {

    @Test(expected = IllegalStateException.class)
    public void testImmutableBean() throws Exception {
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world!");
        SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean);
        Assert.assertEquals("Hello world!", immutableBean.getValue());
        //原来的bean可以修改,并且修改结果反馈到immutableBean上
        bean.setValue("Hello world, again!");
        Assert.assertEquals("Hello world, again!", immutableBean.getValue());
        //修改immutableBean会抛出异常
        immutableBean.setValue("Hello cglib!"); // Causes exception.
    }

    @Test
    public void testBeanGenerator() throws Exception {
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty("value", String.class);
        Object myBean = generator.create();
        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "Hello cglib!");
        Method getter = myBean.getClass().getMethod("getValue");
        Assert.assertEquals("Hello cglib!", getter.invoke(myBean));
    }
}
