package com.anle.test;

import com.anle.service.impl.MethodInterceptorImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-18 11:01
 */
public class EnhancerDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback(new MethodInterceptorImpl());
        EnhancerDemo demo = (EnhancerDemo) enhancer.create();
        demo.test();
        System.out.println(demo);
    }

    public void test() {
        System.out.println("EnhancerDemo test()");
    }
}
