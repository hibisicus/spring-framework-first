package com.anle.entity;

import java.io.Serializable;

/**
 * 汽车测试FactoryBean
 *
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-22 23:53
 */
public class Car implements Serializable{
    private static final long serialVersionUID = 5663798047204837534L;
    private int maxSpeed;
    private String brand;
    private Double price;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
