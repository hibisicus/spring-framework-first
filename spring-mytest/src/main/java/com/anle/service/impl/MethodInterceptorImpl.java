package com.anle.service.impl;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-18 11:04
 */
public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before invoke " + method);
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("After invoke " + method);
        return result;
    }
}
