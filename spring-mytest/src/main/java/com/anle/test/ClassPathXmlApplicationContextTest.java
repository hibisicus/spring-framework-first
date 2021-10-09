package com.anle.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-08 22:47
 * @decription 测试ClassPathXmlApplicationContext
 */
public class ClassPathXmlApplicationContextTest extends ClassPathXmlApplicationContext {
    public ClassPathXmlApplicationContextTest(String... configLocations) throws BeansException {
        super(configLocations);
    }

    @Override
    protected void initPropertySources() {
        // super.initPropertySources();
        // 添加验证要求
        getEnvironment().setRequiredProperties("VAR");
    }

    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        super.setAllowBeanDefinitionOverriding(false); // 允许覆盖
        super.setAllowCircularReferences(false);    // 允许循环依赖
        super.customizeBeanFactory(beanFactory);
    }
}
