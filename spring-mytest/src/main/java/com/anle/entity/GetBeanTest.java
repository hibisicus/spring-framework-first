package com.anle.entity;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-13 19:10
 */
public abstract class GetBeanTest {

    public void showMe() {
        this.getBean().showMe();
    }

    public abstract User getBean();
}
