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
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(),
                this);
    }
}
