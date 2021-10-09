package com.anle.bean;

import java.util.Date;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-09 15:12
 */
public class UserManager {
    private Date dateValue;

    public Date getDateValue() {
        return dateValue;
    }

    public UserManager() {
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public String toString() {
        return "UserManager{" + "dateValue=" + dateValue + '}';
    }
}
