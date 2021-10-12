package com.anle.test;

import com.anle.bean.UserManager;
import com.anle.event.TestEvent;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

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
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META_INF/spring/lookupTest.xml");
//        BeanFactoryAwareTest test = (BeanFactoryAwareTest) ctx.getBean("testBeanFactoryAware");
//        test.testAware();
        /* ===========================End================================ */
        /*-------------2021-1009 1619---------------------------------*/
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META_INF/spring/lookupTest.xml");
//        UserManager userManager = (UserManager) ctx.getBean("userManager");
//        System.out.println(userManager);

        /* ===========================End================================ */
        /*-------------2021=1011-1719==屏蔽部分属性---------------------------------*/
//        ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("META_INF/spring/BeanFactory.xml"));
//        BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) bf.getBean("bfpp");
//        bfpp.postProcessBeanFactory(bf);
//        System.out.println(bf.getBean("simpleBean"));

        /* ===========================End================================ */
        /*-------------2021=1012-1646==Spring事件监听---------------------------------*/
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:META_INF/spring/BeanFactory.xml");
        TestEvent event = new TestEvent("hello", "msg");
        context.publishEvent(event);
        /* ===========================End================================ */

    }

}
