package com.anle.parse;

import com.anle.entity.User;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-18 17:31
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    //        Element对应的类
    protected Class getBeanClass(Element element) {
        return User.class;
    }

    //    从element中解析并提取对应的元素
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");
//      将提取的数据放到BeanDefinitionBuilder中，待到完成所有bean的解析之后统一注册到beanFactory中
        if(StringUtils.hasText(userName)){
            bean.addPropertyValue("userName",userName);
        }
        if(StringUtils.hasText(email)){
            bean.addPropertyValue("email",email);
        }
    }
}
