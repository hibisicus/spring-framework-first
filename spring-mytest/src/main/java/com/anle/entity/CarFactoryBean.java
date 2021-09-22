package com.anle.entity;

import org.springframework.beans.factory.FactoryBean;

/**
 * car factoryBean
 *
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-23 00:01
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;

    @Override public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.parseInt(infos[1]));
        car.setPrice(Double.parseDouble(infos[2]));

        return null;
    }

    @Override public Class<Car> getObjectType() {
        return Car.class;
    }

    @Override public boolean isSingleton() {
        return false;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
