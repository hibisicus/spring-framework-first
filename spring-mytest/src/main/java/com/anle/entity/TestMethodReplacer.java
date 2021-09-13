package com.anle.entity;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-13 20:20
 */
public class TestMethodReplacer implements MethodReplacer {
    @Override public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("测试替换原有方法");
        return null;
    }
}
