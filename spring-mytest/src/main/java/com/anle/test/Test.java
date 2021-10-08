package com.anle.test;

import com.anle.config.JavaConfig;
import com.anle.entity.GetBeanTest;
import com.anle.entity.TestChanegMethod;
import com.anle.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-02 10:47
 */
public class Test {
    public static void main(String[] args) {
        /*------------------2021-09-02 10:47------------------------*/
        // System.out.println("hah");
        // AnnotationConfigApplicationContext context = new
        // AnnotationConfigApplicationContext(JavaConfig.class);
        // User user = (User)context.getBean("user");
        // System.out.println(user.toString());
        /* ===========================End================================ */

        /*-------------2021-0913 19:12---------------------------------*/
        // ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("META_INF.spring/lookupTest.xml");
        // GetBeanTest test = (GetBeanTest) bf.getBean("getBeanTest");
        // test.showMe();
        /* ===========================End================================ */

        /*-------------2021-0913 2023---------------------------------*/
//        ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("META_INF/spring/lookupTest.xml");
//        TestChanegMethod test = (TestChanegMethod) bf.getBean("testChangeMethod");
//        test.changeMe();
        /* ===========================End================================ */

        /*-------------2021-1008 1631---------------------------------*/
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META_INF/spring/lookupTest.xml");
        TestBeanFactoryAware test = (TestBeanFactoryAware) ctx.getBean("testBeanFactoryAware");
        test.testAware();
        /* ===========================End================================ */

    }

}
