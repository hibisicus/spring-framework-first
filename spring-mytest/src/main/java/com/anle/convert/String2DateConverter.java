package com.anle.convert;

import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-10-12 17:21
 */
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        return new Date("yyyy-MM-dd HH:mm:ss");
    }
}
