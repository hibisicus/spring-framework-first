package com.anle.bean;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-13 10:46
 * @Description 测试AOP
 */
public class TestBeanAop {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        System.out.println("test");
    }
}
