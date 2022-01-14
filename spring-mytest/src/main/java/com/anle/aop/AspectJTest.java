//package com.anle.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//
///**
// * @author AnnLee
// * @version 1.0
// * @date 2021-10-13 10:48
// */
//@Aspect
//public class AspectJTest {
//    @Pointcut("execution(* com.anle.bean.*.*(..))")
//    public void test() {}
//
//    @Before("test()")
//    public void beforeTest() {
//        System.out.println("beforeTest");
//    }
//
//    @After("test()")
//    public void afterTest() {
//        System.out.println("afterTest");
//    }
//
//    @Around("test()")
//    public Object aroundTest(ProceedingJoinPoint point) {
//        System.out.println("before1");
//        Object o = null;
//        try {
//            o = point.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        System.out.println("after1");
//        return o;
//    }
//}
