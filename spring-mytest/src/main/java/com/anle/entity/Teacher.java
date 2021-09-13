package com.anle.entity;

import org.springframework.stereotype.Component;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-13 19:14
 */
public class Teacher extends User{
    @Override public void showMe() {
        System.out.println("Teacher----test");
    }
}
