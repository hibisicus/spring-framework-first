package com.anle.handler;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-14 15:55
 */
public class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    /**
     * 构造函数,将代理的对象传入
     * @param target
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * @Description 执行目标对象的方法
     * @param o
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     * @Description 此方法实现了AOP增强的所有逻辑
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        // 在执行方法之前打印
        System.out.println("================before=====================");

        Object result = method.invoke(target, objects);
        // 执行方法之后
        System.out.println("================after=====================");

        return result;
    }

    /**
     * 获取目标对象的代理对象
     * 此方法千篇一律，但是必不可少
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(),
                this);
    }
}
