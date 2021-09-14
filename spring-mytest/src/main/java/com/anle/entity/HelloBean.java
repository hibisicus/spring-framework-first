package com.anle.entity;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-14 19:51
 */
public class HelloBean {
    private  String index;
    private String name;

    public HelloBean() {
        super();
    }

    public HelloBean(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
