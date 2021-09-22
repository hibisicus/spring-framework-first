package com.anle.parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-18 17:47
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
