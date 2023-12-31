package com.anle.test;

import com.anle.handler.MyInvocationHandler;
import com.anle.service.UserService;
import com.anle.service.impl.UserServiceImpl;

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
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "classpath:META_INF/spring/BeanFactory.xml");
//        TestEvent event = new TestEvent("hello", "msg");
//        context.publishEvent(event);
        /* ===========================End================================ */

        /*-------------2021=1013-0958==测试Converter和ConversionService---------------------------------*/
//        DefaultConversionService conversionService = new DefaultConversionService();
//        conversionService.addConverter(new StringToPhoneNumberConverter());
//        String phoneNumberStr = "010-12345678";
//        PhoneNumberModel phoneNumber = conversionService.convert(phoneNumberStr, PhoneNumberModel.class);
//
//        Assert.assertEquals("010", phoneNumber.getAreaCode());
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
//        ApplicationContext bf = new ClassPathXmlApplicationContext("META_INF/spring/AspectAop.xml");
//        TestBeanAop bean = (TestBeanAop) bf.getBean("testBeanAop");
//        bean.test();
        /* ===========================End================================ */

        /*-------------2021=1014-1605==测试AOP对接口增强---------------------------------*/
        UserService userService = new UserServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) invocationHandler.getProxy();
        proxy.add();
        /* ===========================End================================ */

    }

}
