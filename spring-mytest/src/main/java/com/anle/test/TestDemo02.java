package com.anle.test;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.xml.BeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-09 17:50
 */
public class TestDemo02 extends DefaultBeanDefinitionDocumentReader {
    @Override public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
            throws BeanDefinitionStoreException {
        System.out.println("测试");
    }
    @Override
    protected void preProcessXml(Element root) {
        System.out.println("解析前处理");
    }

    @Override protected void postProcessXml(Element root) {
        System.out.println("解析后处理");
    }
}
