package com.anle.test;

import com.anle.bean.Hello;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-08 16:18
 */
public class BeanFactoryAwareTest implements BeanFactoryAware {
    private BeanFactory beanFactory;

    /**
     * @Description 声明bean的时候，Spring会自动注入BeanFactory
     * @param beanFactory owning BeanFactory (never {@code null}). The bean can immediately call methods on the factory.
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        // 通过hello这个bean Id从beanFactory获取实例
        Hello hello = (Hello) beanFactory.getBean("hello");
        hello.say();
    }


}
