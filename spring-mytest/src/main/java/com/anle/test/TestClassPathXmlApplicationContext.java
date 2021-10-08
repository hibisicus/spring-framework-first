package com.anle.test;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-08 22:47
 */
public class TestClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {
    public TestClassPathXmlApplicationContext(String... configLocations) throws BeansException {
        super(configLocations);
    }

    @Override
    protected void initPropertySources() {
//        super.initPropertySources();
//        添加验证要求
        getEnvironment().setRequiredProperties("VAR");
    }
}
